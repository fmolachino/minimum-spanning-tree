package com.example.mst2;

import java.util.*;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private static List<Point> points = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {

        // create a canvas and its graphics context
        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();

        // draw the border
        drawBorder();

        // create the input fields for the point
        Label xLabel = new Label("X: (20-380)");
        TextField xField = new TextField();
        Label yLabel = new Label("Y: (20-380)");
        TextField yField = new TextField();
        Label localidadLabel = new Label("Localidad:");
        TextField localidadField = new TextField();
        Label provinciaLabel = new Label("Provincia:");
        TextField provinciaField = new TextField();

        Button runButton = new Button("Run!");
        runButton.setOnAction(e -> {
            minimumSpanningTree();
        });

        // create the submit button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // get the values from the input fields
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            String localidad = localidadField.getText();
            String provincia = provinciaField.getText();
            // check if x and y are within the allowed range
            if (x < 20 || x > 380 || y < 20 || y > 380) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Coordenadas Invalidas");
                alert.setContentText("Las coordenadas deben ser entre 20 y 380.");
                alert.showAndWait();
                return;
            }
            // create a new point and add it to the list
            Point point = new Point(x, y, localidad, provincia);
            points.add(point);
            // clear the canvas and draw all the points
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawBorder();
            gc.setFill(Color.RED);
            for (Point p : points) {
                gc.fillOval(p.getX(), p.getY(), 10, 10);
                gc.fillText(p.getLocalidad() + ", " + p.getProvincia(), p.getX(), p.getY() - 10);
            }

            if (points.size() > 1) {
                distanceFromLast2Points();
            }
        });

        // create a GridPane to hold the input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, xLabel, xField);
        inputGrid.addRow(1, yLabel, yField);
        inputGrid.addRow(2, localidadLabel, localidadField);
        inputGrid.addRow(3, provinciaLabel, provinciaField);

        // create a VBox to hold the input grid and the buttons
        VBox submitBox = new VBox(10, inputGrid, submitButton, runButton);
        submitBox.setAlignment(Pos.CENTER);

        // create the root layout and add the canvas and submit box
        VBox root = new VBox(10, canvas, submitBox);
        root.setAlignment(Pos.CENTER);

        // create the scene and set it on the stage
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    private void drawBorder() {
        double padding = 10;
        double width = canvas.getWidth() - padding * 2;
        double height = canvas.getHeight() - padding * 2;

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(padding, padding, width, height);

        gc.setStroke(Color.RED);
        gc.moveTo(padding, padding);
        gc.lineTo(padding + width, padding + height);
    }

    private void distanceFromLast2Points() {
        int numPoints = points.size();
        if (numPoints < 2) {
            System.out.println("Not enough points to calculate distance.");
            return;
        }
        Point lastPoint = points.get(numPoints - 1);
        Point secondLastPoint = points.get(numPoints - 2);
        double distance = Math.sqrt(Math.pow(lastPoint.getX() - secondLastPoint.getX(), 2)
                + Math.pow(lastPoint.getY() - secondLastPoint.getY(), 2));
        System.out.printf("Distance between (%f, %f) and (%f, %f): %f\n",
                lastPoint.getX(), lastPoint.getY(), secondLastPoint.getX(), secondLastPoint.getY(), distance);
    }

    private void minimumSpanningTree() {
        int numPoints = points.size();
        if (numPoints < 2) {
            System.out.println("Not enough points to calculate minimum spanning tree.");
            return;
        }
        // create a 2D array to store the distances between points
        double[][] distances = new double[numPoints][numPoints];
        for (int i = 0; i < numPoints; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < numPoints; j++) {
                Point p2 = points.get(j);
                double distance = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
        // create an array to store the minimum distances from each point to the tree
        double[] minDistances = new double[numPoints];
        Arrays.fill(minDistances, Double.MAX_VALUE);
        minDistances[0] = 0; // set the distance of the first point to 0
        // create an array to store the parent of each point in the tree
        int[] parents = new int[numPoints];
        Arrays.fill(parents, -1);
        // create a set to store the points that have already been added to the tree
        Set<Integer> addedPoints = new HashSet<>();
        // add the remaining points to the tree
        for (int i = 0; i < numPoints - 1; i++) {
            // find the point with the minimum distance to the tree
            int minIndex = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < numPoints; j++) {
                if (!addedPoints.contains(j) && minDistances[j] < minDistance) {
                    minIndex = j;
                    minDistance = minDistances[j];
                }
            }
            // add the point to the tree and update the minimum distances and parents
            addedPoints.add(minIndex);
            minDistances[minIndex] = Double.MAX_VALUE;
            for (int j = 0; j < numPoints; j++) {
                if (!addedPoints.contains(j) && minDistances[j] > distances[minIndex][j]) {
                    minDistances[j] = distances[minIndex][j];
                    parents[j] = minIndex;
                }
            }
        }
        // draw the minimum spanning tree
        gc.setStroke(Color.BLUE);
        for (int i = 1; i < numPoints; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(parents[i]);
            gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Point {
        private double x;
        private double y;
        private String localidad;
        private String provincia;

        public Point(double x, double y, String localidad, String provincia) {
            this.x = x;
            this.y = y;
            this.localidad = localidad;
            this.provincia = provincia;
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

        public String getProvincia() {
            return provincia;
        }
    }
}