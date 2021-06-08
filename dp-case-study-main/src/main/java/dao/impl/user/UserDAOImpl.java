package dao.impl.user;

import entity.db.AIMSDB;
import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author
 */
public class UserDAOImpl {

    public User authenticate(String email, String encryptedPassword) throws SQLException {
        String sql = "SELECT * FROM User " +
                "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";

        AIMSDB aimsdb=AIMSDB.getAimsdb();
        ResultSet res =  aimsdb.getConnection().createStatement().executeQuery(sql);
        if(res.next()) {
            return new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("address"),
                    res.getString("phone")
            );
        } else {
            throw new SQLException();
        }
    }
}
