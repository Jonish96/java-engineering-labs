package com.paryogsaala.javaengineeringlabs.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasics {
    public static void main(String[] args) {
        Map<Integer, String> employeesById = new HashMap<>();

        // Add employee records
        employeesById.put(101, "Peter");
        employeesById.put(102, "Flash");
        employeesById.put(103, "Hulk");

        System.out.println("Initial employees: " + employeesById);

        // An existing key is updated with the latest value
        employeesById.put(102, "Patrick");
        System.out.println("After updating employee 102: " + employeesById);

        // Add a value only when the key is not already mapped
        employeesById.putIfAbsent(102, "Batman");
        System.out.println("After putIfAbsent: " + employeesById);

        // Retrieve values
        System.out.println("Employee 101: " + employeesById.get(101));
        System.out.println(
                "Employee 999: "
                        + employeesById.getOrDefault(999, "Employee Not Found")
        );

        // Check whether an employee ID exists
        int employeeId = 103;

        if (employeesById.containsKey(employeeId)) {
            System.out.println(
                    "Employee found: " + employeesById.get(employeeId)
            );
        } else {
            System.out.println("Employee not found");
        }

        // Remove only when both the key and value match
        boolean removed = employeesById.remove(102, "Patrick");

        System.out.println("Employee removed: " + removed);
        System.out.println("Final employees: " + employeesById);
    }
}
