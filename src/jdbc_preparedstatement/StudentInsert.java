package jdbc_preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsert {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.println("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.println("Enter Student Address: ");
        String address = sc.nextLine();

        System.out.println("Enter Student Contact Number: ");
        long contact = sc.nextLong();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "root";

        String query = "INSERT INTO student (id, name, address, contact) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setLong(4, contact);

            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Data inserted successfully");
            } else {
                System.out.println("Data is not inserted!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
