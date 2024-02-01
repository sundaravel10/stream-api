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

        System.out.println("---------> 7. What is the average salary of each department?");
        Map<String, Double> departmentAvereageSalaryMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(departmentAvereageSalaryMap);

        System.out.println("---------> 8. Get the details of youngest male employee in the product development department?");
        Optional<Employee> youngestMaleEmployee = employeeList.stream().filter(employee -> "Male".equals(employee.getGender()) && "Product Development".equals(employee.getDepartment())).min(Comparator.comparing(Employee::getAge));
        System.out.println(youngestMaleEmployee.orElse(null));

        System.out.println("---------> 9. Who has the most working experience in the organization?");
        Optional<Employee> employeeWithMostWorkExperience = employeeList.stream().min(Comparator.comparing(Employee::getYearOfJoining));
        System.out.println(employeeWithMostWorkExperience.orElse(null));

        System.out.println("----------> 10. How many male and female employees are there in the sales and marketing team?");
        Map<String, Long> employeeCountMap = employeeList.stream().filter(employee -> "Sales and Marketing".equalsIgnoreCase(employee.getDepartment())).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(employeeCountMap);

        System.out.println("----------> 11. What is the average salary of male and female employees?");
        Map<String, Double> averageSalaryMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(averageSalaryMap);

        System.out.println("---------> 12. List down the names of all employees in each department?");
        Map<String, List<String>> employeeMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(employeeMap);

        System.out.println("----------> 13. What is the average salary and total salary of the whole organization?");
        DoubleSummaryStatistics salarySummaryStats = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average Salary : "+salarySummaryStats.getAverage());
        System.out.println("Total Salary : "+salarySummaryStats.getSum());


        System.out.println("-----------> 14. Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.");
        Map<Boolean, List<Employee>> ageWiseMap = employeeList.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 25));
        ageWiseMap.entrySet().forEach(System.out::println);

        System.out.println("----------> 15. Who is the oldest employee in the organization? What is his age and which department he belongs to?");
        Optional<Employee> oldestEmployee = employeeList.stream().max(Comparator.comparing(Employee::getAge));
        System.out.println(oldestEmployee.orElse(null));


       }




}
