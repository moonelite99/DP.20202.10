package views.screen;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

public abstract class BaseScreenHandler extends FXMLScreenHandler {

	private static final Logger LOGGER = Utils.getInstance().getLogger(BaseScreenHandler.class.getName());


	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;

	protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}
	protected   void setupData(Object dto) throws Exception {

	}
	protected void setupFunctionality() throws Exception {

	}
	// Clean code : Duplicode do các class view cùng sử dụng chung khối code bên dưới nên ta viết lại thành 1 hàm trong class BaseScreenHandler để tái sử dụng
	public void setupDataAndFunction(Object dto) throws IOException{
		try {
			setupData(dto);
			setupFunctionality();
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error(ex.getMessage());
		}
	}



}
