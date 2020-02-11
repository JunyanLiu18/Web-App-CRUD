package net.vehiclemanagement.model;


public class Vehicle {
    protected int id;
    protected int year;
    protected String make;
    protected String model;

    public Vehicle() {
    }

    public Vehicle(int year, String make, String model) {
        super();
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public Vehicle(int id, int year, String make, String model) {
        super();
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
