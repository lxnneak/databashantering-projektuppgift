package se.linnea.projektuppgift;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        WorkRoleDAO workRoleDAO = new WorkRoleDAOimpl();

        WorkRole role = new WorkRole("Consultant", "consults", 45000, java.sql.Date.valueOf("2024-09-07"));
       // System.out.println(role);

        try {
            WorkRole foundRole = workRoleDAO.getWorkRole(5);
            foundRole.setTitle("Fotbollsspelare");

            workRoleDAO.updateWorkRole(foundRole);

            workRoleDAO.deleteWorkRole(5);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
