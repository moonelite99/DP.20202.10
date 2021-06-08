package dao.impl.media;

import dao.abstracts.media.DVDDAO;
import entity.db.AIMSDB;
import entity.media.DVD;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class DVDDAOImpl extends MediaDAOImpl implements DVDDAO {

    @Override
    public DVD findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM "+
                "aims.DVD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = DVD.id " +
                "where Media.id = " + id + ";";

        AIMSDB aimsdb=AIMSDB.getAimsdb();
        ResultSet res = aimsdb.getConnection().createStatement().executeQuery(sql);
        if(res.next()) {

            // from media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from DVD table
            String discType = res.getString("discType");
            String director = res.getString("director");
            int runtime = res.getInt("runtime");
            String studio = res.getString("studio");
            String subtitles = res.getString("subtitle");
            Date releasedDate = res.getDate("releasedDate");
            String filmType = res.getString("filmType");

            return new DVD(id, title, category, price, quantity, type, discType, director, runtime, studio, subtitles, releasedDate, filmType);

        } else {
            throw new SQLException();
        }
    }

    @Override
    public void insert(DVD dvd) {

    }

    @Override
    public void update(DVD dvd) {

    }

    @Override
    public void delete(DVD dvd) {

    }

    @Override
    public List<DVD> find() {
        return null;
    }

}
