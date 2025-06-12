package com.example.alanyadesktop;

import com.example.alanyadesktop.controller.AuthController;
import com.example.alanyadesktop.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;


public class MainApp extends Application {
    // Getters et setters
    @Getter
    private static int userId;
    @Getter
    private static String authToken;
    private static Stage primaryStage;
    private static MainApp instance;

    public MainApp() {
        instance = this;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApp.primaryStage = primaryStage;
        showAuthPage();
    }


    public void showAuthPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("fxml/auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AuthController controller = fxmlLoader.getController();
        controller.setMainApp(this);

        primaryStage.setTitle("Authentification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void showMainView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController controller = fxmlLoader.getController();
        primaryStage.setTitle("Alanya - Messagerie");
        primaryStage.setScene(scene);
    }

    public static void setUserId(int userId) {
        MainApp.userId = userId;
    }

    public static void setAuthToken(String authToken) {
        MainApp.authToken = authToken;
    }
}

