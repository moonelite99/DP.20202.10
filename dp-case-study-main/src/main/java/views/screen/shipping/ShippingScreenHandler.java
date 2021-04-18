package views.screen.shipping;

import common.exception.InvalidDeliveryInfoException;
import controller.PlaceOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.DistanceCalculator;
import utils.Utils;
import utils.ValidatorUtils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

// SOLID : vì vi phạm nguyên lý LSP VÀ ISP vì class kế thừa từ class cha BaseScreenHandler nhưng không overide các phương thức của class cha
public class ShippingScreenHandler extends BaseScreenHandler {

	private static final Logger LOGGER = Utils.getInstance().getLogger(ShippingScreenHandler.class.getName());

	@FXML
	private Label screenTitle;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private TextField address;

	@FXML
	private TextField instructions;

	@FXML
	private ComboBox<String> province;

	private Order order;

	public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		try {
			setupData(order);
			setupFunctionality();
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.showErrorPopup("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.showErrorPopup(ex.getMessage());
		}
	}
/*
/   Common coupling vì hàm setupData sử dụng global data của lớp ShippingConfigs là PROVINCES, RUSH_SUPPORT_PROVINCES_INDEX
 */
	protected void setupData(Object dto) throws Exception {
		this.order = (Order) dto;
		this.province.getItems().addAll(ShippingConfigs.PROVINCES);
		this.province.getSelectionModel().select(ShippingConfigs.RUSH_SUPPORT_PROVINCES_INDEX[0]);
	}

	protected void setupFunctionality() throws Exception {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		name.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
			if(newValue && firstTime.get()){
				content.requestFocus(); // Delegate the focus to container
				firstTime.setValue(false); // Variable value changed for future references
			}
		});

	}

	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

		// validate delivery info and prepare order info
		preprocessDeliveryInfo();
		
		// create invoice screen
		Invoice invoice = getBController().createInvoice(order);
		BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, ViewsConfig.INVOICE_SCREEN_PATH, invoice);
		InvoiceScreenHandler.setPreviousScreen(this);
		InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
		InvoiceScreenHandler.setScreenTitle("Invoice Screen");
		InvoiceScreenHandler.setBController(getBController());
		InvoiceScreenHandler.show();
	}

	public void preprocessDeliveryInfo() throws IOException, InterruptedException {
		// add info to messages
		HashMap<String, String> messages = new HashMap<>();
		messages.put("name", name.getText());
		messages.put("phone", phone.getText());
		messages.put("address", address.getText());
		messages.put("instructions", instructions.getText());
		messages.put("province", province.getValue());
		DeliveryInfo deliveryInfo;
		try {
			// process and validate delivery info
			deliveryInfo = processDeliveryInfo(messages);
		} catch (InvalidDeliveryInfoException e) {
			// TODO: implement pop up screen
			throw new InvalidDeliveryInfoException(e.getMessage());
		}
		order.setDeliveryInfo(deliveryInfo);
	}

	// Coincidental Cohesion vì phương thức processDeliveryInfo() không liên quan đến nghiệp vụ của class PlaceOrderController
// mà nên tách ra 1 module riêng

	public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
		LOGGER.info("Process Delivery Info");
		LOGGER.info(info.toString());
		DeliveryInfo deliveryInfo = new DeliveryInfo(
				String.valueOf(info.get("name")),
				String.valueOf(info.get("phone")),
				String.valueOf(info.get("province")),
				String.valueOf(info.get("address")),
				String.valueOf(info.get("instructions")),
				new DistanceCalculator());
		validateDeliveryInfo(deliveryInfo);
		System.out.println(deliveryInfo.getProvince());
		return deliveryInfo;
	}


	/**
	 * The method validates the info
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */


/*
/      Logical cohesion vì các phương thức validate như validateDeliveryInfo(),validatePhoneNumber(),validateName(),validateAddress()
      cùng xử lý logic là validate nên ta cần tách ra viết 1 phương thức validate rồi để các phương thức kia override lại
 */
	// SOLID : vi phạm nguyên lý OCP vì sau này cần thay đổi info để validate thì phần code xử lý cũng phải thay đổi
	// Clean code: Parameter nên dùng là DeliveryInfo
	public void validateDeliveryInfo(DeliveryInfo info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
		if (ValidatorUtils.isValidPhoneNumber(info.getPhone())
				|| ValidatorUtils.isValidName(info.getName())
				|| ValidatorUtils.isValidAddress(info.getAddress()))
			return;
		else throw new InvalidDeliveryInfoException();
	}


	public PlaceOrderController getBController(){
		return (PlaceOrderController) super.getBController();
	}
/*
/   Coincidental Cohesion vì phương thức notifyError() này không liên quan đến Module này, cần viết trong mục error riêng
 */

//SOLID : vì vi phạm Nguyên lý DIP vì phương thức notifyError() nên được implements từ 1 class Abstract Notify riêng
	public void notifyError(){
		// TODO: implement later on if we need
	}

}
