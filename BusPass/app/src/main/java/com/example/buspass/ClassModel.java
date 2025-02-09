package com.example.buspass;

public class ClassModel {
    private String name;
    private String gender;
    private String contact;
    private String category;
    private String source;
    private String destination;

    public ClassModel(String name, String gender, String contact, String category, String source, String destination) {
        this.name = name;
        this.gender = gender;
        this.contact = contact;
        this.category = category;
        this.source = source;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
