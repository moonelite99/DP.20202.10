package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;


 //SOLID: Vi phạm nguyên tắc SRP. Vì thực hiện đến nhiều hơn một chức năng,  vừa  thực hiện get, post
// vừa  tạo ra kết nối HTTP thông qua phương thức setupConnection, đồng thời cũng sử dụng method allMethod cho việc điều khiển truy cập 
public class ApplicationProgrammingInterface {
// clean code : DATE_FORMATTẺ không được sử dụng
//	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = Utils.getInstance().getLogger(Utils.class.getName());

	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");
		HttpURLConnection conn = setupConnection(url);

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}


	public static String post(String url, String data) throws IOException {
		allowMethods("PATCH");
		HttpURLConnection conn = setupConnection(url);
		conn.setRequestMethod("PATCH");
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");

		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		BufferedReader in;
		String inputLine;
		// clean code : vì  sử dụng số cụ thể 100 và 2 sẽ làm người dùng khó đọc và khó hiểu ý nghĩa, gây lỗi tiềm ẩn,
		// khi muốn thay đổi thì phải tìm kiếm trên toàn bộ source code gây tốn nhiều thời gian,nên cần thay bằng biến constant
//		if (conn.getResponseCode() / 100 ==2) {
		int ResponseCode=conn.getResponseCode()/100;
		if (ResponseCode==Config.STATUS_CODE) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		LOGGER.info("Respone Info: " + response.toString());
		return response.toString();
	}

	private static HttpURLConnection setupConnection(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}
/*
/    Content coupling vì hàm allowMethods () sử dụng setAccessible(true) để thay đổi sự truy cập
 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}
