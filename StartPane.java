import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

public class StartPane {

    public static Scene start(Stage stage){
        AnchorPane pane=new AnchorPane();
        Scene scene=new Scene(pane);

        Image background =new Image("img.png");
        ImageView imageView=new ImageView(background);
        imageView.setFitWidth(1000.0);
        imageView.setFitHeight(600.0);

        Button button=new Button();
        button.setMinHeight(600);
        button.setMinWidth(1000);
        button.setBackground(new Background(new BackgroundFill(null, null, null)));
        pane.getChildren().addAll(imageView,button);

        button.setOnAction(actionEvent -> {
            Scene secondScene = menu.menu(stage);
            stage.setScene(secondScene);
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        return scene;

    }

}
