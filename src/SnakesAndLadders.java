//import javafx.animation.*;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//public class SnakesAndLadders {
//
//    private int pos1 = 1, pos2 = 1;
//    private boolean isPlayer1Turn = true;
//    private boolean gameOver = false;
//    private boolean isVsComputer = true;
//
//    private ImageView token1, token2;
//    private Text playerLabel = new Text("Player : Red");
//    private Text diceLabel = new Text("Dice : -");
//    private Text winnerLabel = new Text("");
//
//    private Map<Integer, Integer> snakes = new HashMap<>();
//    private Map<Integer, Integer> ladders = new HashMap<>();
//    private Random random = new Random();
//
//    private Button rollButton;
//
//    public Scene startGame(Stage stage, boolean isVsComputer) {
//        this.isVsComputer = isVsComputer;
//
//        ImageView board = new ImageView(new Image("d.jpg"));
//        board.setFitWidth(600);
//        board.setFitHeight(600);
//
//        token1 = createToken("img_4.png");
//        token2 = createToken("img_3.png");
//
//        Pane boardPane = new Pane(board, token1, token2);
//        boardPane.setPrefSize(600, 600);
//
//        VBox controlPane = new VBox(20);
//        controlPane.setPrefWidth(200);
//        controlPane.setAlignment(Pos.CENTER);
//        controlPane.setStyle("-fx-background-color: #f0f0f0;");
//
//        playerLabel.setFont(Font.font(18));
//        diceLabel.setFont(Font.font(18));
//        winnerLabel.setFont(Font.font(20));
//        winnerLabel.setFill(Color.DARKGREEN);
//
//        rollButton = new Button("🎲 Rolling");
//        rollButton.setPrefWidth(150);
//
//        rollButton.setOnAction(e -> {
//            if (gameOver) return;
//            if (isVsComputer && !isPlayer1Turn) return;
//            playTurn(stage);
//        });
//
//        controlPane.getChildren().addAll(playerLabel, diceLabel, rollButton, winnerLabel);
//
//        HBox root = new HBox(boardPane, controlPane);
//        Scene scene = new Scene(root, 800, 600);
//        snakes.put(99, 41);
//        snakes.put(89, 53);
//        snakes.put(76, 58);
//        snakes.put(66, 45);
//        snakes.put(54, 31);
//        snakes.put(43, 18);
//        snakes.put(40, 3);
//        snakes.put(27, 5);
//
//
//        ladders.put(4, 25);
//        ladders.put(13, 46);
//        ladders.put(33, 49);
//        ladders.put(42, 63);
//        ladders.put(50, 69);
//        ladders.put(62, 81);
//        ladders.put(74, 92);
//
//        return scene;
//    }
//
//    private void playTurn(Stage stage) {
//        int dice = random.nextInt(6) + 1;
//        diceLabel.setText("dice : " + dice);
//        playerLabel.setText("player : " + (isPlayer1Turn ? "red" : "blue"));
//
//        ImageView currentToken = isPlayer1Turn ? token1 : token2;
//        int currentPos = isPlayer1Turn ? pos1 : pos2;
//        int targetPos = Math.min(currentPos + dice, 100);
//        int finalPos = checkPosition(targetPos);
//
//        SequentialTransition sequence = new SequentialTransition();
//
//        TranslateTransition moveToTarget = createTransition(currentToken, targetPos);
//        moveToTarget.setOnFinished(ev -> {
//            if (isPlayer1Turn) pos1 = targetPos;
//            else pos2 = targetPos;
//            setPosition(currentToken, targetPos);
//        });
//        sequence.getChildren().add(moveToTarget);
//
//        if (finalPos != targetPos) {
//            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
//            sequence.getChildren().add(pause);
//
//            TranslateTransition moveToFinal = createTransition(currentToken, finalPos);
//            moveToFinal.setOnFinished(ev -> {
//                if (isPlayer1Turn) pos1 = finalPos;
//                else pos2 = finalPos;
//                setPosition(currentToken, finalPos);
//            });
//            sequence.getChildren().add(moveToFinal);
//        }
//
//        sequence.setOnFinished(ev -> {
//            if ((isPlayer1Turn && pos1 == 100) || (!isPlayer1Turn && pos2 == 100)) {
//                winnerLabel.setText("🎉 Winner: " + (isPlayer1Turn ? "Red player" : (isVsComputer ? "Bot" : "Blue player")));
//                gameOver = true;
//                rollButton.setDisable(true);
//            } else {
//                isPlayer1Turn = !isPlayer1Turn;
//
//                if (isVsComputer && !isPlayer1Turn && !gameOver) {
//                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
//                    pause.setOnFinished(e2 -> playTurn(stage));
//                    pause.play();
//                }
//            }
//        });
//
//        sequence.play();
//    }
//
//    private ImageView createToken(String path) {
//        ImageView token = new ImageView(new Image(path));
//        token.setFitWidth(40);
//        token.setFitHeight(40);
//        setPosition(token, 1);
//        return token;
//    }
//
//    private void setPosition(ImageView token, int pos) {
//        int row = (pos - 1) / 10;
//        int col = (pos - 1) % 10;
//        if (row % 2 == 1) col = 9 - col;
//
//        double x = col * 60 + 10;
//        double y = (9 - row) * 60 + 10;
//
//        token.setLayoutX(x);
//        token.setLayoutY(y);
//        token.setTranslateX(0);
//        token.setTranslateY(0);
//    }
//
//    private TranslateTransition createTransition(ImageView token, int pos) {
//        int row = (pos - 1) / 10;
//        int col = (pos - 1) % 10;
//        if (row % 2 == 1) col = 9 - col;
//
//        double x = col * 60 + 10;
//        double y = (9 - row) * 60 + 10;
//
//        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), token);
//        tt.setToX(x - token.getLayoutX());
//        tt.setToY(y - token.getLayoutY());
//        return tt;
//    }
//
//    private int checkPosition(int pos) {
//        if (snakes.containsKey(pos)) return snakes.get(pos);
//        if (ladders.containsKey(pos)) return ladders.get(pos);
//        return pos;
//    }
//
//}