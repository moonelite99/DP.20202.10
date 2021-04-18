package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;

public class DeliveryInfo {

    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;



    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    // stamp coupling vi ham calculateShippingFee truyen vao order nhung khong su dung
    // Communication Cohesion vì Phương thức calculateShippingFee() không liên quan đến Class này
    //SOLID : vi phạm nguyên lý OCP , DIP vì phụ thuộc trực tiếp vào Class distanceCalculator nên sau này những thay đổi trong tương lai hàm calculateDistance sẽ làm thay đổi class DeliveryInfo


//    public int calculateShippingFee(Order order) {
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
// Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
// nên cần thay bằng 1 biến hằng số (static final )
    //    return (int) (distance * 1.2);
        return (int) (distance * ShippingConfigs.HANG_SO);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
