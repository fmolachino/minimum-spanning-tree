package com.example.mst3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.submitCity();
        controller.searchMinimumThree();
        controller.initialiceTextFieldHolder();

        // create the scene and set it on the stage
        Scene scene = new Scene(view.root, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}