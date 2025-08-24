import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public GameBoard() {
        defineSnakes();
        defineLadders();
    }

    private void defineSnakes() {
        snakes.put(99, 41);
        snakes.put(89, 53);
        snakes.put(76, 58);
        snakes.put(66, 45);
        snakes.put(54, 31);
        snakes.put(43, 18);
        snakes.put(40, 3);
        snakes.put(27, 5);
    }

    private void defineLadders() {
        ladders.put(4, 25);
        ladders.put(13, 46);
        ladders.put(33, 49);
        ladders.put(42, 63);
        ladders.put(50, 69);
        ladders.put(62, 81);
        ladders.put(74, 92);
    }

    public int resolvePosition(int pos) {
        if (snakes.containsKey(pos)) return snakes.get(pos);
        if (ladders.containsKey(pos)) return ladders.get(pos);
        return pos;
    }
}