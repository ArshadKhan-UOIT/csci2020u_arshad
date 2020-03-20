//Arshad Khan 100695721
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.util.List;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



public class lab08 extends Application {
//    public StudentRecord[] records;
//    public lab08(StudentRecord[] records) {
//        this.records = records;
//    }
    TableView<StudentRecord> table;
    TableColumn<StudentRecord, String> SIDCol, GradeCol;
    TableColumn<StudentRecord, Float> AsmtCol, MidCol, ExamCol, FinalCol;
    TextField SIDInput, AsmtInput, MidInput, ExamInput;
    Button addButton, deleteButton;
    MenuBar menuBar;
    Menu menu;
    MenuItem newMI, openMI, saveMI, saveAsMI, exitMI;
    GridPane gridpane;
    BorderPane bPane;
    File file;
    FileChooser fileChooser;
    public void start(Stage primaryStage) throws Exception {
        file = new File("records.csv");
//        System.out.println(String.valueOf(file));
        List<String[]> data = read(String.valueOf(file), 4);    //first file is in /home/arshad/Desktop/csci2020u/lab08
        StudentRecord[] records = new StudentRecord[data.size()];
        for (int i = 0; i < data.size(); i++) {
            records[i] = new StudentRecord(data.get(i));
        }
        bPane = new BorderPane();
        gridpane = new GridPane();
        menuBar = new MenuBar();
        menu = new Menu("File");
        getFileMenuItems();
        menuBar.getMenus().addAll(menu);
        getTableColumns();
        primaryStage.setTitle("Lab 08 Solution");
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        //vertical and horizontal gaps between columns
        gridpane.setVgap(10);
        gridpane.setHgap(10);
        //grid alignment
        gridpane.setAlignment(Pos.TOP_LEFT);
        getRowInput();
        getButtons();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,gridpane);
        bPane.setTop(menuBar);
        bPane.setBottom(vBox);
        Scene scene = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void getButtons() {
        //Button
        addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        //add button
        gridpane.add(addButton, 2, 1);
        //delete button
        gridpane.add(deleteButton, 2, 2);
    }
    private void getRowInput() {
        //SID input
        SIDInput = new TextField();
        SIDInput.setPromptText("SID");
        SIDInput.setMinWidth(100);
        //Asmt input
        AsmtInput = new TextField();
        AsmtInput.setPromptText("Assignment/100");
        AsmtInput.setMinWidth(100);
        //Mid input
        MidInput = new TextField();
        MidInput.setPromptText("Midterm/100");
        MidInput.setMinWidth(100);
        //Exam input
        ExamInput = new TextField();
        ExamInput.setPromptText("Exam/100");
        ExamInput.setMinWidth(100);
        //arranging everything in grid
        //SID input
        gridpane.add(SIDInput, 0, 0);
        //Assignment input
        gridpane.add(AsmtInput, 1, 0);
        //Mid input
        gridpane.add(MidInput, 2, 0);
        //Exam input
        gridpane.add(ExamInput, 3, 0);
    }
    private void getTableColumns() {
        //SID col
        SIDCol = new TableColumn<>("SID");
        SIDCol.setMinWidth(200);
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        //Asmt col
        AsmtCol = new TableColumn<>("Assignments");
        AsmtCol.setMinWidth(100);
        AsmtCol.setCellValueFactory(new PropertyValueFactory<>("Asmt"));
        //Midterm col
        MidCol = new TableColumn<>("Midterm");
        MidCol.setMinWidth(100);
        MidCol.setCellValueFactory(new PropertyValueFactory<>("Mid"));
        //Exam col
        ExamCol = new TableColumn<>("Final Exam");
        ExamCol.setMinWidth(100);
        ExamCol.setCellValueFactory(new PropertyValueFactory<>("Exam"));
        //Final Mark col
        FinalCol = new TableColumn<>("Final Mark");
        FinalCol.setMinWidth(100);
        FinalCol.setCellValueFactory(new PropertyValueFactory<>("Final"));
        //Letter grade  col
        GradeCol = new TableColumn<>("Letter Grade");
        GradeCol.setMinWidth(100);
        GradeCol.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        //tableview
        table = new TableView<>();
        table.setItems(getAllStudents());
        table.getColumns().addAll(SIDCol, AsmtCol, MidCol, ExamCol, FinalCol, GradeCol);
    }
    private void getFileMenuItems() {
        newMI =  new MenuItem("New");
        newMI.setOnAction(e -> { table.getItems().clear(); });
        openMI = new MenuItem("Open");
        openMI.setOnAction(e -> { open(); });
        saveMI = new MenuItem("Save");
        saveMI.setOnAction(e -> save());
        saveAsMI = new MenuItem("Save As");
        saveAsMI.setOnAction(e -> saveAs());
        exitMI = new MenuItem("Exit");
        exitMI.setOnAction(e -> { System.exit(0); });
        menu.getItems().addAll(newMI,openMI,saveMI,saveAsMI,exitMI);
    }
    public void open() {
        Stage fileWindow = new Stage();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(fileWindow);
        load(String.valueOf(selectedFile));
    }
    public void saveAs() {
        Stage fileWindow = new Stage();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"));
        file = fileChooser.showSaveDialog(fileWindow);
        save();
    }
    //add button when clicked
    private void addButtonClicked() {
        String newSID = SIDInput.getText();
        float newAsmt = Float.parseFloat(AsmtInput.getText());
        float newMid = Float.parseFloat(MidInput.getText());
        float newExam = Float.parseFloat(ExamInput.getText());
        StudentRecord studentRecord = new StudentRecord(newSID, newAsmt, newMid, newExam);
        studentRecord.getFinal();
        studentRecord.getGrade();
        table.getItems().add(studentRecord);
        SIDInput.clear();
        AsmtInput.clear();
        MidInput.clear();
        ExamInput.clear();
    }
    private void deleteButtonClicked() {
        ObservableList<StudentRecord> recordsSelected, allRecords;
        allRecords = table.getItems();
        recordsSelected = table.getSelectionModel().getSelectedItems();
        recordsSelected.forEach(allRecords::remove);
        boolean checkRecordsSelected = recordsSelected.isEmpty();
        if (checkRecordsSelected == true) {
            rowNotSelected();
        }
    }
    private void rowNotSelected() {
        Stage stage = new Stage();

        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        stage.setTitle("Error!");
        // How to center align content in a layout manager in JavaFX
        box.setAlignment(Pos.CENTER);
        Label label = new Label("Select a row then press 'Delete' \n\tto delete that row");

        Button conform = new Button();
        conform.setText("OK");

        conform.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.close(); // return to main window
            }
        });
        box.getChildren().add(label);
        box.getChildren().add(conform);
        Scene scene = new Scene(box, 250, 150);
        stage.setScene(scene);
        stage.show();
    }
    public void save() {    //saves to directory of lab08 folder
        try {
            PrintWriter pw = new PrintWriter(file);
            String data;
            for (int i=0; i < table.getItems().size(); i++) {
                data = "";
                data += SIDCol.getCellData(i) + ",";
                data += AsmtCol.getCellData(i) + ",";
                data += MidCol.getCellData(i) + ",";
                data += ExamCol.getCellData(i) + "\n";
                pw.write(data);
            }
            pw.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void load(String selectedFile) {
        table.getItems().clear();
        table.setItems(getAllStudents(selectedFile));
    }
    public ObservableList<StudentRecord> getAllStudents(String selectedFile) {
        List<String[]> data = read(selectedFile, 4);
        StudentRecord[] records = new StudentRecord[data.size()];
        for (int i = 0; i < data.size(); i++) {
            records[i] = new StudentRecord(data.get(i));
        }
        ObservableList<StudentRecord> ObList = FXCollections.observableArrayList();
        for (StudentRecord s: records) {//String SID, float Asmt, float Mid, float Exam
            ObList.add(new StudentRecord(s.getSID(),s.getAsmt(),s.getMid(),s.getExam()));
        }
        return ObList;
    }
    public ObservableList<StudentRecord> getAllStudents() {
        List<String[]> data = read(String.valueOf(file), 4);
        StudentRecord[] records = new StudentRecord[data.size()];
        for (int i = 0; i < data.size(); i++) {
            records[i] = new StudentRecord(data.get(i));
        }
        ObservableList<StudentRecord> ObList = FXCollections.observableArrayList();
        for (StudentRecord s: records) {//String SID, float Asmt, float Mid, float Exam
            ObList.add(new StudentRecord(s.getSID(),s.getAsmt(),s.getMid(),s.getExam()));
        }
        return ObList;
    }
    public static List<String[]> read(String fileName, int size) {
        List<String[]> data = null;
        try {
            Reader read = Files.newBufferedReader(Paths.get(fileName));
            data = new ArrayList<>();
            String[] str;
            Iterable<CSVRecord> info = CSVFormat.DEFAULT.parse(read);
            for (CSVRecord record : info) {
                str = new String[size];
                for (int i = 0; i < size; i++) {
                    str[i] = record.get(i);
                }
                data.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

