/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resultanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author suhail
 */
public class FXMLDocumentController implements Initializable {
    
    File file;
    PDDocument document;
    PDFTextStripper pdfStripper;
    String temp;
    int begIndex,endIndex;
    int pos[];
    String[] courses={"MA101","PH100","BE100","BE10102","EE100","CY100","BE110","EC100","BE103","PH110","ME110","EC110"};
    List<String> values ;
    Task analyser;
    List<Result> cust;
    public static Boolean fileLoaded=false;
    /*Table Values*/
    private final ObservableList<Result> data = FXCollections.observableArrayList();
    
    private final ProgressIndicator pin = new ProgressIndicator();
    Stage dialogStage;


    
    @FXML
    private Label label;
    @FXML
    private TableView resultTable;
    @FXML
    private TableColumn regColumn,sub1Column,sub2Column,sub3Column,sub4Column,sub5Column,sub6Column,sub7Column,sub8Column,sub9Column,sub10Column,sub11Column,sub12Column; 
    @FXML
    private ProgressIndicator indicator;
    
    public FXMLDocumentController() {   
    }
    
    @FXML
    private void chooseFile(ActionEvent event){
        
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF","*.pdf"));
        file=fileChooser.showOpenDialog(null);
        loadResult();
    }
    public void loadResult(){
        fileLoaded=false;
        resultTable.getItems().clear();
        cust=new ArrayList<>();
            
        Task task=createTask();
        
        indicator.setVisible(true);
        label.setText("");
        indicator.progressProperty().bind(task.progressProperty());
        
        new Thread(task).start();
        
         task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                indicator.setVisible(false);
            }
        });
    }
    public void getRegisterNumber(String txt,String code){
        txt=txt.replace("\n", " ");
        String[] str=txt.split(" ");
        int length=str.length;
        for(int i=0;i<length;i++){
            if(str[i].startsWith(code)){
                getResult(str,i,code,str[i]);
            }
        }
    }
    void getResult(String result[],int start,String code,String regno){
        int i=start+1;
        int c=0;
        values= new ArrayList<>();
        
        while(c<courses.length){
            for (String course : courses) {
                if (i<result.length && result[i].startsWith(course)) {
                    String grade=result[i].replace(",", "");
                    int beg=grade.indexOf("(");
                    int end=grade.indexOf(")");
                    values.add(grade.substring(beg+1, end));
                    i++;
                } else{
                    values.add("-");
                }
                c++;
            }
            c++;
        }
        
        int index=1;
        String[] res=new String[15];
        res[0]=regno;
        for(String value :values){
            res[index]=value;
            index++;
        }
        Result ress=new Result(res[0],res[1],res[2],res[3],res[4],res[5],res[6],res[7],res[8],res[9],res[10],res[11],res[12]);
        cust.add(ress);
    }
    void getTable(){
        resultTable.refresh();
        cust.forEach((res) -> {
            if(res.getRegisterNo().length()>3){
                data.add(new Result(res.getRegisterNo(),res.getSubjectOne(),res.getSubjectTwo(),res.getSubjectThree(),res.getSubjectFour(),res.getSubjectFive(),res.getSubjectSix(),res.getSubjectSeven(),res.getSubjectEight(),res.getSubjectNine(),res.getSubjectTen(),res.getSubjectEleven(),res.getSubjectTwelve()));
            }
        });
        regColumn.setCellValueFactory(new PropertyValueFactory<>("registerNo"));
        sub1Column.setCellValueFactory(new PropertyValueFactory<>("subjectOne"));
        sub2Column.setCellValueFactory(new PropertyValueFactory<>("subjectTwo"));
        sub3Column.setCellValueFactory(new PropertyValueFactory<>("subjectThree"));
        sub4Column.setCellValueFactory(new PropertyValueFactory<>("subjectFour"));
        sub5Column.setCellValueFactory(new PropertyValueFactory<>("subjectFive"));
        sub6Column.setCellValueFactory(new PropertyValueFactory<>("subjectSix"));
        sub7Column.setCellValueFactory(new PropertyValueFactory<>("subjectSeven"));
        sub8Column.setCellValueFactory(new PropertyValueFactory<>("subjectEight"));
        sub9Column.setCellValueFactory(new PropertyValueFactory<>("subjectNine"));
        sub10Column.setCellValueFactory(new PropertyValueFactory<>("subjectTen"));
        sub11Column.setCellValueFactory(new PropertyValueFactory<>("subjectEleven"));
        sub12Column.setCellValueFactory(new PropertyValueFactory<>("subjectTwelve"));

        resultTable.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sub1Column.setText(courses[0]);
        sub2Column.setText(courses[1]);
        sub3Column.setText(courses[2]);
        sub4Column.setText(courses[3]);
        sub5Column.setText(courses[4]);
        sub6Column.setText(courses[5]);
        sub7Column.setText(courses[6]);
        sub8Column.setText(courses[7]);
        sub9Column.setText(courses[8]);
        sub10Column.setText(courses[9]);
        sub11Column.setText(courses[10]);
        sub12Column.setText(courses[11]);
        indicator.setVisible(false);
    }    
    
    @FXML 
    private void closeApp(ActionEvent event ){
        System.exit(0);
    }
    @FXML
    private void analyse(ActionEvent event){
        System.out.println("Analyse");
        if(!fileLoaded){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Result PDF is not loaded");
            alert.setHeaderText(null);
            alert.setContentText("Please Load Result PDF file (File->Load Result PDF)");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void test(ActionEvent event){
        
    }

    private Task<Void> createTask() {
        int N_SECS=10;
        return new Task<Void>() {
            @Override public Void call() {
                    temp=file.getName();
                    begIndex=temp.indexOf("_");
                    endIndex=temp.indexOf('.');
                    temp=temp.substring(begIndex+1,endIndex);
                    try {
                        document=PDDocument.load(file);
                        pdfStripper=new PDFTextStripper();
                        String text= pdfStripper.getText(document);
                        
                        String collegeName = text.split("\n")[7];
                        
                        document.close();
                        getRegisterNumber(text,temp);
                        Platform.runLater(() -> {
                        label.setText("College Name :"+collegeName+"\nCollege Code : "+temp);
                        fileLoaded=true;
                        getTable();
                        });
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }     
                updateProgress(N_SECS, N_SECS);
                return null;
            }
        };
    }
    
}
