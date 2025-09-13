
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class GameUI {
    private GameBoard board;
    private Player red;
    private Player blue;
    private GameController controller;

    private TextArea historyArea = new TextArea();

    public Scene createScene(Stage stage, boolean isVsComputer, SnakeLadderConfig config) {
        
        board = new GameBoard(config);
        red = new Player("Red", "img_4.png");
        blue = new Player(isVsComputer ? "Bot" : "Blue", "img_3.png");

        controller = new GameController(board, red, blue, isVsComputer);

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
        rollButton.setOnAction(e -> {
            int diceValue = controller.playTurn(stage, diceLabel, playerLabel, winnerLabel, rollButton);

            if (diceValue > 0) {
                String currentPlayer = playerLabel.getText().replace("Player : ", "");
                historyArea.appendText(currentPlayer + " 🎲 : " + diceValue + "\n");

                playSound("dice.mp3");

                int pos = currentPlayer.equals("Red") ? red.getPosition() : blue.getPosition();
                if (board.getSnakes().containsKey(pos)) playSound("snake.mp3");
                else if (board.getLadders().containsKey(pos)) playSound("ladder.mp3");
            }
            
            if (!winnerLabel.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("بازی تمام شد");
                alert.setHeaderText(winnerLabel.getText());
                alert.setContentText("می‌خواهید دوباره بازی کنید؟");
                alert.showAndWait();
            }
        });

        Button refreshButton = new Button("🔄 رفرش بازی");
        refreshButton.setPrefWidth(150);
        refreshButton.setOnAction(e -> {
            red.setPosition(1);
            blue.setPosition(1);
            controller.resetGame();

            diceLabel.setText("Dice : -");
            playerLabel.setText("Player : Red");
            winnerLabel.setText("");
            rollButton.setDisable(false);
            historyArea.clear();
        });

        historyArea.setEditable(false);
        historyArea.setPrefHeight(200);
        historyArea.setPrefWidth(180);

        VBox controlPane = new VBox(20, playerLabel, diceLabel, rollButton, winnerLabel, refreshButton,
                new Text("📜 تاریخچه:"), historyArea);
        controlPane.setPrefWidth(200);
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setStyle("-fx-background-color: #f0f0f0;");

        return new Scene(new HBox(boardPane, controlPane), 800, 600);
    }

    private void playSound(String fileName) {
        try {
            URL resource = getClass().getResource(fileName);
            if (resource != null) {
                AudioClip clip = new AudioClip(resource.toString());
                clip.play();
            }
        } catch (Exception e) {
            System.out.println("مشکل در پخش صدا: " + fileName);
        }
    }

    public void showSnakesAndLadders() {
        if (board == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("🐍 مارها:\n");
        board.getSnakes().forEach((head, tail) -> sb.append("از ").append(head).append(" به ").append(tail).append("\n"));

        sb.append("\n🪜 نردبون‌ها:\n");
        board.getLadders().forEach((bottom, top) -> sb.append("از ").append(bottom).append(" به ").append(top).append("\n"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("لیست مار و نردبون‌ها");
        alert.setHeaderText("موقعیت‌ها");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }
}
