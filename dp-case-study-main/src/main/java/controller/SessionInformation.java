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
// nên tạo lớp SessionInformation với các thuộc tính như dưới ko dùng static
}
