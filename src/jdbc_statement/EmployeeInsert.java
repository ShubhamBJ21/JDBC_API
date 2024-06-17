package jdbc_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeeInsert {

	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp", "root", "root");
		
		Statement statement = connection.createStatement();
		
		int result = statement.executeUpdate("Insert into emp values (3, 'chaitanya', 'nagar', 8888537883)");
		
		if(result != 0) {
			System.out.println("Data is inserted");
		}else {
			System.out.println("Data is not inserted");
		}
		
		connection.close();
	}
}
