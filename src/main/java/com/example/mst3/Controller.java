package com.example.mst3;

import javafx.scene.paint.Color;

import java.util.List;

public class Controller {

    //-----------class variables---------
    private final Model model;
    private final View view;

    //------------class constructor---------
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


//    public void drawMST(List<Point> points, int numPoints, View view, int[] parents) {
//        view.setColorAndDrawThree(points, numPoints, parents);
//        //TODO view.showTotalCost();
//        //view.showTotalCost();
//    }
    public void drawMST(List<Point> points, int numPoints, View view, int[] parents, double totalCost) {
        view.setColorAndDrawThree(points, numPoints, parents);
        view.setTotalCost(totalCost);
    }

    public void searchMinimumThree() {
        view.runButton.setOnAction(e -> {
            model.minimumSpanningTree(view, this);
        });
    }

    public void submitCity() {
        view.submitButton.setOnAction(e -> {
            view.checkAddedCity();
            model.addPoint(view.getX(), view.getY(), view.getLocalidad(), view.getProvincia());
            // clear the canvas and draw all the points
            view.clearAndDrawPoints(model.getPoints());
        });
    }

    public void initialiceTextFieldHolder() {
        view.initialiceTextFieldHolder();
    }
}

