package jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeeUpdate {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "root");
		
		Statement statement = connection.createStatement();
		
		int result = statement.executeUpdate("Update emp set name = 'SHUBHAM' where id = 1");

		if(result!=0) {
			System.out.println("Data updated successfully");
		}
		else {
			System.out.println("Data is not updated");
		}
		
		connection.close();
	}
}