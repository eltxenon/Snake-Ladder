import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameController {
    private final GameBoard board;
    private final Player player1;
    private final Player player2;
    private boolean isPlayer1Turn = true;
    private boolean gameOver = false;
    private final boolean isVsComputer;
    private final Random random = new Random();

    public GameController(GameBoard board, Player p1, Player p2, boolean isVsComputer) {
        this.board = board;
        this.player1 = p1;
        this.player2 = p2;
        this.isVsComputer = isVsComputer;
    }

    public int playTurn(Stage stage, Text diceLabel, Text playerLabel, Text winnerLabel, Button rollButton) {
        if (gameOver) return -1; 

        Player current = isPlayer1Turn ? player1 : player2;
        int dice = random.nextInt(6) + 1;

        diceLabel.setText("Dice : " + dice);
        playerLabel.setText("Player : " + current.getName());

        int target = Math.min(current.getPosition() + dice, 100);
        int finalPos = board.resolvePosition(target);

        SequentialTransition sequence = new SequentialTransition();

        TranslateTransition move1 = current.moveTo(target);
        move1.setOnFinished(e -> current.setPosition(target));
        sequence.getChildren().add(move1);

        if (finalPos != target) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            sequence.getChildren().add(pause);

            TranslateTransition move2 = current.moveTo(finalPos);
            move2.setOnFinished(e -> current.setPosition(finalPos));
            sequence.getChildren().add(move2);
        }

        sequence.setOnFinished(e -> {
            if (current.getPosition() == 100) {
                winnerLabel.setText("🎉 Winner: " + current.getName());
                gameOver = true;
                rollButton.setDisable(true);
            } else {
                isPlayer1Turn = !isPlayer1Turn;

                if (isVsComputer && !isPlayer1Turn && !gameOver) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e2 -> playTurn(stage, diceLabel, playerLabel, winnerLabel, rollButton));
                    pause.play();
                }
            }
        });

        sequence.play();
        return dice; 
    }

    public void resetGame() {
        gameOver = false;
        isPlayer1Turn = true;
    }
}
