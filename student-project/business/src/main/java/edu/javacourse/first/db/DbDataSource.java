package edu.javacourse.first.db;

import edu.javacourse.first.domain.StudentOrder;

import java.sql.*;
import java.util.List;

public class DbDataSource implements StudentOrderDataSource {

    static {
        try {
            Class.forName("org.postgresql.Driver");
//            System.out.println("JDBC is loaded");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        String login = "postgres";
        String pswd = "postgres";
        String url = "jdbc:postgresql://127.0.0.1:5432/java-code";
        return DriverManager.getConnection(url, login, pswd);
    }

    @Override
    public Long addStudentOrder(StudentOrder so) {
        try (Connection con = getConnection()) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.prepareStatement(
                        "insert into st_group (groupName, curator, speciality) Values (?,?,?)");
                stmt.setString(1, "Четвертая группа");
                stmt.setString(2, "Куратор Чебурашка");
                stmt.setString(3, "Четвертая специальность");
                stmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null) stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentOrder> getStudentOrders() {
        try (Connection con = getConnection()) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = con.prepareStatement("select * from st_group");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    long groupId = rs.getLong("group_id");
                    String groupName = rs.getString("groupName");
                    String curator = rs.getString("curator");
                    String speciality = rs.getString("speciality");
                    System.out.println(groupId + ":" + groupName + ":" + curator + ":" + speciality);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
