import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {

    public static Scene createMenuScene(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 1000, 600);


        ImageView background = new ImageView(new Image("img_1.jpeg"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);


        Button botButton = new Button();
        botButton.setMinSize(160, 110);
        botButton.setTextFill(Color.DARKBLUE);
        botButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        AnchorPane.setTopAnchor(botButton, 270.0);
        AnchorPane.setLeftAnchor(botButton, 520.0);


        Button pvpButton = new Button();
        pvpButton.setMinSize(160, 110);
        pvpButton.setTextFill(Color.RED);
        pvpButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        AnchorPane.setTopAnchor(pvpButton, 270.0);
        AnchorPane.setLeftAnchor(pvpButton, 300.0);

        botButton.setOnAction(e -> {
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createScene(stage, false);
            stage.setScene(gameScene);
        });

        pvpButton.setOnAction(e -> {
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createScene(stage, true);
            stage.setScene(gameScene);
        });

        root.getChildren().addAll(botButton, pvpButton);
        return scene;
    }
}
