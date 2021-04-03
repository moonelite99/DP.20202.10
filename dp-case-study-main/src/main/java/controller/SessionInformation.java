package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */

public class SessionInformation {
    private  User mainUser;
    private  Cart cartInstance ;
    private  LocalDateTime expiredTime;
    private static SessionInformation instance;

    private SessionInformation(){
        cartInstance = Cart.getCartInstance();
    }
    public static SessionInformation getInstance(){
        if(instance==null) {
            instance = new SessionInformation();
        }
        return instance;
    }

    public User getMainUser() {
        return mainUser;
    }

    public Cart getCartInstance() {
        return cartInstance;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
