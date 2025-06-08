package it.unipv.ingsw.syzygy.excamp.database.dao;

import java.sql.*;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;

public class StaffProfileDAO {
    private Connection connection;

    public StaffProfileDAO(Connection connection) {
        this.connection = connection;
    }

   
    public StaffProfileModel getStaffProfileById(int id) {
        StaffProfileModel staff = null;
        
        String query = "SELECT s.persona_id, s.nameST, s.surnameST, s.ageST, s.phonenumberST, " +
                       "s.accommodationST, s.residenceST, s.transportationST, s.allergiesST, s.medicalIssuesST, " +
                       "p.profilePicturePath, p.campExp, p.camp " +
                       "FROM STAFF s " +
                       "JOIN PROFILO p ON s.persona_id = p.persona_id AND s.CFST = p.CFST " +
                       "WHERE s.persona_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new StaffProfileModel(
                    rs.getString("nameST"),
                    rs.getString("surnameST"),
                    rs.getInt("ageST"),
                    rs.getString("phonenumberST"),
                    rs.getString("accommodationST"),
                    rs.getString("residenceST"),
                    rs.getString("transportationST"),  
                    rs.getString("allergiesST"),
                    rs.getString("medicalIssuesST"),
                    rs.getString("profilePicturePath"),
                    rs.getString("campExp")
                );
                staff.setId(rs.getInt("persona_id"));
                staff.setCamp(rs.getString("camp")); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }
  
    public boolean updateExperience(StaffProfileModel staffProfile) {
        String query = "UPDATE PROFILO SET campExp = ? WHERE persona_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staffProfile.getExperience());
            stmt.setInt(2, staffProfile.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProfilePicture(StaffProfileModel staffProfile) {
        String query = "UPDATE PROFILO SET profilePicturePath = ? WHERE persona_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staffProfile.getProfilePicturePath());
            stmt.setInt(2, staffProfile.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
