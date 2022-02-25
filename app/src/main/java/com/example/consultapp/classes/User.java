package com.example.consultapp.classes;

public class User {

    String id, name, studentId;

    public User() {

    }
    public User(String id, String name, String studentId) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
