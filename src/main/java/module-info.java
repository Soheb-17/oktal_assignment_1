module com.example.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.sql.rowset;


    opens com.example.assignment to javafx.fxml;
    exports com.example.assignment;

    opens com.example.assignment.Model to javafx.fxml;
    exports com.example.assignment.Model;

    opens com.example.assignment.Controller to javafx.fxml;
    exports com.example.assignment.Controller;

}