import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {

        private int position = 1;
        private final ImageView token;
        private final String name;

        public Player(String name, String imagePath) {
            this.name = name;
            this.token = new ImageView(new Image(imagePath));
            token.setFitWidth(40);
            token.setFitHeight(40);
            setPosition(1);
        }

        public void setPosition(int pos) {
            this.position = pos;
            int row = (pos - 1) / 10;
            int col = (pos - 1) % 10;
            if (row % 2 == 1) col = 9 - col;

            double x = col * 60 + 10;
            double y = (9 - row) * 60 + 10;

            token.setLayoutX(x);
            token.setLayoutY(y);
            token.setTranslateX(0);
            token.setTranslateY(0);
        }

        public TranslateTransition moveTo(int pos) {
            int row = (pos - 1) / 10;
            int col = (pos - 1) % 10;
            if (row % 2 == 1) col = 9 - col;

            double x = col * 60 + 10;
            double y = (9 - row) * 60 + 10;

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), token);
            tt.setToX(x - token.getLayoutX());
            tt.setToY(y - token.getLayoutY());
            return tt;
        }

        public int getPosition() { return position; }
        public void updatePosition(int pos) { this.position = pos; }
        public ImageView getToken() { return token; }
        public String getName() { return name; }

}
