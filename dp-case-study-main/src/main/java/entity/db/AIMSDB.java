package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;



public class AIMSDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;
	// TODO: refactor Utils -> limit connections
    //Singleton: Cần dùng singleton cho đối tượng Connection vì nếu tạo quá nhiều Connection có thể gây ảnh hưởng tới tốc độ hoặc lãng phí connection
    public static Connection getConnection() {
        if(connect == null)
            synchronized (AIMSDB.class){
                if(connect == null){
                    try {
                        Class.forName("org.sqlite.JDBC");
                        String url = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
                        connect = DriverManager.getConnection(url);
                        LOGGER.info("Connect database successfully");
                    } catch (Exception e) {
                        LOGGER.info(e.getMessage());
                    }
                }
            }
        return connect;
    }

    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
