package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Agenda;
import model.Course;
import model.Student;

import java.io.IOException;

public class ReportController {

    private Stage stage;
    private Agenda agenda;
    private Course current;

    @FXML // fx:id="avgCourses_Student_Label"
    private Label avgCourses_Student_Label; // Value injected by FXMLLoader

    @FXML // fx:id="avgCredits_Student_Label"
    private Label avgCredits_Student_Label; // Value injected by FXMLLoader

    @FXML // fx:id="mostCourseLabel"
    private Label mostCourseLabel; // Value injected by FXMLLoader

    @FXML // fx:id="leastCourseLabel"
    private Label leastCourseLabel; // Value injected by FXMLLoader

    @FXML // fx:id="coursesTV"
    private TableView<Course> coursesTV; // Value injected by FXMLLoader

    @FXML // fx:id="coursesTVColumn"
    private TableColumn coursesTVColumn; // Value injected by FXMLLoader

    @FXML // fx:id="courseInfoTA"
    private TextArea courseInfoTA; // Value injected by FXMLLoader

    @FXML
    void returnToMainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MainScreen.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Simply Contacts");
        EditCourseController editCourseCtrl = fxmlLoader.getController();
        editCourseCtrl.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }
    
    @SuppressWarnings("unchecked")
	private void load() {
    	
    	avgCourses_Student_Label.setText(Integer.toString(agenda.assignedCoursesAverage()));
    	avgCredits_Student_Label.setText(Integer.toString(agenda.assignedCreditsAverage()));
    	mostCourseLabel.setText(agenda.mostAssignedCourse().getName());
    	leastCourseLabel.setText(agenda.leastAssignedCourse().getName());
    
    	coursesTV.getColumns().clear();
        coursesTV.getColumns().add(coursesTVColumn);
        ObservableList<Course> observableCourses = FXCollections.observableArrayList();
        observableCourses.addAll(agenda.getCourses());
        coursesTVColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        coursesTV.setItems(observableCourses);
    }
    
    @FXML
    void showSelectedCourseInfo() {
        current = coursesTV.getSelectionModel().getSelectedItem();
        String infoToAdd = "Course Name: ";
        infoToAdd += current.getName() + "\n";
        infoToAdd += "Credits: ";
        infoToAdd += current.getCredits() + "\n";
        infoToAdd += "NRC: ";
        infoToAdd += current.getNRC() + "\n\n";
        infoToAdd += "Enrolled Students: " + "\n";
        for (Student student : current.getStudents()
        ) {
            infoToAdd += student.getName() + "\n";
        }
        courseInfoTA.setText(infoToAdd);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
        load();
    }
}
