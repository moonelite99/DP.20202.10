package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import org.example.DistanceCalculator;
import utils.ValidatorUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */

// SOLID: vi pham nguyen ly SRP, vi PlaceOrderController thuc hien nhieu chuc nang: place order, validate
// logical conhesion, cac phuong thuc validate can dc tach rieng vao mot lop
// SOLID: Vi phạm Nguyên lý SRP vì class này thực vì class PlaceOrderController thực hiện nhiều hơn 1 nhiệm vụ như vừa phải
// điều khiển luồng dữ liệu như tạo đơn hàng, tạo hóa đơn,xử lý thông tin đơn, vừa phải validate dữ liệu
// SOLID : vì phạm nguyên lý ISP và LSP vì class PlaceOrderController kế thừa lớp BaseController nhưng lại ko thực hiện (override ) các hành vi,
//phương thức của class cha là BaseController


public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getInstance().getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    /*
/   common coupling vì hàm placeOrder sử dụng data global của class SessionInformation
 */
    public void placeOrder() throws SQLException {
        // SessionInformation.cartInstance.checkAvailabilityOfProduct();
        SessionInformation.getInstance().getCartInstance().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    /*
    /   common coupling vì hàm createOrder sử dụng data global của class SessionInformation
    */
    public Order createOrder() throws SQLException {
        // return new Order(SessionInformation.cartInstance);
        return new Order(SessionInformation.getInstance().getCartInstance());
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    
    // data coupling vi ham createInvoice truyen vao order va dung het bien order
    //SOLID : vi phạm nguyên lý DIP VÀ OCP phương thức này có tham số là 1 class cụ thể Order nếu sau này xuất hiện loại đơn hàng khác thì ảnh hưởng đến mã nguồn
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */


}
