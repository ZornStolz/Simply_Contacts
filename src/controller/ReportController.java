package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private TableView coursesTV; // Value injected by FXMLLoader

    @FXML // fx:id="coursesTVColumn"
    private TableColumn coursesTVColumn; // Value injected by FXMLLoader

    @FXML // fx:id="courseInfoTA"
    private TextArea courseInfoTA; // Value injected by FXMLLoader

    @FXML
    void returnToMainMenu(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @SuppressWarnings("unchecked")
    private void load() {

        avgCourses_Student_Label.setText(Integer.toString(agenda.assignedCoursesAverage()));
        avgCredits_Student_Label.setText(Integer.toString(agenda.assignedCreditsAverage()));
        Course most = agenda.mostAssignedCourse();
        Course least = agenda.leastAssignedCourse();
        mostCourseLabel.setText("(" + most.getStudents().size() + ") " + most.getName());
        leastCourseLabel.setText("(" + least.getStudents().size() + ") " + least.getName());

        coursesTV.getColumns().clear();
        coursesTV.getColumns().add(coursesTVColumn);
        ObservableList<Course> observableCourses = FXCollections.observableArrayList();
        observableCourses.addAll(agenda.getCourses());
        coursesTVColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        coursesTV.setItems(observableCourses);
    }

    @FXML
    void showSelectedCourseInfo() {
        if (coursesTV.getSelectionModel().getSelectedItem() != null) {
            current = (Course) coursesTV.getSelectionModel().getSelectedItem();
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
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
        load();
    }
}
