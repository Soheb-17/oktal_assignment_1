package com.example.assignment;

import com.example.assignment.Model.EmployeeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddNewController implements Initializable {

    static Connection connection;
    static String databaseName = "";
    static String url = "jdbc:mysql://localhost:3307/" + databaseName;
    static String username = "root";
    static String password = "Sohaib@1716";
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    TextField firstName, lastName;
    @FXML
    DatePicker dob, joiningDate;
    @FXML
    TextArea resultDisplay;

    @FXML
    Button homePage;

    public void AddToDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        String f_name = firstName.getText();
        String l_name = lastName.getText();
        String dateob = dob.getEditor().getText();
        String joining = joiningDate.getEditor().getText();

        System.out.println("First_name    : " + f_name);
        System.out.println("Last_name     : " + l_name);
        System.out.println("Date of Birth : " + dateob);
        System.out.println("Joining Date  : " + joining);

        String borderColor = "-fx-border-color:#fc5a5a ; -fx-border-width: 2px ;";

        if(f_name.isEmpty()){
            firstName.setStyle(borderColor);
        }else if(f_name.matches(".*[0-9].*")){
            firstName.setStyle(borderColor);
        }else{
            firstName.setStyle(null);
        }

        if(l_name.isEmpty()){
            lastName.setStyle(borderColor);
        }else if(l_name.matches(".*[0-9].*")){
            lastName.setStyle(borderColor);
        }else {
            lastName.setStyle(null);
        }

        if(dateob.isEmpty()){
            dob.setStyle(borderColor);
        }else{
            dob.setStyle(null);
        }

        if(joining.isEmpty()){
            joiningDate.setStyle(borderColor);
        }else{
            joiningDate.setStyle(null);
        }

        if (f_name.isEmpty() || l_name.isEmpty() || dateob.isEmpty() || joining.isEmpty()) {
            resultDisplay.setText("Error \nFields cannot be empty");
            resultDisplay.setStyle("-fx-control-inner-background: #fc5a5a");

        } else {
            if(f_name.matches(".*[0-9].*") || l_name.matches(".*[0-9].*")){
                resultDisplay.setText("Warning \nName cannot contain numeric values \nplease fill the form again with proper details");
                resultDisplay.setStyle("-fx-control-inner-background: orange");
            }else{
                resultDisplay.setText("Success \nData added to database successfully");
                resultDisplay.setStyle("-fx-control-inner-background: green");
                EmployeeDAO.insertEmploye(f_name, l_name, dateob, joining);
                ClearData();
            }
        }

    }

    public void ReturnToHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ClearData() {
        firstName.clear();
        lastName.clear();
        dob.getEditor().clear();
        joiningDate.getEditor().clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().
                getResource("/com/example/assignment/home.png")).toExternalForm());
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        homePage.setGraphic(imageView);
//        homePage.setContentDisplay(ContentDisplay.TOP);
    }
}
