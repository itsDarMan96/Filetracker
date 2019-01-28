package FileLogger;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;
import FileLogger.LoginManager;
import java.net.InetAddress;


public class LoginController implements Initializable{
	
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {

	    }
	 
	  @FXML
	  private TextField txtUserName;
	  @FXML 
	  private TextField txtPassword;
	  
	  @FXML
	  private TextField Port;
	  
	  @FXML
	  private TextField IP;
	  
	  static LoginManager lm;
	    public void initManager(final LoginManager LM)
	    {
	        lm=LM;
	    }
	    
	    @FXML
	    private void exitApp(){
	        System.exit(0);
	    }
	    

	 @FXML
	 private void loginclick()
	    {
	    	
	    	
	    
	    	
	        if (txtUserName.getText().equals("")) {

	            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter user name", ButtonType.OK);
	            alert.showAndWait();

	            if (alert.getResult() == ButtonType.OK) {
	                alert.close();
	            }

	            return;

	        }
	    	String user=txtUserName.getText();
	        String Password= String.valueOf(txtPassword.getCharacters());
	        if (Password.equals("")) {
	            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter password", ButtonType.OK);
	            alert.showAndWait();

	            if (alert.getResult() == ButtonType.OK) {
	                alert.close();
	            }

	            return;

	        }
	        String pass=txtPassword.getText();
	        String ip=IP.getText();
	        String server=null;
	    	try {
	    	InetAddress addr=InetAddress.getByName(ip);
	    	
	    	server = addr.getHostName();}
	    	catch(IOException e)
	    	{
	    		System.out.println(e);
	    	}
	        
	        //String server="localhost"; // need to remove this later
	        int port=Integer.parseInt(Port.getText());
	        login(user,pass,server,port);
	    }
	    
	    
 private static void login(String user,String pass,String server,int port) {
	    	 try {
	    	        FileReader reader=new FileReader("path.properties");
	    	        Properties p=new Properties();  
	    	        p.load(reader);  
	    	        String remotepath=p.getProperty("path1");
	    	        System.out.println(remotepath);
	    	        FTPClient ftpClient = new FTPClient();

	    	        try {
	    	        	//FTPClient ftp2=ftpClient;
	    	            ftpClient.connect(server, port);
	    	            boolean success = ftpClient.login(user,pass);
	    	            if (!success) {
	    	            	Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed", ButtonType.OK);
	                        alert.showAndWait();

	                        if (alert.getResult() == ButtonType.OK) {
	                            alert.close();
	                        }
	    	            } 
	    	            else 
	    	            {
	    	            	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Successful", ButtonType.OK);
	    	                alert.showAndWait();

	    	                if (alert.getResult() == ButtonType.OK) {
	    	                    alert.close();
	    	                    //LoginManager.nam=LoginManager.tp="admin";
	    	                   // l.authenticated(generateSessionID());
	    	                    lm.showMainView();
	    	                    

	    	                }
	    	            }
	    	                
	    	                
	    	               
	    	            }
	    	             catch (IOException ex) {
	    	            System.out.println("Error: " + ex.getMessage());
	    	            ex.printStackTrace();
	    	        } 
	    	
	    	
	    	
	    }catch(Exception e)
	    	 {
	    	System.out.println(e);
	    	 }
	
	
} }