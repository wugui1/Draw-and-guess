package fzu.zrf.dng.client.gui.scene;

import java.io.IOException;

import fzu.zrf.dng.both.io.LoginInfo;
import fzu.zrf.dng.client.Args;
import fzu.zrf.dng.client.Args.Key;
import fzu.zrf.dng.client.ClientMain;
import fzu.zrf.dng.client.i18n.I18N;
import fzu.zrf.dng.client.io.ObjectSocket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Login extends Scene {

    public static final Login INSTANCE = new Login();

    private Login() {
        super(new GridPane());

        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label(I18N.get("login.username.hint"));
        grid.add(userName, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label pw = new Label(I18N.get("login.password.hint"));
        grid.add(pw, 0, 1);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 1);

        Button reg = new Button(I18N.get("login.register.hint"));
        reg.setOnAction(evt -> ClientMain.setScene(Register.INSTANCE));
        Button login = new Button(I18N.get("login.login.hint"));
        login.setOnAction(evt -> {
            try (ObjectSocket os = new ObjectSocket();) {
                os.write(new LoginInfo(userTextField.getText(), pwBox.getText()));
                LoginInfo.Result rsl = (LoginInfo.Result) os.read();
                if (rsl.success) {
                    ClientMain.setScene(Lobby.INSTANCE);
                }
                Args.setValue(Key.USER_TYPE, rsl.type);
                Args.setValue(Key.AUTH, rsl.auth);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(reg, login);
        grid.add(hbBtn, 1, 3);

    }

}
