package entity.cart;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<CartItem> lstCartItem;

    private static Cart instance;

    private Cart() {
        lstCartItem = new ArrayList<>();
    }
    public static Cart getCartInstance(){
        if(instance == null){
            instance = new Cart();
        }
        return instance;
    }

    public void addCartMedia(CartItem cm){
        lstCartItem.add(cm);
    }

    public void removeCartMedia(CartItem cm){
        lstCartItem.remove(cm);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }

    public CartItem checkMediaInCart(Media media){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
            //Stamp Coupling vì truyền vào cả media nhưng chỉ dùng mỗi id
        }
        return null;
    }

}
