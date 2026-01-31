package ui;

import db.Migrations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Migrations.migrate();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/tela-login.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Sistema de Cadastro");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
