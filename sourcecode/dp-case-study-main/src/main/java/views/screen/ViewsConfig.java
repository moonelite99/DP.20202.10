package views.screen;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author
 */
//Design Pattern :Singleton Pattern  vì class ViewsConfig đảm bảo chỉ duy nhất 1 instance  được tạo ra và class này cung cấp method getCurrencyFormat()
// để có thể truy xuất được instance duy nhất  mọi lúc mọi nơi trong chương trình
public class ViewsConfig {

    // static resource
    public static final String IMAGE_PATH = "src/main/resources/assets/images";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
    public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
    public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
    public static final String LOGIN_SCREEN_PATH = "/views/fxml/login.fxml";
    public static final String INTRO_SCREEN_PATH = "/views/fxml/intro.fxml";
    public static final String CART_SCREEN_PATH = "/views/fxml/cart.fxml";
    public static final String SHIPPING_SCREEN_PATH = "/views/fxml/shipping.fxml";
    public static final String CART_MEDIA_PATH = "/views/fxml/media_cart.fxml";
    public static final String HOME_PATH  = "/views/fxml/home.fxml";
    public static final String HOME_MEDIA_PATH = "/views/fxml/media_home.fxml";
    public static final String POPUP_PATH = "/views/fxml/popup.fxml";

    public static float PERCENT_VAT = 10;
    public static final int SUBTRACT_VALUE= 31;

    public static final int HEIGHT= 160;
    public static final int WIDTH= 152;

    public static final int WIDTH_MEDIA_INVOICE= 83;
    public static final int HEIGHT_MEDIA_INVOICE= 90;


    public static final int MIN_SPINNER_VALUE= 0;
    public static final int MAX_SPINNER_VALUE= 100;
    public static final int INITIAL_SPINNER_VALUE= 1;



    public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

    public static String getCurrencyFormat(int num) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietnam);
        return defaultFormat.format(num);
    }
}
