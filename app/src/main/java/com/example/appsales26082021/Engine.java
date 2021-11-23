package com.example.appsales26082021;

public class Engine {
    private int horsepower;
    private String name;

    public Engine(int horsepower, String name) {
        this.horsepower = horsepower;
        this.name = name;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "horsepower=" + horsepower +
                ", name='" + name + '\'' +
                '}';
    }
}
