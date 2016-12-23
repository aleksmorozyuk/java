// Copyright (c) 2016 Boomi, Inc.
package com.mcdowell.solutions.problems.hard;

import com.mcdowell.solutions.problems.hard.common.Helper;

import java.util.Set;

/**
 * @author aleks.
 */
public class Respace {

    static class ParseResult {
        public String parsed = "";
        public int invalid = Integer.MAX_VALUE;
        public ParseResult(int inv, String p) {
            parsed = p;
            invalid = inv;
        }

        public ParseResult clone() {
            return new ParseResult(this.invalid, this.parsed);
        }

        public static ParseResult min(ParseResult r1, ParseResult r2) {
            if (r1 == null) {
                return r2;
            } else if (r2 == null) {
                return r1;
            }

            return r2.invalid < r1.invalid ? r2 : r1;
        }
    }

    public static String clean(String sentence) {
        char[] punctuation = {',', '"', '!', '.', '\'', '?', ','};
        for (char character : punctuation) {
            sentence = sentence.replace(character, ' ');
        }
        return sentence.replace(" ", "").toLowerCase();
    }

    public static String bestSplit(Set<String> dictionary, String sentence) {
        ParseResult[] memoized = new ParseResult[sentence.length()];
        ParseResult parseResult = split(dictionary, memoized, sentence, 0);
        return parseResult == null ? null : parseResult.parsed;
    }

    public static ParseResult split(Set<String> dictionary, ParseResult[] memoized, String sentence, int start) {
        if(start >= sentence.length()) {
            return new ParseResult(0, "");
        }

        if(memoized[start] != null) {
            return memoized[start];
        }

        if (dictionary.contains(sentence)) {
            return new ParseResult(0, sentence);
        }

        String partial = "";
        String bestParsing = null;
        int bestInvalid = Integer.MAX_VALUE;

        for(int index = start; index < sentence.length(); index++) {
            char character = sentence.charAt(index);
            partial = partial + character;

            // Check with dictionary if partial substring can be separated
            int invalid = dictionary.contains(partial) ? 0 : partial.length();
            if(invalid < bestInvalid) { // We found a partial to separate with space

                // Let's next check if next partial substring can be separated
                ParseResult result = split(dictionary,memoized,sentence,index+1);
                if(invalid + result.invalid < bestInvalid) { // We found best parsing
                    bestInvalid = invalid + result.invalid;
                    bestParsing = partial + " " + result.parsed;

                    if(bestInvalid == 0) { // There is no need to check already best result
                        break;
                    }
                }
            }
        }

        // Remember best partial substring to reduce process of same partial substring
        memoized[start] = new ParseResult(bestInvalid, bestParsing);
        return memoized[start];
    }

    public static void main(String[] args) {
        final String sentence = "As one of the top companies in the world, Google";
        System.out.println(bestSplit(Helper.getWordListAsHashSet(), clean(sentence)));
    }
}
