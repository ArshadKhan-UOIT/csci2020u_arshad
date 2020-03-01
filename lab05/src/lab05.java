//Arshad Khan 100695721
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.layout.GridPane;
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
import java.util.*;


public class lab05 extends Application {
    TableView<StudentRecord> table;
    TextField SIDInput, AsmtInput, MidInput, ExamInput;
    Button addButton, deleteButton;
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Lab 05 Solutions");

        //SID col
        TableColumn<StudentRecord, String> SIDCol = new TableColumn<>("SID");
        SIDCol.setMinWidth(200);
        SIDCol.setCellValueFactory(new PropertyValueFactory<>("SID"));

        //Asmt col
        TableColumn<StudentRecord, Float> AsmtCol = new TableColumn<>("Assignments");
        AsmtCol.setMinWidth(100);
        AsmtCol.setCellValueFactory(new PropertyValueFactory<>("Asmt"));

        //Midterm col
        TableColumn<StudentRecord, Float> MidCol = new TableColumn<>("Midterm");
        MidCol.setMinWidth(100);
        MidCol.setCellValueFactory(new PropertyValueFactory<>("Mid"));

        //Exam col
        TableColumn<StudentRecord, Float> ExamCol = new TableColumn<>("Final Exam");
        ExamCol.setMinWidth(100);
        ExamCol.setCellValueFactory(new PropertyValueFactory<>("Exam"));

        //Final Mark col
        TableColumn<StudentRecord, Float> FinalCol = new TableColumn<>("Final Mark");
        FinalCol.setMinWidth(100);
        FinalCol.setCellValueFactory(new PropertyValueFactory<>("Final"));

        //Letter grade  col
        TableColumn<StudentRecord, String> GradeCol = new TableColumn<>("Letter Grade");
        GradeCol.setMinWidth(100);
        GradeCol.setCellValueFactory(new PropertyValueFactory<>("Grade"));

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

        //Button
        addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

//        HBox hBox =  new HBox();
//        hBox.setPadding(new Insets(10,10,10,10));
//        hBox.setSpacing(10);
//        hBox.getChildren().addAll(SIDInput, AsmtInput, MidInput, ExamInput, addButton, deleteButton);
//        Text Note = new Text("Select a row then press 'Delete' to delete that row");

        //grid pane
        GridPane gridpane = new GridPane();

        //size for plane
        gridpane.setMinSize(400, 220);

        //setting padding
        gridpane.setPadding(new Insets(10, 10, 10, 10));

        //vertical and horizontal gaps between columns
        gridpane.setVgap(10);
        gridpane.setHgap(10);

        //grid alignment
        gridpane.setAlignment(Pos.TOP_LEFT);
        //arranging everything in grid

        //SID input
        gridpane.add(SIDInput, 0, 0);

        //Assignment input
        gridpane.add(AsmtInput, 1, 0);

        //Mid input
        gridpane.add(MidInput, 0, 1);

        //Exam input
        gridpane.add(ExamInput, 1, 1);

        //Note
//        gridpane.add(Note, 2, 1);

        //add button
        gridpane.add(addButton, 0, 2);

        //delete button
        gridpane.add(deleteButton, 2, 2);

        table = new TableView<>();
        table.setItems(getAllStudents());
        table.getColumns().addAll(SIDCol, AsmtCol, MidCol, ExamCol, FinalCol, GradeCol);

        VBox vBox = new VBox();
//        vBox.getChildren().addAll(table, hBox);
        vBox.getChildren().addAll(table, gridpane);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
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
//        System.out.println(recordsSelected);
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
//        box.getChildren().add(Note);
        box.getChildren().add(conform);
        Scene scene = new Scene(box, 250, 150);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<StudentRecord> getAllStudents() {
        //SID, Assignments, Midterm, Final Exam, Final Mark, Letter Grade
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));
        return marks;
    }
}

