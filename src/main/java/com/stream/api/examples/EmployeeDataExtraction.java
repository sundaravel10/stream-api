package main.java.com.stream.api.examples;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDataExtraction {


    public static void main(String[] args) {

        List<Employee> employeeList = Employee.loadEmployeeData();
        System.out.println("--------> 1. How many male and female employees are there in the organization?");

        long maleCount = employeeList.stream().filter(employee -> "Male".equalsIgnoreCase(employee.getGender())).count();
        long femaleCount = employeeList.stream().filter(employee -> "Female".equalsIgnoreCase(employee.getGender())).count();

        System.out.println("Count of Males: " + maleCount);
        System.out.println("Count of Female: " + femaleCount);

        Map<String,Long> resultMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("Result " + resultMap);

        System.out.println("-------> 2. Print the name of all departments in the organization?");
        Set<String> departmentList = employeeList.stream().map(Employee::getDepartment).collect(Collectors.toSet());

        System.out.println("Department List " + departmentList);
        System.out.println("Department List using another method ");
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        System.out.println("--------> 3. What is the average age of male and female employees?");
        Map<String,Double> averageAgeMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingLong(Employee::getAge)));
        System.out.println("Employee average age " + averageAgeMap);

        System.out.println("----------> 4. Get the details of highest paid employee in the organization?");
        Optional<Employee> maxSalary = employeeList.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println("Max Salary Employee Details " + maxSalary.orElse(null));

        System.out.println("-----------> 5. Get the names of all employees who have joined after 2015?");
        List<String> employeeNameList = employeeList.stream().filter(employee -> employee.getYearOfJoining() > 2015).map(Employee::getName).toList();
        System.out.println("Employees who have joined after 2015 " + employeeNameList);

        System.out.println("----------> 6. Count the number of employees in each department?");
        Map<String, Long> employerCounting = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(employerCounting);

       }




}
