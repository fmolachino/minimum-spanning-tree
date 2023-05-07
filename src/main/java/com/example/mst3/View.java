package com.example.mst3;

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

import java.util.List;

public class View {

    //---------class variables--------
    private Canvas canvas;
    private GraphicsContext gc;
    public Button runButton;
    public Button submitButton;
    private TextField xField;
    private TextField yField;
    private TextField localidadField;
    private TextField provinciaField;
    private Label xLabel;
    private Label yLabel;
    private Label localidadLabel;
    private Label provinciaLabel;
    public VBox root;


    //---------class constructor--------
    public View() {
        this.canvas = new Canvas(700, 700);
        this.gc = canvas.getGraphicsContext2D();
        drawBorder();
        this.submitButton = new Button("Submit");
        this.xLabel = new Label("X: (20-380)");
        this.xField = new TextField();
        this.yLabel = new Label("Y: (20-380)");
        this.yField = new TextField();
        this.localidadLabel = new Label("Localidad:");
        this.localidadField = new TextField();
        this. provinciaLabel = new Label("Provincia:");
        this.provinciaField = new TextField();
        this.runButton = new Button("Run!");
    }


    public void setColorAndDrawThree(List<Point> points, int numPoints, int[] parents) {
        this.gc.setStroke(Color.BLUE);
        for (int i = 1; i < numPoints; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(parents[i]);
            this.gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
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

    public void clearAndDrawPoints(List<Point> points){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBorder();
        gc.setFill(Color.RED);
        for (Point p : points) {
            gc.fillOval(p.getX(), p.getY(), 10, 10);
            gc.fillText(p.getLocalidad() + ", " + p.getProvince(), p.getX(), p.getY() - 10);
        }
    }

    public void checkAddedCity(){
        double x = this.getX();
        double y = this.getY();
        if (x < 20 || x > 680 || y < 20 || y > 680) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Coordenadas Invalidas");
            alert.setContentText("Las coordenadas deben ser entre 20 y 680.");
            alert.showAndWait();
            return;
        }
    }

    public void initialiceTextFieldHolder(){
        // create a GridPane to hold the input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, this.xLabel, xField);
        inputGrid.addRow(1, yLabel, yField);
        inputGrid.addRow(2, localidadLabel, localidadField);
        inputGrid.addRow(3, provinciaLabel, provinciaField);

        // create a VBox to hold the input grid and the buttons
        VBox submitBox = new VBox(10, inputGrid, submitButton, runButton);
        submitBox.setAlignment(Pos.CENTER);

        // create the root layout and add the canvas and submit box
        this.root = new VBox(10, canvas, submitBox);
        root.setAlignment(Pos.CENTER);


    }


    public double getX(){
        return Double.parseDouble(xField.getText());
    }

    public double getY(){
        return Double.parseDouble(yField.getText());
    }
    public String getLocalidad() {
        return localidadField.getText();
    }

    public String getProvincia() {
        return provinciaField.getText();
    }
}
