package jdbc_preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		admin();
	}

	static Connection connection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/student";
		String username = "root";
		String password = "root";

		Connection connection = DriverManager.getConnection(url, username, password);

		return connection;
	}

	static void admin() throws Exception {

		boolean repeat = true;
		while(repeat) {
			System.out.println("--------------------------------");
			System.out.println("1. Display Student Details");
			System.out.println("2. Add Student");
			System.out.println("3. Update Student Name");
			System.out.println("4. Delete Student");
			System.out.println("5. Exit");
			System.out.println("--------------------------------");
			
			System.out.println("Choose Appropriate option: ");
			int option = sc.nextInt();
			sc.nextLine();

			Connection connection = connection();

			switch (option) {
			case 1: {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select * from student");

				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Name: " + resultSet.getString("name"));
					System.out.println("Address: " + resultSet.getString("address"));
					System.out.println("Phone: " + resultSet.getLong("contact"));
					System.out.println();
				}
				break;

			}
			case 2: {
				String query = "Insert into student values(?,?,?,?)";

				System.out.println("Enter Student ID: ");
				int id = sc.nextInt();
				sc.nextLine(); // Consume newline

				System.out.println("Enter Student Name: ");
				String name = sc.nextLine();

				System.out.println("Enter Student Address: ");
				String address = sc.nextLine();

				System.out.println("Enter Student Contact Number: ");
				long contact = sc.nextLong();

				PreparedStatement preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, address);
				preparedStatement.setLong(4, contact);

				int result = preparedStatement.executeUpdate();

				if (result != 0) {
					System.out.println("Data inserted successfully");
				} else {
					System.out.println("Data is not inserted");
				}
				break;
			}
			case 3: {
				String query = "Update emp set name = ? where id = ?";

				System.out.println("Enter Student id: ");
				int id = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter Student name: ");
				String name = sc.nextLine();

				PreparedStatement preparedStatement = connection.prepareStatement(query);

				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);

				int result = preparedStatement.executeUpdate();

				if (result != 0) {
					System.out.println("Data updated successfully");
				} else {
					System.out.println("Data is not updated");
				}
				break;
			}

			case 4: {
				String query = "Delete from student where id = ?";

				System.out.println("Enter Student id: ");
				int id = sc.nextInt();
				sc.nextLine();

				PreparedStatement preparedStatement = connection.prepareStatement(query);

				preparedStatement.setInt(2, id);
				
				int result = preparedStatement.executeUpdate();
				
				if(result != 0) {
					System.out.println("Data deleted successfully");
				}else {
					System.out.println("Data is not deleted");
				}
				break;
			}
			
			case 5:{
				repeat = false;
				System.out.println("Thank You!..");
				return;
			}
			
			default:
				System.out.println("Please choose appropriate option");
			}

			connection.close();
		}
	}
}
