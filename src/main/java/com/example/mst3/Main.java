package com.example.mst3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private static List<com.example.mst3.Point> points = new ArrayList<>();
    private final int maxDistanceToNotChargeTax = 300;
    private final double pricePerKm = 10.0;
    private final double pricePerKmAbove300 = 12.0;
    private final double diffProvinceTax = 500;

    @Override
    public void start(Stage stage) throws Exception {

//        // create a canvas and its graphics context
//        canvas = new Canvas(700, 700);
//        gc = canvas.getGraphicsContext2D();

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        // draw the border
//        drawBorder();

        // create the input fields for the point
//        Label xLabel = new Label("X: (20-380)");
//        TextField xField = new TextField();
//        Label yLabel = new Label("Y: (20-380)");
//        TextField yField = new TextField();
//        Label localidadLabel = new Label("Localidad:");
//        TextField localidadField = new TextField();
//        Label provinciaLabel = new Label("Provincia:");
//        TextField provinciaField = new TextField();
//
//        Button runButton = new Button("Run!");


        //TODO finish main sequence. Below is a possible start. Scene must be initialiced in main.
        //Should these method stay on main?
        controller.submitCity();
        controller.searchMinimumThree(controller);
        controller.initialiceTextFieldHolder();
        // create the scene and set it on the stage
        Scene scene = new Scene(view.root, Color.WHITE);
        stage.setScene(scene);
        stage.show();


//        runButton.setOnAction(e -> {
//            minimumSpanningTree();
//        });
//
//        // create the submit button
//        Button submitButton = new Button("Submit");
//        submitButton.setOnAction(e -> {
//            // get the values from the input fields
//            double x = Double.parseDouble(xField.getText());
//            double y = Double.parseDouble(yField.getText());
//            String localidad = localidadField.getText();
//            String provincia = provinciaField.getText();
//            // check if x and y are within the allowed range
//            if (x < 20 || x > 680 || y < 20 || y > 680) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Coordenadas Invalidas");
//                alert.setContentText("Las coordenadas deben ser entre 20 y 680.");
//                alert.showAndWait();
//                return;
//            }
//
//
//            model.addPoint(x,y,localidad,provincia);
//
//            // clear the canvas and draw all the points
//            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//            drawBorder();
//            gc.setFill(Color.RED);
//            for (Point p : points) {
//                gc.fillOval(p.getX(), p.getY(), 10, 10);
//                gc.fillText(p.getLocalidad() + ", " + p.getProvince(), p.getX(), p.getY() - 10);
//            }
//        });
//
//        // create a GridPane to hold the input fields
//        GridPane inputGrid = new GridPane();
//        inputGrid.setHgap(10);
//        inputGrid.setVgap(10);
//        inputGrid.addRow(0, xLabel, xField);
//        inputGrid.addRow(1, yLabel, yField);
//        inputGrid.addRow(2, localidadLabel, localidadField);
//        inputGrid.addRow(3, provinciaLabel, provinciaField);
//
//        // create a VBox to hold the input grid and the buttons
//        VBox submitBox = new VBox(10, inputGrid, submitButton, runButton);
//        submitBox.setAlignment(Pos.CENTER);
//
//        // create the root layout and add the canvas and submit box
//        VBox root = new VBox(10, canvas, submitBox);
//        root.setAlignment(Pos.CENTER);
//
//        // create the scene and set it on the stage
//        Scene scene = new Scene(root, Color.WHITE);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void drawBorder() {
//        double padding = 10;
//        double width = canvas.getWidth() - padding * 2;
//        double height = canvas.getHeight() - padding * 2;
//
//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(2);
//        gc.strokeRect(padding, padding, width, height);
//
//        gc.setStroke(Color.RED);
//        gc.moveTo(padding, padding);
//        gc.lineTo(padding + width, padding + height);
//    }
//
//
//    public double costForThisConnection(Point a, Point b, double distance){
//        double finalConnectionCost = 0;
//        if (a.getProvince().equals(b.getProvince())){
//            finalConnectionCost += diffProvinceTax;
//        }
//        if (distance < maxDistanceToNotChargeTax){
//            finalConnectionCost = finalConnectionCost + (distance*pricePerKm);
//        }
//        else finalConnectionCost = finalConnectionCost + (distance*pricePerKmAbove300);
//
//        return finalConnectionCost;
//    }
//
//    private void minimumSpanningTree() {
//        int numPoints = points.size();
//        if (numPoints < 2) {
//            System.out.println("Not enough points to calculate minimum spanning tree.");
//            return;
//        }
//        // create a 2D array to store the costPerConnection between points
//        double[][] costPerConnection = new double[numPoints][numPoints];
//        for (int i = 0; i < numPoints; i++) {
//            Point p1 = points.get(i);
//            for (int j = i + 1; j < numPoints; j++) {
//                Point p2 = points.get(j);
//                double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
//                double finalConnectionCost = costForThisConnection(p1,p2,distance);
//                costPerConnection[i][j] = finalConnectionCost;
//                costPerConnection[j][i] = finalConnectionCost;
//            }
//        }
//        // create an array to store the minimum costPerConnection from each point to the tree
//        double[] minCost = new double[numPoints];
//        Arrays.fill(minCost, Double.MAX_VALUE);
//        minCost[0] = 0; // set the distance of the first point to 0
//
//        // create an array to store the parent of each point in the tree
//        int[] parents = new int[numPoints];
//        Arrays.fill(parents, -1);
//
//        // create a set to store the points that have already been added to the tree
//        Set<Integer> addedPoints = new HashSet<>();
//
//        // add the remaining points to the tree
//        for (int i = 0; i < numPoints - 1; i++) {
//
//            // find the point with the minimum distance to the tree
//            int minIndex = -1;
//            double lowestCost = Double.MAX_VALUE;
//            for (int j = 0; j < numPoints; j++) {
//                if (!addedPoints.contains(j) && minCost[j] < lowestCost) {
//                    minIndex = j;
//                    lowestCost = minCost[j];
//                }
//            }
//            // add the point to the tree and update the minimum costPerConnection and parents
//            addedPoints.add(minIndex);
//            minCost[minIndex] = Double.MAX_VALUE;
//            for (int j = 0; j < numPoints; j++) {
//                if (!addedPoints.contains(j) && minCost[j] > costPerConnection[minIndex][j]) {
//                    minCost[j] = costPerConnection[minIndex][j];
//                    parents[j] = minIndex;
//                }
//            }
//        }
//
//        // calculate the total cost of the minimum spanning tree
//        double totalCost = 0;
//
//        for (int i = 1; i < numPoints; i++) {
//            Point p1 = points.get(i);
//            Point p2 = points.get(parents[i]);
//            double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
//            double cost = costForThisConnection(p1, p2, distance);
//            totalCost += cost;
//            System.out.println("Costo total: " + totalCost);
//        }
//        System.out.println("Costo total: " + totalCost);
//
//        // draw the minimum spanning tree
//        //TODO put method from Controller: drawMST(List<Point> points, int numPoints, View view, int[] parents)
//        //TODO and replace code till line 231.
//        gc.setStroke(Color.BLUE);
//        for (int i = 1; i < numPoints; i++) {
//            Point p1 = points.get(i);
//            Point p2 = points.get(parents[i]);
//            gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
//        }
//
//    }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private static class Point {
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
}