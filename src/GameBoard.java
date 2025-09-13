import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameBoard {
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public GameBoard(SnakeLadderConfig config) {
        switch (config.getMode()) {
            case DEFAULT -> defineDefault(); 
            case CUSTOM_COUNT -> {
                defineRandomSnakes(config.getSnakes());
                defineRandomLadders(config.getLadders());
            }
            case FULL_RANDOM -> defineFullRandom(config.getSnakes(), config.getLadders());
        }
    }

    private void defineDefault() {
        snakes.put(99, 41);
        snakes.put(89, 53);
        snakes.put(76, 58);
        snakes.put(66, 45);
        snakes.put(54, 31);
        snakes.put(43, 18);
        snakes.put(40, 3);
        snakes.put(27, 5);

        ladders.put(4, 25);
        ladders.put(13, 46);
        ladders.put(33, 49);
        ladders.put(42, 63);
        ladders.put(50, 69);
        ladders.put(62, 81);
        ladders.put(74, 92);
    }

    private void defineRandomSnakes(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int head = rand.nextInt(90) + 10; 
            int tail = rand.nextInt(head - 1) + 1; 
            snakes.put(head, tail);
        }
    }

    private void defineRandomLadders(int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int bottom = rand.nextInt(80) + 1;
            int top = bottom + rand.nextInt(20) + 5;
            if (top <= 100) {
                ladders.put(bottom, top);
            }
        }
    }

    private void defineFullRandom(int snakeCount, int ladderCount) {
        Random rand = new Random();

        for (int i = 0; i < snakeCount; i++) {
            int head = rand.nextInt(99) + 2; 
            int tail = rand.nextInt(head - 1) + 1;
            snakes.put(head, tail);
        }

        for (int i = 0; i < ladderCount; i++) {
            int bottom = rand.nextInt(95) + 1;
            int top = rand.nextInt(100 - bottom) + bottom + 1;
            ladders.put(bottom, top);
        }
    }

    public int resolvePosition(int pos) {
        if (snakes.containsKey(pos)) return snakes.get(pos);
        if (ladders.containsKey(pos)) return ladders.get(pos);
        return pos;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }
}
