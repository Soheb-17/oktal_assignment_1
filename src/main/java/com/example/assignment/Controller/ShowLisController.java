package com.example.assignment.Controller;

import com.example.assignment.Model.Employee;
import com.example.assignment.Model.EmployeeDAO;
import com.example.assignment.Util.DButil;
import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class ShowLisController implements Initializable {

    @FXML
    private TableView<Employee> EmployeeTable;
    @FXML
    private TableColumn<Employee, String> first_Name;
    @FXML
    private TableColumn<Employee, String> last_Name;
    @FXML
    private TableColumn<Employee, String> dateof_Birth;
    @FXML
    private TableColumn<Employee, String> joining_Date;

    int increment = 0;
    int inc_value = 8;

    ObservableList<Employee> list = FXCollections.observableArrayList();

    @FXML
    Button homePage,next,prev;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homePage.setGraphic(ImageButton("home.png"));
        next.setGraphic(ImageButton("right-arrow.png"));
        prev.setGraphic(ImageButton("left_arrow.png"));

        try {
            PopulateList();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        first_Name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        last_Name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateof_Birth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        joining_Date.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));
        EmployeeTable.setItems(list);
    }

    public void BackToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void NextData() throws SQLException, ClassNotFoundException {
        increment = increment + inc_value;
        PopulateList();
    }

    public void PrevData() throws SQLException, ClassNotFoundException {
        if(increment > (inc_value-1)){
            increment = increment - inc_value;
            PopulateList();
        }
    }

    public void PopulateList() throws SQLException, ClassNotFoundException {
        list.clear();
        String inc = Integer.toString(increment);
        String staticVal = Integer.toString(inc_value);
        ResultSet rs = DButil.RetriveData("SELECT * FROM employee.personal_data ORDER BY employe_id DESC LIMIT "+ inc +","+staticVal+";");
        System.out.println("Result set : " + rs);
        while (rs.next()){
            list.add(new Employee(rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("dob"),
                    rs.getString("joining_date")));
        }
    }

    public ImageView ImageButton(String image){
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().
                getResource("/com/example/assignment/" + image)).toExternalForm());
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        return imageView;
    }
}

