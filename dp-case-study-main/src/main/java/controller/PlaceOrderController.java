package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import org.example.DistanceCalculator;
import utils.Config;

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

    private static PlaceOrderController placeOrderController;
    private PlaceOrderController(){

    }

    public static PlaceOrderController getInstance(){
        if(placeOrderController==null){
            placeOrderController=new PlaceOrderController();
        }
        return placeOrderController;

    }



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
// Coincidental Cohesion vì phương thức processDeliveryInfo() không liên quan đến nghiệp vụ của class PlaceOrderController
// mà nên tách ra 1 module riêng

    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }


    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */


/*
/      Logical cohesion vì các phương thức validate như validateDeliveryInfo(),validatePhoneNumber(),validateName(),validateAddress()
      cùng xử lý logic là validate nên ta cần tách ra viết 1 phương thức validate rồi để các phương thức kia override lại

 */


    // SOLID : vi phạm nguyên lý OCP vì sau này cần thay đổi info để validate thì phần code xử lý cũng phải thay đổi
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
    // Clean code : khong nen viet cac dieu kien kiem tra dai, gay kho hieu ma nen chuyen thanh cac bien kiem tra
        boolean isPhoneNumber=validatePhoneNumber(info.get("phone"));
        boolean isName=validateInfoUser(info.get("name"));
        boolean isAddress=validateInfoUser(info.get("address"));
//        if (validatePhoneNumber(info.get("phone"))
//        || validateName(info.get("name"))
//        || validateAddress(info.get("address"))) return;
        if(isPhoneNumber||isName||isAddress) return;

        else throw new InvalidDeliveryInfoException();
    }


    public boolean validatePhoneNumber(String phoneNumber) {

//Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
// nên cần thay bằng 1 biến hằng số (static final )
//        if (phoneNumber.length() != 10) return false;
        if (phoneNumber.length() != Config.PHONE_LENGTH) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
 // Clean code : bị duplicode    nên viết thành 1 hàm
  /*  public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }*/

    public boolean validateInfoUser(String text) {
        if (Objects.isNull(text)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
