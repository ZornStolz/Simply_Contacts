package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agenda;
import model.Course;
import model.Student;

/**
 * Controller Class for the Search Contact window.
 */
public class SearchContactController {

    private Agenda agenda;
    private MainScreenController mainController;
    private Student student;

    @FXML // fx:id="infoTF"
    private TextField infoTF; // Value injected by FXMLLoader

    @FXML // fx:id="criteriaCB"
    private ComboBox<String> criteriaCB; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        criteriaCB.getItems().add("Name");
        criteriaCB.getItems().add("Phone Number");
        criteriaCB.getItems().add("E-mail");
        criteriaCB.getItems().add("Address");
        criteriaCB.getItems().add("Student ID");
    }

    @FXML
    void returnToMainScreen(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchContact(ActionEvent e) {
        if (infoTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Information field is empty", ButtonType.OK);
            alert.show();
        } else if (criteriaCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please specify a search criteria", ButtonType.OK);
            alert.show();
        } else {
            String criteria = criteriaCB.getSelectionModel().getSelectedItem();
            Student toFind = null;
            for (Student student : agenda.getContacts()
            ) {
                switch (criteria) {
                    case "Name":
                        if (student.getName().contains(infoTF.getText())) {
                            toFind = student;
                        }
                        break;
                    case "Phone Number":
                        if (student.phoneNumber().contains(infoTF.getText())) {
                            toFind = student;
                        }
                        break;
                    case "E-mail":
                        if (student.getEmail().contains(infoTF.getText())) {
                            toFind = student;
                        }
                        break;
                    case "Address":
                        if (student.getAddress().contains(infoTF.getText())) {
                            toFind = student;
                        }
                        break;
                    case "Student ID":
                        if (student.getCode().contains(infoTF.getText())) {
                            toFind = student;
                        }
                        break;
                }
            }
            if (toFind == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No contact found", ButtonType.OK);
                alert.show();
            } else {
                mainController.setCurrentStudent(toFind);
                mainController.loadStudent();
                returnToMainScreen(e);
            }
        }
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public void setup(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
