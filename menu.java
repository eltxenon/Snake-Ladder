import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class menu {
    public static Scene menu(Stage stage){
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 1000, 600);

        Image background =new Image("img_1.jpeg");
        ImageView imageView=new ImageView(background);
        imageView.setFitWidth(1000.0);
        imageView.setFitHeight(600.0);

        Button bot=new Button();
        bot.setMinHeight(110);
        bot.setMinWidth(160);
        AnchorPane.setRightAnchor(bot,300.0);
        AnchorPane.setTopAnchor(bot,270.0);
        bot.setBackground(new Background(new BackgroundFill(null, null, null)));

        Button pvp=new Button();
        pvp.setMinHeight(110);
        pvp.setMinWidth(160);
        pvp.setTextFill(Color.RED);
        AnchorPane.setRightAnchor(pvp,520.0);
        AnchorPane.setTopAnchor(pvp,270.0);
        pvp.setBackground(new Background(new BackgroundFill(null, null, null)));

        pvp.setOnAction(actionEvent -> {
            Scene secondScene = menu.menu(stage);
            stage.setScene(secondScene);
        });

        bot.setOnAction(actionEvent -> {
            Scene secondScene = menu.menu(stage);
            stage.setScene(secondScene);
        });

        anchorPane.getChildren().addAll(imageView,bot,pvp);
        return scene;
    }
}
