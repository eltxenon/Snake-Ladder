
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Optional;

public class Menu {

    public static Scene createMenuScene(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 1000, 600);

        ImageView background = new ImageView(new Image("img_1.jpeg"));
        background.setFitWidth(1000);
        background.setFitHeight(600);
        root.getChildren().add(background);

        Button defaultBtn = new Button(" حالت پیشفرض");
        defaultBtn.setMinSize(160, 60);
        AnchorPane.setTopAnchor(defaultBtn, 180.0);
        AnchorPane.setLeftAnchor(defaultBtn, 420.0);
        defaultBtn.setOnAction(e -> {
            SnakeLadderConfig config = new SnakeLadderConfig(SnakeLadderConfig.Mode.DEFAULT, 0, 0);
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createScene(stage, false, config);
            stage.setScene(gameScene);
        });

        Button customBtn = new Button("⚙️ انتخاب تعداد");
        customBtn.setMinSize(160, 60);
        AnchorPane.setTopAnchor(customBtn, 260.0);
        AnchorPane.setLeftAnchor(customBtn, 420.0);
        customBtn.setOnAction(e -> {
            TextInputDialog snakeDialog = new TextInputDialog("5");
            snakeDialog.setHeaderText("تعداد مارها:");
            Optional<String> snakeResult = snakeDialog.showAndWait();

            TextInputDialog ladderDialog = new TextInputDialog("5");
            ladderDialog.setHeaderText("تعداد نردبون‌ها:");
            Optional<String> ladderResult = ladderDialog.showAndWait();

            if (snakeResult.isPresent() && ladderResult.isPresent()) {
                int snakes = Integer.parseInt(snakeResult.get());
                int ladders = Integer.parseInt(ladderResult.get());

                SnakeLadderConfig config = new SnakeLadderConfig(SnakeLadderConfig.Mode.CUSTOM_COUNT, snakes, ladders);
                GameUI gameUI = new GameUI();
                Scene gameScene = gameUI.createScene(stage, false, config);
                stage.setScene(gameScene);
            }
        });

        Button randomBtn = new Button("🎲 رندوم کامل");
        randomBtn.setMinSize(160, 60);
        AnchorPane.setTopAnchor(randomBtn, 340.0);
        AnchorPane.setLeftAnchor(randomBtn, 420.0);
        randomBtn.setOnAction(e -> {
            SnakeLadderConfig config = new SnakeLadderConfig(SnakeLadderConfig.Mode.FULL_RANDOM, 7, 7);
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createScene(stage, false, config);
            stage.setScene(gameScene);
        });

        Button showBtn = new Button("📜 نمایش مار و نردبون‌ها");
        showBtn.setMinSize(180, 60);
        AnchorPane.setTopAnchor(showBtn, 420.0);
        AnchorPane.setLeftAnchor(showBtn, 410.0);
        showBtn.setOnAction(e -> {
            
            SnakeLadderConfig config = new SnakeLadderConfig(SnakeLadderConfig.Mode.DEFAULT, 0, 0);
            GameUI gameUI = new GameUI();
            Scene gameScene = gameUI.createScene(stage, false, config);
            stage.setScene(gameScene);

            
            gameUI.showSnakesAndLadders();
        });

        root.getChildren().addAll(defaultBtn, customBtn, randomBtn, showBtn);
        return scene;
    }
}
