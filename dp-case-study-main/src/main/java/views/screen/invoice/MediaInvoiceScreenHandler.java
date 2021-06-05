package views.screen.invoice;

import java.io.IOException;
import java.sql.SQLException;

import entity.order.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

// Clean code :Với yêu cầu thêm 1 mặt hàng media mới thì khi thay đổi các thuộc tính trong lớp Media thì cũng phải thay đổi code hiển thị sản phẩm trong  lớp này
public class MediaInvoiceScreenHandler extends FXMLScreenHandler{

    @FXML
    private HBox hboxMedia;

    @FXML
    private VBox imageLogoVbox;
    @FXML
    private VBox description;

    @FXML
    private ImageView image;


    @FXML
    private Label title;

    @FXML
    private Label numOfProd;

    @FXML
    private Label labelOutOfStock;

    @FXML
    private Label price;

    private OrderItem orderItem;

    public MediaInvoiceScreenHandler(String screenPath) throws IOException{
        super(screenPath);
    }

    public void setOrderItem(OrderItem orderItem) throws SQLException{
        this.orderItem = orderItem;
        setMediaInfo();
    }

    public void setMediaInfo() throws SQLException{
        title.setText(orderItem.getMedia().getTitle());
        price.setText(ViewsConfig.getCurrencyFormat(orderItem.getPrice()));
        numOfProd.setText(String.valueOf(orderItem.getQuantity()));
        setImage(image, orderItem.getMedia().getImageURL());
		image.setPreserveRatio(false);
        // Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
        // nên cần thay bằng 1 biến hằng số (static final )  HEIGHT_MEDIA_INVOICE và WIDTH_MEDIA_INVOICE
//		image.setFitHeight(90);
//		image.setFitWidth(83);
        image.setFitHeight(ViewsConfig.HEIGHT_MEDIA_INVOICE);
        image.setFitWidth(ViewsConfig.WIDTH_MEDIA_INVOICE);
    }

}
