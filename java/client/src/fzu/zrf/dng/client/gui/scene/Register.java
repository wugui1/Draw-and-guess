package fzu.zrf.dng.client.gui.scene;

import fzu.zrf.dng.both.io.RegisterInfo;
import fzu.zrf.dng.both.io.RegisterInfo.Result;
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

public class Register extends Scene {

    public static final Register INSTANCE = new Register();

    private Register() {
        super(new GridPane());
        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Label(I18N.get("register.username.hint")), 0, 0);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        grid.add(new Label(I18N.get("register.nickname.hint")), 0, 1);
        TextField nickname = new TextField();
        grid.add(nickname, 1, 1);

        grid.add(new Label(I18N.get("register.password.hint")), 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        grid.add(new Label(I18N.get("register.confirm.password.hint")), 0, 3);
        PasswordField confirmBox = new PasswordField();
        grid.add(confirmBox, 1, 3);

        Button ret = new Button(I18N.get("register.return.hint"));
        ret.setOnAction(evt -> ClientMain.setScene(Login.INSTANCE));
        Button reg = new Button(I18N.get("login.register.hint"));
        reg.setOnAction(evt -> {
            try (ObjectSocket os = new ObjectSocket()) {
                os.write(new RegisterInfo(userTextField.getText(), nickname.getText(), pwBox.getText()));
                switch ((Result) os.read()) {
                case FAILED:
                    System.out.println(Result.FAILED);
                    break;
                case SUCCESS:
                    System.out.println(Result.SUCCESS);
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(ret, reg);
        grid.add(hbBtn, 1, 5);
    }

}
