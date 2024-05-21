package org.example.json.test;

import java.util.Arrays;

public class University {
    private String name;
    private Faculty[] faculties;

    private University() {}

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", faculties=" + Arrays.toString(faculties) +
                '}';
    }

    private static class Faculty {
        private String name;
        private Department[] departments;

        private Faculty() {}

        @Override
        public String toString() {
            return "Faculty{" +
                    "name='" + name + '\'' +
                    ", departments=" + Arrays.toString(departments) +
                    '}';
        }
    }

    private static class Department {
        private String name;
        private Professor[] professors;
        private Student[] students;

        private Department() {}

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", professors=" + Arrays.toString(professors) +
                    ", students=" + Arrays.toString(students) +
                    '}';
        }
    }

    private static class Professor {
        private String name;
        private int age;
        private String title;

        private Professor() {}

        @Override
        public String toString() {
            return "Professor{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    private static class Student {
        private String name;
        private int age;
        private String[] courses;

        private Student() {}

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", courses=" + Arrays.toString(courses) +
                    '}';
        }
    }
}
