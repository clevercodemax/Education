package edu.javacourse.first.db;

import edu.javacourse.first.domain.PersonChild;
import edu.javacourse.first.domain.StudentOrder;

import java.sql.*;
import java.util.List;

public class DbDataSource implements StudentOrderDataSource {

    private static final String INSERT_ORDER = "INSERT INTO so_student_order (" +
            "student_order_date, " +
            "h_surname, h_givenname, h_patronymic, h_date_of_birth, h_passport_seria, h_passport_number, h_date_issue, h_date_expire, " +
            "w_surname, w_givenname, w_patronymic, w_date_of_birth, w_passport_seria, w_passport_number, w_date_issue, w_date_expire) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_CHILD = "INSERT INTO so_student_order_child (" +
            "student_order_id, c_surname, c_givenname, c_patronymic, c_date_of_birth, c_birth_document) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    static {
        try {
            Class.forName("org.postgresql.Driver");
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
            con.setAutoCommit(false);
            PreparedStatement stmt = null;
            PreparedStatement stmt2 = null;
            try {
                stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"});
                prepareParametersAdult(stmt, so);
                stmt.executeUpdate();

                ResultSet grs = stmt.getGeneratedKeys();
                grs.next();
                int soId = grs.getInt(1);
                grs.close();

                stmt2 = con.prepareStatement(INSERT_CHILD);
                for (PersonChild child : so.getChildren()) {
                    prepareParametersChild(stmt2, child, soId);
//                    stmt2.executeUpdate();
                    stmt2.addBatch();
                }
                stmt2.executeBatch();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            } finally {
                if (stmt != null) stmt.close();
                if (stmt2 != null) stmt2.close();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void prepareParametersAdult(PreparedStatement stmt, StudentOrder so) throws SQLException {
        stmt.setDate(1, new java.sql.Date(so.getStudentOrderDate().getTime()));
        stmt.setString(2, so.getHusband().getSurName());
        stmt.setString(3, so.getHusband().getGivenName());
        stmt.setString(4, so.getHusband().getPatronymic());
        stmt.setDate(5, new java.sql.Date(so.getHusband().getDateOfBirth().getTime()));
        stmt.setString(6, so.getHusband().getPassportSeria());
        stmt.setString(7, so.getHusband().getPassportNumber());
        stmt.setDate(8, new java.sql.Date(so.getHusband().getPassportDateExpire().getTime()));
        stmt.setDate(9, new java.sql.Date(so.getHusband().getPassportDateIssue().getTime()));
        stmt.setString(10, so.getWife().getSurName());
        stmt.setString(11, so.getWife().getGivenName());
        stmt.setString(12, so.getWife().getPatronymic());
        stmt.setDate(13, new java.sql.Date(so.getWife().getDateOfBirth().getTime()));
        stmt.setString(14, so.getWife().getPassportSeria());
        stmt.setString(15, so.getWife().getPassportNumber());
        stmt.setDate(16, new java.sql.Date(so.getWife().getPassportDateExpire().getTime()));
        stmt.setDate(17, new java.sql.Date(so.getWife().getPassportDateIssue().getTime()));
    }

    private void prepareParametersChild(PreparedStatement stmt, PersonChild pc, int soId) throws SQLException {
        stmt.setInt(1, soId);
        stmt.setString(2, pc.getSurName());
        stmt.setString(3, pc.getGivenName());
        stmt.setString(4, pc.getPatronymic());
        stmt.setDate(5, new java.sql.Date(pc.getDateOfBirth().getTime()));
        stmt.setString(6, pc.getBirthDocument());
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
