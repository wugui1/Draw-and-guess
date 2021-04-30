package fzu.zrf.dng.client;

import fzu.zrf.dng.client.gui.scene.Login;
import fzu.zrf.dng.client.i18n.I18N;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle(I18N.get("window.title"));
        setScene(Login.INSTANCE);
        stage.show();
    }

    public static synchronized void setScene(Scene scene) {
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
