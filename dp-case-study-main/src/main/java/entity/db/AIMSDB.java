package entity.db;

import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;


//Design Pattern : Singleton pattern vì class AIMSDB đảm bảo chỉ duy nhất 1 instance  được tạo ra và class cung cấp method getConnection()
// để có thể truy xuất được instance duy nhất  mọi lúc mọi nơi trong chương trình
public class AIMSDB {

	private static Logger LOGGER = Utils.getInstance().getLogger(Connection.class.getName());
    private static AIMSDB aimsdb;
    private AIMSDB(){

    }
    public static AIMSDB getAimsdb(){
        if(aimsdb==null){
            aimsdb=new AIMSDB();
        }
        return aimsdb;
    }

	private static Connection connect;
	// TODO: refactor Utils -> limit connections
    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
			Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
            connect = DriverManager.getConnection(url);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } 
        return connect;
    }


    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
