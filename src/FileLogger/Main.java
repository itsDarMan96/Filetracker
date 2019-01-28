package FileLogger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage s;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(new StackPane());
        primaryStage.setTitle("File Tracker");
        primaryStage.setMinWidth(235);
        primaryStage.setMinHeight(195);

       // Screen screen = Screen.getPrimary();
       // Rectangle2D bounds = screen.getVisualBounds();
       // primaryStage.setWidth(bounds.getWidth());
       // primaryStage.setHeight(bounds.getHeight());
        
       // primaryStage.setMaximized(true);
        Main.s=primaryStage;

        LoginManager loginManager = new LoginManager(scene,primaryStage);
        loginManager.showLoginScreen();
        
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, Color.TRANSPARENT));


        primaryStage.show();
        res();
        //loginManager.testfunc();
        // primaryStage.sizeToScene();


    }

    public static void res(){
    	
        s.resizableProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2){
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        s.sizeToScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
