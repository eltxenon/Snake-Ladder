import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class GameUI {
    public Scene createScene(Stage stage, boolean isVsComputer) {
        GameBoard board = new GameBoard();
        Player red = new Player("Red", "img_4.png");
        Player blue = new Player(isVsComputer ? "Bot" : "Blue", "img_3.png");

        GameController controller = new GameController(board, red, blue, isVsComputer);

        ImageView boardImage = new ImageView(new Image("d.jpg"));
        boardImage.setFitWidth(600);
        boardImage.setFitHeight(600);

        Pane boardPane = new Pane(boardImage, red.getToken(), blue.getToken());
        boardPane.setPrefSize(600, 600);

        Text playerLabel = new Text("Player : Red");
        Text diceLabel = new Text("Dice : -");
        Text winnerLabel = new Text("");
        winnerLabel.setFill(Color.DARKGREEN);
        winnerLabel.setFont(Font.font(20));

        Button rollButton = new Button("🎲 Rolling");
        rollButton.setPrefWidth(150);
        rollButton.setOnAction(e -> controller.playTurn(stage, diceLabel, playerLabel, winnerLabel, rollButton));

        VBox controlPane = new VBox(20, playerLabel, diceLabel, rollButton, winnerLabel);
        controlPane.setPrefWidth(200);
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setStyle("-fx-background-color: #f0f0f0;");

        // 🔹 اینجا کد لود سیو اضافه شد
        if (GameSaver.saveExists()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "بازی ذخیره‌شده‌ای وجود دارد. ادامه بدهیم؟",
                    ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    try {
                        int[] positions = GameSaver.loadGame();
                        red.setPosition(positions[0]);
                        blue.setPosition(positions[1]);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        return new Scene(new HBox(boardPane, controlPane), 800, 600);
    }
}
