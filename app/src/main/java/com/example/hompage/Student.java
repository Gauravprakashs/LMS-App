package com.example.hompage;

public class Student {
    private String name;
    private String rollNo;
    private String course;

    public Student(String name, String rollNo, String course) {
        this.name = name;
        this.rollNo = rollNo;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getCourse() {
        return course;
    }
}
