package com.example.mst3;

import java.util.*;

public class Model {

    //-----------class variables---------
    private static List<Point> points = new ArrayList<>();
    private final int maxDistanceToNotChargeTax = 300;
    private final double pricePerKm = 10.0;
    private final double pricePerKmAbove300 = pricePerKm * 1.2;
    private final double diffProvinceTax = 500;

    //-----------class constructor---------
    public Model() {
    }


    public void addPoint(double x, double y, String city, String province){
        Point point = new Point(x, y, city, province);
        points.add(point);
    }



    public double costForThisConnection(Point a, Point b, double distance){
        double finalConnectionCost = 0;
        if (a.getProvince().equals(b.getProvince())){
            finalConnectionCost += diffProvinceTax;
        }
        if (distance < maxDistanceToNotChargeTax){
            finalConnectionCost = finalConnectionCost + (distance*pricePerKm);
        }
        else finalConnectionCost = finalConnectionCost + (distance*pricePerKmAbove300);

        return finalConnectionCost;
    }


    //TODO minimumSpanningTree MUST BE Shorter- it kinda needs a rework, we should use more auxiliaries
    public void minimumSpanningTree(View view, Controller controller) {
        int numPoints = points.size();
        if (numPoints < 2) {
            System.out.println("Not enough points to calculate minimum spanning tree.");
            return;
        }
        // create a 2D array to store the costPerConnection between points
        double[][] costPerConnection = new double[numPoints][numPoints];
        for (int i = 0; i < numPoints; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < numPoints; j++) {
                Point p2 = points.get(j);
                double distance = calculateDistance(p1, p2);
                double finalConnectionCost = costForThisConnection(p1,p2,distance);
                costPerConnection[i][j] = finalConnectionCost;
                costPerConnection[j][i] = finalConnectionCost;
            }
        }
        // create an array to store the minimum costPerConnection from each point to the tree
        double[] minCost = new double[numPoints];
        Arrays.fill(minCost, Double.MAX_VALUE);
        minCost[0] = 0; // set the distance of the first point to 0
        // create an array to store the parent of each point in the tree
        int[] parents = new int[numPoints];
        Arrays.fill(parents, -1);
        // create a set to store the points that have already been added to the tree
        Set<Integer> addedPoints = new HashSet<>();
        // add the remaining points to the tree
        for (int i = 0; i < numPoints - 1; i++) {
            // find the point with the minimum distance to the tree
            int minIndex = -1;
            double lowestCost = Double.MAX_VALUE;
            for (int j = 0; j < numPoints; j++) {
                if (!addedPoints.contains(j) && minCost[j] < lowestCost) {
                    minIndex = j;
                    lowestCost = minCost[j];
                }
            }
            // add the point to the tree and update the minimum costPerConnection and parents
            addedPoints.add(minIndex);
            minCost[minIndex] = Double.MAX_VALUE;
            for (int j = 0; j < numPoints; j++) {
                if (!addedPoints.contains(j) && minCost[j] > costPerConnection[minIndex][j]) {
                    minCost[j] = costPerConnection[minIndex][j];
                    parents[j] = minIndex;
                }
            }
        }

        // calculate the total cost of the minimum spanning tree
        double totalCost = 0;

        for (int i = 1; i < numPoints; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(parents[i]);
            double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
            double cost = costForThisConnection(p1, p2, distance);
            totalCost += cost;
            System.out.println("Costo total: " + totalCost);
        }
        System.out.println("Costo total: " + totalCost);


        controller.drawMST(points, numPoints, view, parents);
    }

    //Auxiliaries:
    private double calculateDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public List<Point> getPoints(){
        return this.points;
    }

    public int getPointsSize(){
        return this.points.size();
    }

}
