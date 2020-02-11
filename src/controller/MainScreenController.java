package controller;

/**
 * Controller Class for the Main Screen window.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Agenda;
import model.Course;
import model.Student;

import java.io.IOException;

public class MainScreenController {

    private Stage stage;
    private Agenda agenda;
    private Student currentStudent;
    private Course selectedCourse;

    {
        try {
            agenda = new Agenda();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        studentPhoto.setImage(new Image("https://www.sackettwaconia.com/wp-content/uploads/default-profile.png"));
        currentStudent = agenda.getContacts().get(0);
        selectedCourse = null;
        loadStudent();
    }

    @FXML // fx:id="studentPhoto"
    private ImageView studentPhoto; // Value injected by FXMLLoader

    @FXML // fx:id="studentLabel"
    private Label studentLabel; // Value injected by FXMLLoader

    @FXML // fx:id="informationTA"
    private TextArea informationTA; // Value injected by FXMLLoader

    @FXML // fx:id="coursesTV"
    private TableView<Course> coursesTV; // Value injected by FXMLLoader

    @FXML // fx:id="coursesTVColumn"
    private TableColumn coursesTVColumn; // Value injected by FXMLLoader

    @FXML // fx:id="creditsTVColumn"
    private TableColumn creditsTVColumn; // Value injected by FXMLLoader

    @FXML // fx:id="courseInfoTA"
    private TextArea courseInfoTA; // Value injected by FXMLLoader

    @FXML
    void addCourse() throws IOException {
        loadStage("../view/EditCourse.fxml", "New Course");
    }

    @FXML
    void addStudent() throws IOException {
        loadStage("../view/EditContact.fxml", "New Contact");
    }

    @FXML
    void deleteCurrentCourse() {
        currentStudent.getCourses().remove(selectedCourse);
        loadStudent();
    }

    @FXML
    void deleteCurrentStudent() {
        int indexOfStudent = agenda.getContacts().indexOf(currentStudent);
        if (indexOfStudent == agenda.getContacts().size() - 1) {
            currentStudent = agenda.getContacts().get(0);
        } else {
            currentStudent = agenda.getContacts().get(indexOfStudent + 1);
        }
        agenda.deleteContact(indexOfStudent);
        loadStudent();
    }

    @FXML
    void editCourse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditCourse.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Edit Course");
        EditCourseController editCourseCtrl = fxmlLoader.getController();
        editCourseCtrl.courseNameLabel.setText(coursesTV.getSelectionModel().getSelectedItem().toString());//TODO: check if works
        editCourseCtrl.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editStudent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditContact.fxml"));
        Parent root = fxmlLoader.load();
        EditContactController controller = fxmlLoader.getController();
        controller.setStudent(currentStudent);
        controller.setup(this);
        controller.setAgenda(agenda);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Edit Contact");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getNextStudent() {
        int currentIndex = agenda.getContacts().indexOf(currentStudent);
        if (currentIndex == agenda.getContacts().size() - 1) {
            currentStudent = agenda.getContacts().get(0);
        } else {
            currentStudent = agenda.getContacts().get(currentIndex + 1);
        }
        loadStudent();
    }

    @FXML
    void getPrevStudent() {
        int currentIndex = agenda.getContacts().indexOf(currentStudent);
        if (currentIndex == 0) {
            currentStudent = agenda.getContacts().get(agenda.getContacts().size() - 1);
        } else {
            currentStudent = agenda.getContacts().get(currentIndex - 1);
        }
        loadStudent();
    }

    @FXML
    void loadReport() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Report.fxml"));
        Parent root = fxmlLoader.load();
        ReportController controller = fxmlLoader.getController();
        controller.setAgenda(agenda);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Report");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openCourseSearchWindow() throws IOException {
        loadStage("../view/SearchCourse.fxml", "Search Course");
    }

    @FXML
    void openStudentSearchWindow() throws IOException {
        loadStage("../view/SearchContact.fxml", "Search Contact");
    }

    private void loadStage(String path, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void showSelectedCourseInfo() {
        selectedCourse = coursesTV.getSelectionModel().getSelectedItem();
        String infoToAdd = "Course Name: ";
        infoToAdd += selectedCourse.getName() + "\n";
        infoToAdd += "Credits: ";
        infoToAdd += selectedCourse.getCredits() + "\n";
        infoToAdd += "NRC: ";
        infoToAdd += selectedCourse.getNRC() + "\n\n";
        infoToAdd += "Enrolled Students: " + "\n";
        for (Student student : selectedCourse.getStudents()
        ) {
            infoToAdd += student.getName() + "\n";
        }
        courseInfoTA.setText(infoToAdd);
    }

    @SuppressWarnings("unchecked")
    void loadStudent() {
        studentPhoto.setImage(new Image(currentStudent.getPictureURL()));
        studentLabel.setText(currentStudent.getName());
        String infoToAdd = "Name: ";
        infoToAdd += currentStudent.getName() + "\n";
        infoToAdd += "Phone: ";
        infoToAdd += currentStudent.phoneNumber() + "\n";
        infoToAdd += "E-mail: ";
        infoToAdd += currentStudent.getEmail() + "\n";
        infoToAdd += "Birthday: ";
        infoToAdd += currentStudent.getBirthdate() + "\n";
        infoToAdd += "Address: ";
        infoToAdd += currentStudent.getAddress() + "\n";
        infoToAdd += "Student ID: ";
        infoToAdd += currentStudent.getCode() + "\n";
        infoToAdd += "Program: ";
        infoToAdd += currentStudent.getCareer() + "\n";
        informationTA.setText(infoToAdd);

        coursesTV.getColumns().clear();
        coursesTV.getColumns().addAll(coursesTVColumn, creditsTVColumn);
        ObservableList<Course> observableCourses = FXCollections.observableArrayList();
        observableCourses.addAll(currentStudent.getCourses());
        coursesTVColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        creditsTVColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("credits"));

        coursesTV.setItems(observableCourses);
    }


    @FXML
    void saveToDatabase() throws IOException {
        agenda.outputContacts();
    }

}






