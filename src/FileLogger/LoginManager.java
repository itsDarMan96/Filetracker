package FileLogger;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.stage.Screen;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

import FileLogger.Main;
//import java.lang.Exception.*;
import java.io.IOException;
import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {
    public String sid;
    public static Scene scene;
    public static Stage stage;
    public static Connection con=DBConnect.ConnectDB();
    
    private static double x = 0;
    private static double y = 0;
    public static Parent root = null;
    //Image img = new Image(getClass().getResourceAsStream("back3.png"));
    
    
    public static ResultSet sql(String q)
    {
        try
        {
            PreparedStatement pst=con.prepareStatement(q);
            if(q.contains("select")||q.contains("Select")||q.contains("SELECT"))
                return pst.executeQuery();
            else
            {pst.execute();
                return null;}
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    public LoginManager(Scene scene,Stage st) {
        this.scene = scene;
        stage=st;
    }

    public LoginManager(){}

    public void authenticated(String sessionID) throws Exception {
        sid=sessionID;
        
        showMainView();

    }

    public void logout() {
        showLoginScreen();
    }

   

    double x1 = 0;
    double y1 = 0;
    public void mover1(Parent root,Stage stage){

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x1 = event.getSceneX();
                y1 = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x1);
                stage.setY(event.getScreenY() - y1);
            }
        });

     //   ImagePattern pattern = new ImagePattern(img);


        //scene.setFill(pattern);
    }




    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Login.fxml")
            );
        root=loader.load();
        scene.setRoot(root);
        mover();

            LoginController controller =
                    loader.<LoginController>getController();
            controller.initManager(this);
            //stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       Main.res();
    }

    public void showMainView() throws Exception {
        //System.out.println("Main view func"+LoginManager.tp);
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("record.fxml")
            );
            root=loader.load();
            mover();
            scene.setRoot(root);
           
            RecControl controller =
                    loader.<RecControl>getController();
            controller.initTableview();
            //controller.initSessionID(this);
            //stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }

    public void mover()
    {


        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });


       // ImagePattern pattern = new ImagePattern(img);


      //  scene.setFill(pattern);
    }
    
    
    
    public void testfunc()
    {
    	ResultSet res=sql("select file_name from file_table");
    	try {
    	while(res.next())
    	{
    		System.out.println(res.getString("file_name"));
    	}
    	
    }catch(Exception e)
    	{
    	System.out.println(e);
    	
    	}
    }
}

