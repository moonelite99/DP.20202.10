package entity.invoice;

import entity.order.Order;

public class Invoice {

    private Order order;
    private int amount;

// Clean code : bỏ phương thức Invoice() vid không sử dụng
//    public Invoice(){
//
//    }

    //SOLID : vi phạm nguyên lý DIP VÀ OCP phương thức này có tham số là 1 class cụ thể Order nếu sau này xuất hiện loại đơn hàng khác thì ảnh hưởng đến mã nguồn
    public Invoice(Order order){
        this.order = order;
        this.amount = order.getTotal();
    }

    public Order getOrder() {
        return order;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    //SOLID : vì vi phạm nguyên tắc SRP vì entity này chỉ cần tạo ra các đối tượng invoice mà ko nên có các phương thức như saveInvoice
  // Clean code : phương thức saveInvoice() không sử dụng
    public void saveInvoice(){
        
    }
}
