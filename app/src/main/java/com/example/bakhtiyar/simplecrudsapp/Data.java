package com.example.bakhtiyar.simplecrudsapp;

/**
 * Created by Bakhtiyar on 12/30/2016.
 */
public class Data {

    String key;

    String name;

    String classs;

    int roll;

    int age;

    public Data(String key, String name, String classs, int roll, int age) {
        this.key = key;
        this.name = name;
        this.classs = classs;
        this.roll = roll;
        this.age = age;
    }

    public Data() {
    }



    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getClasss() {
        return classs;
    }

    public int getRoll() {
        return roll;
    }

    public int getAge() {
        return age;
    }
}
