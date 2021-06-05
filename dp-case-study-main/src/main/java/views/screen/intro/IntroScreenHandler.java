package views.screen.intro;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

// SOLID : vì vi phạm nguyên lý LSP VÀ ISP vì class  kế thừa từ class cha BaseScreenHandler nhưng không overide các phương thức của class cha
public class IntroScreenHandler extends BaseScreenHandler {

    private static final Logger LOGGER = Utils.getInstance().getLogger(IntroScreenHandler.class.getName());


    @FXML
    ImageView logo;

    public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setupDataAndFunction(null);
    }

    // stamp coupling vì ham setupData truyen vao dto nhung khong su dung
    // clean code : tham số dto không được sử dụng
//    protected void setupData(Object dto) throws Exception {
    protected void setupData() throws Exception {
        return;
    }

    protected void setupFunctionality() throws Exception {
        File file = new File("src/main/resources/assets/images/Logo.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}