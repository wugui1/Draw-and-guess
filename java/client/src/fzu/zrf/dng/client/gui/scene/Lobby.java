package fzu.zrf.dng.client.gui.scene;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Lobby extends Scene {
    public static final Lobby INSTANCE = new Lobby();

    private Lobby() {
        super(new StackPane());
        StackPane root = (StackPane) getRoot();
        BorderPane bp = new BorderPane();
        root.getChildren().add(bp);
        HBox hb = new HBox(new Button("1"));
        bp.setCenter(hb);
        ListView<String> friends = new ListView<String>(FXCollections.observableArrayList("1", "2"));
        bp.setRight(friends);
    }

}
