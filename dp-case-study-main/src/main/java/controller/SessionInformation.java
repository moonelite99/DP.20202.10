package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */

// gây ra vấn đề common coupling của các module khác vào lớp này
// nên tạo lớp SessionInformation với các thuộc tính như dưới ko dùng static
public class SessionInformation {

    public static User mainUser;
    public static Cart cartInstance = new Cart();
    public static LocalDateTime expiredTime;

}
