package se.linnea.projektuppgift;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {
    public void insertWorkRole(WorkRole workRole) throws SQLException;

    public void updateWorkRole(WorkRole workRole) throws SQLException;

    public void deleteWorkRole(Integer roleID) throws SQLException;

    public WorkRole getWorkRole(Integer roleID) throws SQLException;

    public List<WorkRole> getAllWorkRoles() throws SQLException;
}
