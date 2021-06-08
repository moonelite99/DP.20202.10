package dao.impl.media;

import dao.abstracts.media.CDDAO;
import entity.db.AIMSDB;
import entity.media.CD;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class CDDAOImpl extends MediaDAOImpl implements CDDAO {

    @Override
    public CD findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM "+
                "aims.CD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = CD.id " +
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

            // from CD table
            String artist = res.getString("artist");
            String recordLabel = res.getString("recordLabel");
            String musicType = res.getString("musicType");
            Date releasedDate = res.getDate("releasedDate");

            return new CD(id, title, category, price, quantity, type,
                    artist, recordLabel, musicType, releasedDate);

        } else {
            throw new SQLException();
        }
    }

    @Override
    public void insert(CD cd) {

    }

    @Override
    public void update(CD cd) {

    }

    @Override
    public void delete(CD cd) {

    }

    @Override
    public List<CD> find() {
        return null;
    }
}
