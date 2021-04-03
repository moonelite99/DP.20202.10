package views.screen.home;

import controller.AuthenticationController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

// SOLID : vì vi phạm nguyên lý LSP VÀ ISP vì class  kế thừa từ class cha BaseScreenHandler nhưng không overide các phương thức của class cha
public class LoginScreenHandler extends BaseScreenHandler{

    public static Logger LOGGER = Utils.getInstance().getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
        try {
            setupData(null);
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error("Error when loading resources.");
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }
    
    // stamp coupling vì ham setupData truyen vao dto nhung khong su dung
    protected void setupData(Object dto) throws Exception {
    }

    protected void setupFunctionality() throws Exception {
    }

    @FXML
    void login(MouseEvent event) throws IOException, InterruptedException, SQLException {
        try {
            getBController().login(email.getText(), password.getText());
            PopupScreen.success("Login Successfully!");
            backToHomeScreen(event);
        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
        }
    }
    // Coincidental cohesion hàm này nên để ra util 
    @FXML
    void backToHomeScreen(MouseEvent event) throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
