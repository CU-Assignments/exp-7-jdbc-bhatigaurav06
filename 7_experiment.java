//easy form
import java.sql.*;

public class EasyJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM Employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(empId + " | " + name + " | " + salary);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


// MEDIUM form

import java.sql.*;
import java.util.Scanner;

public class MediumJDBC {
    static Connection conn;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Insert\n2. Read\n3. Update\n4. Delete\n5. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> insertProduct(sc);
                    case 2 -> readProducts();
                    case 3 -> updateProduct(sc);
                    case 4 -> deleteProduct(sc);
                    case 5 -> {
                        conn.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertProduct(Scanner sc) throws SQLException {
        System.out.print("Enter ProductID, Name, Price, Quantity: ");
        int id = sc.nextInt();
        String name = sc.next();
        double price = sc.nextDouble();
        int quantity = sc.nextInt();

        String query = "INSERT INTO Product VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setDouble(3, price);
        ps.setInt(4, quantity);
        ps.executeUpdate();
        System.out.println("Product added.");
    }

    static void readProducts() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " +
                    rs.getDouble(3) + " | " + rs.getInt(4));
        }
        rs.close();
        stmt.close();
    }

    static void updateProduct(Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to update: ");
        int id = sc.nextInt();

        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();

        String query = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDouble(1, price);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Product updated.");
    }

    static void deleteProduct(Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Product deleted.");
    }
}


// HARD form

import java.sql.*;
import java.util.Scanner;

public class StudentManagement {
    static Connection conn;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add Student\n2. Display Students\n3. Update Marks\n4. Delete Student\n5. Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addStudent(sc);
                    case 2 -> displayStudents();
                    case 3 -> updateStudent(sc);
                    case 4 -> deleteStudent(sc);
                    case 5 -> {
                        conn.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addStudent(Scanner sc) throws SQLException {
        System.out.print("Enter ID, Name, Department, Marks: ");
        int id = sc.nextInt();
        String name = sc.next();
        String department = sc.next();
        int marks = sc.nextInt();

        String query = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, department);
        ps.setInt(4, marks);
        ps.executeUpdate();
        System.out.println("Student added successfully.");
    }

    static void displayStudents() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " +
                               rs.getString(3) + " | " + rs.getInt(4));
        }
        rs.close();
    }

    static void updateStudent(Scanner sc) throws SQLException {
        System.out.print("Enter ID and new Marks: ");
        int id = sc.nextInt();
        int marks = sc.nextInt();

        String query = "UPDATE Student SET Marks = ? WHERE StudentID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, marks);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Student updated successfully.");
    }

    static void deleteStudent(Scanner sc) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM Student WHERE StudentID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Student deleted successfully.");
    }
}

