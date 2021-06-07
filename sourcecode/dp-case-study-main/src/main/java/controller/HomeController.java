package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.MediaDAO;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
// SOLID : vì phạm nguyên lý ISP và LSP vì class HomeController kế thừa lớp BaseController nhưng lại ko thực hiện (override ) các hành vi,phương thức của class cha là BaseController
public class HomeController extends BaseController {

    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }
}
