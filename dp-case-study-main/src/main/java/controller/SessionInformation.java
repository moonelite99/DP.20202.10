package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */

public class SessionInformation {

    public static User mainUser;
    // public static Cart cartInstance = new Cart();
    public static LocalDateTime expiredTime;

    private static Cart cartInstance;
    public static Cart getCartInstance(){
        if(cartInstance==null){
            cartInstance=new Cart();
        }
        return cartInstance;
    }
    

}
