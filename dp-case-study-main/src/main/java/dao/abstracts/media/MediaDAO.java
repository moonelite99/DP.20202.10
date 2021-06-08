package dao.abstracts.media;

import entity.media.Media;

import java.sql.SQLException;
import java.util.List;

public interface MediaDAO {
    public List<Media> getAllMedias() throws SQLException;
    public Media getMediaById(int id) throws SQLException;
}
