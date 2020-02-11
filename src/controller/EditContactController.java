package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Agenda;
import model.Course;
import model.Student;

import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Controller Class for the Edit Contact window.
 */
public class EditContactController {

    private Stage stage;
    private Student student;
    private Agenda agenda;
    private MainScreenController mainController;

    @FXML // fx:id="contactNameLabel"
    private Label contactNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="displayPhoto"
    private ImageView displayPhoto; // Value injected by FXMLLoader

    @FXML // fx:id="nameTF"
    private TextField nameTF; // Value injected by FXMLLoader

    @FXML // fx:id="phoneTF"
    private TextField phoneTF; // Value injected by FXMLLoader

    @FXML // fx:id="careerCB"
    private TextField careerTF; // Value injected by FXMLLoader

    @FXML // fx:id="emailTF"
    private TextField emailTF; // Value injected by FXMLLoader

    @FXML // fx:id="dobTF"
    private TextField dobTF; // Value injected by FXMLLoader

    @FXML // fx:id="addressTF"
    private TextField addressTF; // Value injected by FXMLLoader

    @FXML // fx:id="idTF"
    private TextField idTF; // Value injected by FXMLLoader

    @FXML // fx:id="semesterTF"
    private TextField semesterTF; // Value injected by FXMLLoader

    @FXML // fx:id="photoURL_TF"
    private TextField photoURL_TF; // Value injected by FXMLLoader

    @FXML // fx:id="nrcTF"
    private TextField nrcTF; // Value injected by FXMLLoader

    @FXML
    void returnToMainScreen(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SearchContact.fxml"));
//        Parent root = fxmlLoader.load();
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setTitle("Search Contact");
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void saveStudent(ActionEvent e) throws Exception {
        Student studentToAdd = new Student(contactNameLabel.getText(), phoneTF.getText(), emailTF.getText(),
                dobTF.getText(), addressTF.getText(), idTF.getText(), semesterTF.getText(), careerTF.getText()
                , photoURL_TF.getText());

        if (!nrcTF.getText().isEmpty()) {
            studentToAdd.getCourses().clear();
            String[] nrc = nrcTF.getText().split(",");
            for (int i = 0; i < nrc.length; i++) {
                Course toAdd = agenda.searchCourseNrc(nrc[i]);
                studentToAdd.getCourses().add(toAdd);
            }
        }

        student = studentToAdd;

        returnToMainScreen(e);
        mainController.loadStudent();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setup(MainScreenController mainController) {
        this.mainController = mainController;
        if (student == null) {
            displayPhoto.setImage(new Image("https://www.sackettwaconia.com/wp-content/uploads/default-profile.png"));
        } else {
            displayPhoto.setImage(new Image(student.getPictureURL()));
            contactNameLabel.setText(student.getName());
            nameTF.setText(student.getName());
            phoneTF.setText(student.phoneNumber());
            emailTF.setText(student.getEmail());
            dobTF.setText(student.getBirthdate());
            addressTF.setText(student.getAddress());
            idTF.setText(student.getCode());
            careerTF.setText(student.getCareer());
            photoURL_TF.setText(student.getPictureURL());
            String toAdd = "";
            for (Course course : student.getCourses()
            ) {
                toAdd += course.getNRC() + ",";
            }
            toAdd = toAdd.substring(0, toAdd.length() - 1);
            nrcTF.setText(toAdd);
        }

    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}

