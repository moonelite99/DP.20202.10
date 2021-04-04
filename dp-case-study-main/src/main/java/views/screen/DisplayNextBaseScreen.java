package views.screen;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class DisplayNextBaseScreen extends BaseScreenHandler {
    protected DisplayNextBaseScreen(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    protected abstract void displayNextScreen(BaseScreenHandler baseScreenHandler);

    public final void showNextScreen( BaseScreenHandler baseScreenHandler){
        displayNextScreen(baseScreenHandler);
        baseScreenHandler.show();
    }


}
