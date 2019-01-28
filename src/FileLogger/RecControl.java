package FileLogger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;


import java.net.URL;
import java.util.ResourceBundle;


public class RecControl implements Initializable{
	
	// @FXML //  fx:id="about_menuitem"
	  //  private MenuItem about_menuitem; // Value injected by FXMLLoader

	    @FXML //  fx:id="file_menu"
	    private Menu file_menu; // Value injected by FXMLLoader

	    @FXML //  fx:id="file_quit_menuitem"
	    private MenuItem file_quit_menuitem; // Value injected by FXMLLoader

	  //  @FXML //  fx:id="help_menu"
	   // private Menu help_menu; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_data_table_anchorpane"
	    private AnchorPane main_data_table_anchorpane; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_data_toolbar"
	    private ToolBar main_data_toolbar; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_data_toolbar_search_textfield"
	    private TextField main_data_toolbar_search_textfield; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_data_vbox"
	    private VBox main_data_vbox; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_menubar"
	    private MenuBar main_menubar; // Value injected by FXMLLoader

	    @FXML //  fx:id="main_vbox"
	    private VBox main_vbox; // Value injected by FXMLLoader

	     //  fx:id="menu_data_customers_tableview"
	   // private TableView<?> menu_data_customers_tableview; // Value injected by FXMLLoader
	    @FXML
	    private TableView<LogFiledata> tableView = new TableView<>();
	    
