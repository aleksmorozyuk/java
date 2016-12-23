package com.skiena.solutions.puzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Company {
    public static class Employee {

		private final int id;
		private final String name;
		private List<Employee> reports;

		public Employee(int id, String name) {
			this.id = id;
			this.name = name;
			this.reports = new ArrayList<>();
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public List<Employee> getReports() {
			return reports;
		}

		public void addReport(Employee employee) {
			reports.add(employee);
		}
	}

	public static class EmployeeTreeNode {

		private Employee self;
		private EmployeeTreeNode parent;
		private int level;

		public EmployeeTreeNode(Employee self, EmployeeTreeNode parent,
				int level) {
			this.self = self;
			this.parent = parent;
			this.level = level;
		}

		public Employee getSelf() {
			return self;
		}

		public EmployeeTreeNode getParent() {
			return parent;
		}

		public int getLevel() {
			return level;
		}
	}

	/**
	 * closestCommonManager - method to find the closest manager (i.e. farthest from the CEO) to two employees
	 * 
	 * Solution complexity analysis: Runtime complexity O(N+H) and Spatial complexity O(2N)
	 * 
	 * Read the attached PDF for more explanation about the problem Note: Don't modify the signature of this function
	 * 
	 * @param ceo
	 * @param firstEmployee
	 * @param secondEmployee
	 * @return common manager for both employees that is closest to them.
	 */
	public static Employee closestCommonManagerIterative(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
		EmployeeTreeNode firstNode = null;
		EmployeeTreeNode secondNode = null;

		int level = 0;
		Stack<EmployeeTreeNode> stack = new Stack<>();
		stack.add(new EmployeeTreeNode(ceo, null, level));
		do {
			EmployeeTreeNode parent = stack.pop();
			level++;
			for (Employee employee : parent.getSelf().getReports()) {
				EmployeeTreeNode etn = new EmployeeTreeNode(employee, parent, level);
				stack.add(etn);

				if (etn.getSelf().getId() == firstEmployee.getId()) {
					firstNode = etn;
				} else if (etn.getSelf().getId() == secondEmployee.getId()) {
					secondNode = etn;
				}
			}
		} while (!stack.isEmpty());

		return searchCommonParent(firstNode, secondNode);
	}

	/**
	 * closestCommonManager - method to find the closest manager (i.e. farthest from the CEO) to two employees
	 *
	 * Solution complexity analysis: Runtime complexity O(N+H) and Spatial complexity O(2N)
	 *
	 * Read the attached PDF for more explanation about the problem Note: Don't modify the signature of this function
	 *
	 * @param ceo
	 * @param firstEmployee
	 * @param secondEmployee
	 * @return common manager for both employees that is closest to them.
	 */
	public static Employee closestCommonManagerRecursive(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
		return searchCommonParent(findEmployeePath(ceo, null, firstEmployee, 0), findEmployeePath(ceo, null, secondEmployee, 0));
	}

	private static EmployeeTreeNode findEmployeePath(Employee employee, EmployeeTreeNode employeeNode, Employee employeeToFind, int level) {
        EmployeeTreeNode parentNode = new EmployeeTreeNode(employee, employeeNode, level);
        level++;
        for (Employee reportee : parentNode.getSelf().getReports()) {

            // BASE CASE: If current reportee matches employee to find
            if(reportee.getId() == employeeToFind.getId()) {
                return new EmployeeTreeNode(reportee, parentNode, level);
            }

            // RECURSIVE CASE: Check reportee's children if any of them matches employee to find
            EmployeeTreeNode reporteeNode = findEmployeePath(reportee, parentNode, employeeToFind, level);
            if(reporteeNode != null && (reporteeNode.getSelf().getId() == employeeToFind.getId())) {
                return reporteeNode;
            }
        }
        return null;
    }

	/**
	 * Search for common parent between two tree nodes (assuming it exists)
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @return Employee the common parent between firstNode and secondNode
	 */
	private static Employee searchCommonParent(EmployeeTreeNode firstNode, EmployeeTreeNode secondNode) {
		if (firstNode == null || secondNode == null) {
			throw new RuntimeException("Could not find common parent");
		}

		//Corner case - if one employee is the boss of the getOtherVertexID
		if (firstNode.getSelf().getId() == secondNode.getParent().getSelf().getId()) {
			return firstNode.getSelf();
		} else if (firstNode.getParent().getSelf().getId() == secondNode.getSelf().getId()) {
			return secondNode.getSelf();
		}

		//Travel upwards the tree by level searching for common parent
		EmployeeTreeNode firstParent = firstNode.getParent();
		EmployeeTreeNode secondParent = secondNode.getParent();
		while (firstParent.getSelf().getId() != secondParent.getSelf().getId()) {
			if (firstParent.getLevel() > secondParent.getLevel()) {
				firstParent = firstParent.getParent();
			} else {
				secondParent = secondParent.getParent();
			}
		}

		return firstParent.getSelf();
	}

	public static void main(String[] args) {

        final Employee bob = new Employee(1, "Bob");
        final Employee dom = new Employee(2, "Dom");
        final Employee bill = new Employee(3, "Bill");
        final Employee nina = new Employee(4, "Nina");
        final Employee peter = new Employee(5, "Peter");
        final Employee samir = new Employee(6, "Samir");
        final Employee milton = new Employee(7, "Milton");
        final Employee porter = new Employee(8, "Porter");
        final Employee michael = new Employee(9, "Michael");

        dom.addReport(bob);
        dom.addReport(peter);
        dom.addReport(porter);

        bill.addReport(dom);
        bill.addReport(samir);
        bill.addReport(michael);

        peter.addReport(nina);
        peter.addReport(milton);

        System.out.println("Closest Common Manager: Iterative Deapth-First-Search (DFS)");

        Employee commonManager = Company.closestCommonManagerIterative(bill, nina, samir);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + nina.getName() + "' and '" + samir.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerIterative(bill, peter, nina);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + peter.getName() + "' and '" + nina.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerIterative(bill, milton, nina);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + milton.getName() + "' and '" + nina.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerIterative(bill, nina, porter);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + nina.getName() + "' and '" + porter.getName() + "': " + commonManager.getName());

        System.out.println("\nClosest Common Manager: Recursive Deapth-First-Search (DFS)");

        commonManager = Company.closestCommonManagerRecursive(bill, nina, samir);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + nina.getName() + "' and '" + samir.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerRecursive(bill, peter, nina);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + peter.getName() + "' and '" + nina.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerRecursive(bill, milton, nina);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + milton.getName() + "' and '" + nina.getName() + "': " + commonManager.getName());

        commonManager = Company.closestCommonManagerRecursive(bill, nina, porter);
        System.out.println((commonManager == null) ? "No common manager found" : "Common manager of '"
                + nina.getName() + "' and '" + porter.getName() + "': " + commonManager.getName());
    }
}