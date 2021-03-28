package controller;

import entity.cart.CartItem;
import entity.media.Media;

import java.util.List;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
/*
/   common coupling vì hàm checkMediaIncart sử dụng data global của class SessionInformation là cartInstance
 */
    public CartItem checkMediaInCart(Media media){
        //  return SessionInformation.cartInstance.checkMediaInCart(media);
        return SessionInformation.getInstance().getCartInstance().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */

    /*
/   common coupling vì hàm getListCartMedia() sử dụng data global của class SessionInformation là biến cartInstance
 */
    public List getListCartMedia(){
        // return SessionInformation.cartInstance.getListMedia();
        return SessionInformation.getInstance().getCartInstance().getListMedia();
    }
}
