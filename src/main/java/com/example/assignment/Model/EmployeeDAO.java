package com.example.assignment.Model;

import com.example.assignment.Util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public static void insertEmploye(String firstName, String lastName, String dateob, String joining) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `employee`.`personal_data` (`first_name`, `last_name`, `dob`, `joining_date`, `personal_datacol`) VALUES ('"+firstName+"', '"+lastName+"', '"+dateob+"', '"+joining+"', 'null');";
        try {
            DButil.dbExecuteQuery(sql);
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Exception occured running the query");
            e.printStackTrace();
            throw e;
        }
    }


}
