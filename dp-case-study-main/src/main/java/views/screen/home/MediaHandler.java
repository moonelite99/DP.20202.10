package views.screen.home;

import common.interfaces.Observable;
import common.interfaces.Observer;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
//Áp dụng Observer pattern

// Clean code :Với yêu cầu thêm 1 mặt hàng media mới thì khi thay đổi các thuộc tính trong lớp Media thì cũng phải thay đổi code hiển thị sản phẩm trong  lớp này

public class MediaHandler extends FXMLScreenHandler implements Observable {

    @FXML
    protected ImageView mediaImage;

    @FXML
    protected Label mediaTitle;

    @FXML
    protected Label mediaPrice;

    @FXML
    protected Label mediaAvail;

    @FXML
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;
// clean code : Logger không được sử dụng trong class này
//    private static Logger LOGGER = Utils.getInstance().getLogger(MediaHandler.class.getName());
    private Media media;
    private List<Observer> observerList;

    public MediaHandler(String screenPath, Media media) throws SQLException, IOException{
        super(screenPath);
        this.media = media;
        this.observerList = new ArrayList<>();
        addToCartBtn.setOnMouseClicked(event -> {
            notifyObservers();
        });
        setMediaInfo();
    }

    Media getMedia(){
        return media;
    }
    int getRequestQuantity() {
        return spinnerChangeNumber.getValue();
    }

    private void setMediaInfo() throws SQLException {
        // set the cover image of media
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        // Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
        // nên cần thay bằng 1 biến hằng số (static final )  HEIGHT và WIDTH
//      mediaImage.setFitHeight(160);
//      mediaImage.setFitWidth(152);
        mediaImage.setFitHeight(ViewsConfig.HEIGHT);
        mediaImage.setFitWidth(ViewsConfig.WIDTH);
        mediaImage.setImage(image);

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(ViewsConfig.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));

        spinnerChangeNumber.setValueFactory(
                // Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
                // nên cần thay bằng 1 biến hằng số (static final )
                // new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
                new SpinnerValueFactory.IntegerSpinnerValueFactory(ViewsConfig.MIN_SPINNER_VALUE, ViewsConfig.MAX_SPINNER_VALUE, ViewsConfig.INITIAL_SPINNER_VALUE)
        );

        setImage(mediaImage, media.getImageURL());
    }

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(observer -> observer.update(this));
    }

}
