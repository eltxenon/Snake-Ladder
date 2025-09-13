
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI {
    private GameBoard board;  
    private Player red;
    private Player blue;
    private GameController controller;

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
        rollButton.setOnAction(e -> controller.playTurn(stage, diceLabel, playerLabel, winnerLabel, rollButton));

        Button showButton = new Button("📜 نمایش مار و نردبون‌ها");
        showButton.setPrefWidth(150);
        showButton.setOnAction(e -> showSnakesAndLadders());

        VBox controlPane = new VBox(20, playerLabel, diceLabel, rollButton, winnerLabel, showButton);
        controlPane.setPrefWidth(200);
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setStyle("-fx-background-color: #f0f0f0;");

        return new Scene(new HBox(boardPane, controlPane), 800, 600);
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