	    ObservableList<LogFiledata> details;
	    
	    
	    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
	    	//assert about_menuitem != null : "fx:id=\"about_menuitem\" was not injected: check your FXML file 'Main.fxml'.";
	        assert file_menu != null : "fx:id=\"file_menu\" was not injected: check your FXML file 'Main.fxml'.";
	        assert file_quit_menuitem != null : "fx:id=\"file_quit_menuitem\" was not injected: check your FXML file 'Main.fxml'.";
	        //assert help_menu != null : "fx:id=\"help_menu\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_data_table_anchorpane != null : "fx:id=\"main_data_table_anchorpane\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_data_toolbar != null : "fx:id=\"main_data_toolbar\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_data_toolbar_search_textfield != null : "fx:id=\"main_data_toolbar_search_textfield\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_data_vbox != null : "fx:id=\"main_data_vbox\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_menubar != null : "fx:id=\"main_menubar\" was not injected: check your FXML file 'Main.fxml'.";
	        assert main_vbox != null : "fx:id=\"main_vbox\" was not injected: check your FXML file 'Main.fxml'.";
	        assert tableView!= null : "fx:id=\"tableView\" was not injected: check your FXML file 'Main.fxml'.";
	        /*try {
	        
	        }catch(Exception e)
	        {
	        	System.out.println(e);
	        }*/
	    }
	    
	  //  @SupressWarnings("Unchecked")
	    void initTableview() throws Exception
	    {	//FileLogger.Parse.begin();
	    	
	    	tableView.setEditable(true);
	    	
	    	
	    	
	    	Collection<LogFiledata> list = Files.readAllLines(new File("C:\\Users\\mandar.DESKTOP-TV1E709\\workspace\\watchservice\\log.txt").toPath())
                    .stream()
                    .map(line -> {
                        String[] details = line.split(",");
                        LogFiledata cd = new LogFiledata();
                        cd.setlog(details[0]);
                        cd.setstatus(details[1]);
                        cd.setFilename(details[2]);
                        
                        if(details[1].equals("delete"))
                        {
                        	cd.setdate(null);
                            cd.setspr(null);
                            cd.setsdcr(null);
                        }else {
                        cd.setdate(details[3]);
                        cd.setspr(details[4]);
                        cd.setsdcr(details[5]);}
                        return cd;
                    })
                    .collect(Collectors.toList());
	    	
	    details = FXCollections.observableArrayList(list);
	    
	   // TableView<LogFiledata> tableView = new TableView<>();
     
        
        TableColumn<LogFiledata, String> logcol = new TableColumn<>("log number");
        TableColumn<LogFiledata, String> statuscol = new TableColumn<>("Status");
        TableColumn<LogFiledata, String> filenamecol = new TableColumn<>("File name");
        TableColumn<LogFiledata, String> datecol = new TableColumn<>("Last modified");
        TableColumn<LogFiledata, String> sprcol = new TableColumn<>("SPR");
        TableColumn<LogFiledata, String> sdcrcol = new TableColumn<>("SDCR");
       // TableColumn projcol = new TableColumn<>("Project Name"); 
       // TableColumn packcol = new TableColumn<>("Package Name");
        TableColumn approveColumn = new TableColumn<>("Approve");
        TableColumn delColumn = new TableColumn<>("Reject");
        
        tableView.getColumns().addAll(logcol,statuscol,filenamecol,datecol,sprcol,sdcrcol); //EDIT 3
       
        logcol.setCellValueFactory(data -> data.getValue().logProperty());
        statuscol.setCellValueFactory(data -> data.getValue().statusProperty());
        filenamecol.setCellValueFactory(data -> data.getValue().FilenameProperty());
        
        
        datecol.setCellValueFactory(data -> data.getValue().dateProperty());
        sprcol.setCellValueFactory(data -> data.getValue().sprProperty());
        sdcrcol.setCellValueFactory(data -> data.getValue().sdcrProperty());
        
       // tableView.setItems(details);
        
        
        approveColumn.setMinWidth(80);
        approveColumn.setEditable(false);
       // approveColumn.setCellValueFactory(new Callback(new SimpleStringProperty("saveButton")));
        approveColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogFiledata, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LogFiledata, String> p) {
               
                    return new SimpleStringProperty("saveButton");
             
        }} );
        approveColumn.setCellFactory(new Callback<TableColumn<LogFiledata, String>, TableCell<LogFiledata, String>>() {

            public TableCell<LogFiledata, String> call(TableColumn<LogFiledata, String> p) {
                TableCell returnTableCell;

                //System.out.println("item 0:"+Neo4jTableBuilder.this.tableView.getItems().get(0));

                returnTableCell = new ButtonTableCell<LogFiledata, String>(tableView,details); 

                return returnTableCell;
            }
        });
        
      //  tableView.getColumns().add(approveColumn); //edit 1
        
        
        delColumn.setMinWidth(80);
        delColumn.setEditable(false);
       // approveColumn.setCellValueFactory(new Callback(new SimpleStringProperty("saveButton")));
        delColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogFiledata, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LogFiledata, String> p) {
               
                    return new SimpleStringProperty("delButton");
             
        }} );
        delColumn.setCellFactory(new Callback<TableColumn<LogFiledata, String>, TableCell<LogFiledata, String>>() {

            public TableCell<LogFiledata, String> call(TableColumn<LogFiledata, String> p) {
                TableCell returnTableCell;

                //System.out.println("item 0:"+Neo4jTableBuilder.this.tableView.getItems().get(0));

                returnTableCell = new ButtonTableCell<LogFiledata, String>();

                return returnTableCell;
            }
        });
        
     //   tableView.getColumns().add(delColumn);		//EDIT 2
        
        
       // TableColumn<LogFiledata, String> col = filenamecol ; // column you want

        List<String> packagen = new ArrayList<>();
        ObservableList<String> obs1=FXCollections.observableArrayList(packagen);
        List<String> projn = new ArrayList<>();
        ObservableList<String> obs2=FXCollections.observableArrayList(projn);
        String q=null;
        ResultSet res,res2,res3;
        q="select package_name from package_table";
        res2=LoginManager.sql(q);
        q=null;
        q="select project_name from project_table";
        res3=LoginManager.sql(q);
        try {
        	while(res2.next())
        	{	System.out.println(res2.getString(1));
        		obs1.add(res2.getString(1));
        	}
        	while(res3.next())
        	{	System.out.println(res3.getString(1));
        		obs2.add(res3.getString(1));
        	}
        }catch(Exception e)
        {
        	System.out.println(e);
        }
        
        
      /*  for (LogFiledata item : tableView.getItems()) {
            String file=col.getCellObservableValue(item).getValue();
            q=null;
            q="select * from file_table where file_name='"+file+"'";
            res=LoginManager.sql(q);
           
            
            
            
            if(res.next())
            {	
            	continue;
            }else{*/
            		
            
      // tableView.getColumns().add(packcol);
    	
        TableColumn<LogFiledata, StringProperty> projcol = new TableColumn<>("Project Name");
        projcol.setCellValueFactory(i -> {
            final StringProperty value = i.getValue().projProperty();
            // binding to constant value
            return Bindings.createObjectBinding(() -> value);
        });
        projcol.setCellFactory(data ->{
            TableCell<LogFiledata, StringProperty> c = new TableCell<>();
            final ComboBox<String> comboBox = new ComboBox<>(obs2);
            c.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue != null) {
                    comboBox.valueProperty().unbindBidirectional(oldValue);
                }
                if (newValue != null) {
                    comboBox.valueProperty().bindBidirectional(newValue);
                }
            });
            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
            return c;
        });
        
        
        TableColumn<LogFiledata, StringProperty> packcol = new TableColumn<>("Package Name");
        projcol.setCellValueFactory(i -> {
            final StringProperty value = i.getValue().packProperty();
            // binding to constant value
            return Bindings.createObjectBinding(() -> value);
        });
        packcol.setCellFactory(data ->{
            TableCell<LogFiledata, StringProperty> c = new TableCell<>();
            final ComboBox<String> comboBox = new ComboBox<>(obs1);
            c.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue != null) {
                    comboBox.valueProperty().unbindBidirectional(oldValue);
                }
                if (newValue != null) {
                    comboBox.valueProperty().bindBidirectional(newValue);
                }
            });
            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
            return c;
        });
        
        
        
        
      tableView.getColumns().add(projcol);
      tableView.getColumns().add(packcol);
        tableView.getColumns().add(approveColumn);
    	tableView.getColumns().add(delColumn);
        tableView.setItems(details);
      //  }
        
	 //   }
	   
	    }   
	   
	    
	    	
	    
	





 class LogFiledata {
    StringProperty Filename = new SimpleStringProperty();
    StringProperty status = new SimpleStringProperty();
    StringProperty log = new SimpleStringProperty();
    StringProperty date = new SimpleStringProperty();
    StringProperty spr = new SimpleStringProperty();
    StringProperty sdcr = new SimpleStringProperty();
    StringProperty proj = new SimpleStringProperty();
    StringProperty pack = new SimpleStringProperty();
    LogFiledata()
    {
    	
    }
    public final StringProperty FilenameProperty() {
        return this.Filename;
    }

    public final java.lang.String getFilename() {
        return this.FilenameProperty().get();
    }

    public final void setFilename(final java.lang.String Filename) {
        this.FilenameProperty().set(Filename);
    }

    public final StringProperty statusProperty() {
        return this.status;
    }

    public final java.lang.String getstatus() {
        return this.statusProperty().get();
    }

    public final void setstatus(final java.lang.String status) {
        this.statusProperty().set(status);
    }

    public final StringProperty logProperty() {
        return this.log;
    }

    public final java.lang.String getlog() {
        return this.logProperty().get();
    }

    public final void setlog(final java.lang.String log) {
        this.logProperty().set(log);
    }

  public final StringProperty dateProperty() {
        return this.date;
    }

    public final java.lang.String getdate() {
        return this.dateProperty().get();
    }

    public final void setdate(final java.lang.String date) {
        this.dateProperty().set(date);
    }
    
    
    public final StringProperty sprProperty() {
        return this.spr;
    }

    public final java.lang.String getspr() {
        return this.sprProperty().get();
    }

    public final void setspr(final java.lang.String spr) {
        this.sprProperty().set(spr);
    }
    
    
    
    public final StringProperty sdcrProperty() {
        return this.sdcr;
    }

    public final java.lang.String getsdcr() {
        return this.sdcrProperty().get();
    }

    public final void setsdcr(final java.lang.String sdcr) {
        this.sdcrProperty().set(sdcr);
    }
    
    public final StringProperty packProperty() {
        return this.pack;
    }

    public final java.lang.String getpack() {
        return this.packProperty().get();
    }

    public final void setpack(final java.lang.String pack) {
        this.packProperty().set(pack);
    }
    
    public final StringProperty projProperty() {
        return this.proj;
    }

    public final java.lang.String getproj() {
        return this.projProperty().get();
    }

    public final void setproj(final java.lang.String proj) {
        this.projProperty().set(proj);
    }


}
 
  class ButtonTableCell<S, T> extends TableCell<S, T> {
     private Button button;
     private TableView<LogFiledata> tableView;
     private ObservableList<LogFiledata> details;
     //private ObservableValue<T> observableValue;

     /** constructs the ButtonTableCell
      * 
      
      */
     public ButtonTableCell()
     {
    	 
     }
     public ButtonTableCell(final TableView<LogFiledata> table,ObservableList<LogFiledata> det) {
    	 
    	 	tableView=table;
    	 	details=det;
    	 	
    	 
    	 
    	 
     }

     @Override
     public void updateItem(T item, boolean empty) {
         super.updateItem(item, empty);

         if (empty) {
             setText(null);
             setGraphic(null);
         } else {
             if (item.toString().equals("saveButton")) {
                 button = new Button("Save");
                 button.setStyle("-fx-base: green;");
                 button.setAlignment(Pos.CENTER);
                 button.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent t) {
                         
                    	 int selectdIndex = getTableRow().getIndex();
                    	 
                    	 LogFiledata selectedRecord = (LogFiledata) tableView.getItems().get(selectdIndex);
                         
                    	 int log=Integer.parseInt(selectedRecord.getlog());
                    	 
                    	 String filename=selectedRecord.getFilename();
                    	 String sdcr=selectedRecord.getsdcr();
                    	 String spr=selectedRecord.getspr();
                    	 String date =selectedRecord.getdate();
                    	 String status=selectedRecord.getstatus();
                    	 
                    	 String sql="Select file_id from file_table where file_name='"+filename+"'";
                    	 
                    	 ResultSet res=LoginManager.sql(sql);
                    	 int fileid=0;
                    	 try {
                         if(res.next())
                         {
                        	fileid=res.getInt(1);
                         }}catch(Exception e)
                    	 {
                        	 System.out.println(e);
                    	 }
                    	 
                    	 
                        sql=null;
                        sql="insert into transaction_table(file_id,last_modified,spr,sdcr,baseline,log_no) values ("+fileid+",'"+date+"','"+spr+"','"+sdcr+"','null',"+log+")";
                        ResultSet res2=LoginManager.sql(sql);
                        details.remove(selectedRecord);
                        
                        
                     }
                 });
                 this.getStyleClass().add("table-row-cell-new");
             }
             else {
                 button = new Button("reject");
                 button.setStyle("-fx-base: red;");
                 button.setAlignment(Pos.CENTER);
                button.setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent t) {
                    	 int selectdIndex = getTableRow().getIndex();
                         System.out.println(selectdIndex);
                         //delete the selected item in data
                         LogFiledata selectedRecord = (LogFiledata) tableView.getItems().get(selectdIndex);
                         details.remove(selectedRecord);
                    
                         
                     }
                 });
                 this.getStyleClass().remove("table-row-cell-new");
             }
             setAlignment(Pos.CENTER);
             setGraphic(button);
         }
     }
 }
  
 
}
  
  
  


 