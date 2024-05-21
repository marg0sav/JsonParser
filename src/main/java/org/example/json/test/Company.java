package org.example.json.test;

import java.util.Arrays;

public class Company {
    private String name;
    private Department[] departments;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", departments=" + Arrays.toString(departments) +
                '}';
    }
}

class Department {
    private String name;
    private Manager manager;
    private Employee[] employees;

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", employees=" + Arrays.toString(employees) +
                '}';
    }
}

class Manager {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Employee {
    private String name;
    private int age;
    private String[] skills;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + Arrays.toString(skills) +
                '}';
    }
}
