package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */

public class SessionInformation {

    public static User mainUser;
    public static Cart cartInstance = new Cart();
    public static LocalDateTime expiredTime;
// Common Coupling do sử dụng các biến global
// gây Content Coupling ở các module khác vì khi dùng phải truy cập vào các biến static của lớp này
// nên tạo lớp SessionInformation với các thuộc tính như dưới ko dùng static
}
