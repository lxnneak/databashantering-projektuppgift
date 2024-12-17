package se.linnea.projektuppgift;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAOimpl implements WorkRoleDAO {
    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = JDBCUtil.getConnection();

            String sql = "INSERT INTO work_role (title, description, salary, creation_date) VALUES (?, ?, ?, ?)";
            pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, workRole.getTitle());
            pStmt.setString(2, workRole.getDescription());
            pStmt.setDouble(3, workRole.getSalary());
            pStmt.setDate(4, workRole.getCreationDate());

            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Role added: " + workRole);
            System.out.println("Rows affected: " + rowsAffected);

            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public void updateWorkRole(WorkRole workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = JDBCUtil.getConnection();

            String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, workRole.getTitle());
            pStmt.setString(2, workRole.getDescription());
            pStmt.setDouble(3, workRole.getSalary());
            pStmt.setDate(4, workRole.getCreationDate());
            pStmt.setInt(5, workRole.getRoleID());

            int rowsAffected = pStmt.executeUpdate();

            System.out.println("Role updated: " + workRole);
            System.out.println("Rows affected: " + rowsAffected);

            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public void deleteWorkRole(Integer roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = JDBCUtil.getConnection();

            String sql = "DELETE FROM work_role WHERE role_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, roleID);

            int rowsAffected = pStmt.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public WorkRole getWorkRole(Integer roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        WorkRole workRole = null;

        try {
            conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM work_role WHERE role_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, roleID);

            rs = pStmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                double salary = rs.getDouble("salary");
                java.sql.Date creationDate = rs.getDate("creation_date");

                workRole = new WorkRole(roleID, title, description, salary, creationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
        return workRole;
    }

    @Override
    public List<WorkRole> getAllWorkRoles() throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        WorkRole workRole = null;
        List<WorkRole> workRoles = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();

            String sql = "SELECT * FROM work_role";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                int roleID = rs.getInt("role_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                double salary = rs.getDouble("salary");
                java.sql.Date creationDate = rs.getDate("creation_date");

                System.out.println("Role ID: " + roleID);
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Salary: " + salary);
                System.out.println("Creation date: " + creationDate);

                workRole = new WorkRole(roleID, title, description, salary, creationDate);
                workRoles.add(workRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }

        return workRoles;
    }
}
