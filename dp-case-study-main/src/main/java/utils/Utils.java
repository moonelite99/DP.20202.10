package utils;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			//Clean code : Đặt tên biến không rõ ràng như các biến  0xff,02x gây khó đọc hiểu code, dễ nhầm lẫn,
			// khi cần thay đổi thì phải tìm kiếm ở toàn bộ source để thay đổi, nên cầy thay thế bằng các biến hằng số (static final )
			for (byte b : hash) {
//              sb.append(String.format("%02x", b & 0xff));
				sb.append(String.format(Config.HEXADECIMAL, b & Config.HEXA_VALUE));
			}
			digest = sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			this.getLogger(Utils.class.getName());
			digest = "";
		}
		return digest;
	}
	public boolean validateInfoUser(String text) {
		if (Objects.isNull(text)) return false;
		String patternString = "^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	public boolean validatePhoneNumber(String phoneNumber) {
		if (phoneNumber.length() != Config.PHONE_LENGTH) return false;
		if (!phoneNumber.startsWith("0")) return false;
		try {
			Integer.parseInt(phoneNumber);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}