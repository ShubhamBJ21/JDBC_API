package jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDisplay {

	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "root");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery("Select * from emp");
		
		while(resultset.next()) {
			System.out.println("Id: "+resultset.getString("id"));
			System.out.println("Name: "+resultset.getString("name"));
			System.out.println("Address: "+resultset.getString("address"));
			System.out.println("Phone: "+resultset.getString("phone"));
			System.out.println();
		}
	}
}