package se.linnea.projektuppgift;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkRoleDAOimplTest {
    @AfterEach
    public void cleanUp() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS work_role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Test
    void insertWorkRole() {

        WorkRoleDAO workRoleDAO = new WorkRoleDAOimpl();
        WorkRole role = new WorkRole("Teacher", "educates people", 35000, java.sql.Date.valueOf("2024-09-05"));
        List<WorkRole> roles = new ArrayList<>();

        try {
            workRoleDAO.insertWorkRole(role);
            roles = workRoleDAO.getAllWorkRoles();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        WorkRole foundRole = roles.getFirst();

        Assertions.assertEquals(roles.size(), 1);
        Assertions.assertEquals(foundRole.getTitle(), role.getTitle());
        Assertions.assertEquals(foundRole.getDescription(), role.getDescription());
        Assertions.assertEquals(foundRole.getSalary(), role.getSalary());
        Assertions.assertEquals(foundRole.getCreationDate(), role.getCreationDate());
    }
}