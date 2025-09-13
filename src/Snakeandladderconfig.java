public class SnakeLadderConfig {
    public enum Mode {
        DEFAULT, CUSTOM_COUNT, FULL_RANDOM
    }

    private Mode mode;
    private int snakes;
    private int ladders;

    public SnakeLadderConfig(Mode mode, int snakes, int ladders) {
        this.mode = mode;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public Mode getMode() {
        return mode;
    }

    public int getSnakes() {
        return snakes;
    }

    public int getLadders() {
        return ladders;
    }
}
