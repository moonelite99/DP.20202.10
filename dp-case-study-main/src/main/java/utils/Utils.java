package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */
//Design Pattern :  Singleton pattern  vì class Utils đảm bảo chỉ nen co  1 instance được tạo ra, neu tao nhieu thua, ton tai nguyen
public class Utils {
	private static  Utils instance;
	private  Utils(){}
	public static Utils getInstance(){
		if(instance == null ){
			instance= new Utils();
		}
		return instance;
	}

	private DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private  Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public DateFormat getDateFormatter() {return DATE_FORMATTER ;}

}