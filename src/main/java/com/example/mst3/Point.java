package com.example.mst3;

public class Point {
    private double x;
    private double y;
    private String localidad;
    private String province;

    public Point(double x, double y, String localidad, String provincia) {
        this.x = x;
        this.y = y;
        this.localidad = localidad;
        this.province = provincia;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvince() {
        return province;
    }
}
