package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Config;
import utils.Utils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;



/**
 * @author
 */

// SOLID : vì phạm nguyên lý ISP và LSP vì class AuthenticationController kế thừa lớp BaseController nhưng lại ko thực hiện (override ) các hành vi,phương thức của class cha là BaseController
// Clean code : Large Class vì class AuthenticationController vừa làm nhiệm vụ authentication vừa phải mã hóa md5, nên tách ra 1 class utils để có thể tái sử dụng
public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }


/*
/    common coupling vì hàm getMainUser sử dụng chung global data từ class SessionInformation
*/
    // clean code : nên chuyển các điều kiện so sánh vào 1 hàm vì biểu thức so sánh hiện tại dài, khó tái sử dụng
    public User getMainUser() throws ExpiredSessionException {

        if (SessionInformation.getInstance().checkUserExit() || SessionInformation.getInstance().isExpired()) {
            logout();
            throw new ExpiredSessionException();
        } else return SessionInformation.getInstance().getMainUser().cloneInformation();
    }

/*
   common coupling vì hàm login  sử dụng chung global data từ class SessionInformation
*/
    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, Utils.getInstance().md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.getInstance().setMainUser(user);
            // Clean code : vì sử số trực tiếp trong code gây khó đọc hiểu, sau này khi muốn thay đổi sẽ phải tìm kiếm trên toàn bộ source code để thay đổi
            // nên cần thay bằng 1 biến hằng số (static final )
            //SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(24));
            SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(Config.PLUS_HOUR));
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }


/*
/    common coupling vì hàm logout sử dụng chung global data từ class SessionInformation là mainUser va expiredTime
*/
// Procedural Cohesion vì phương thức logout() được nhóm lại trong Class này để tuân theo trình tự thực thi từ login -> Logout
    public void logout() {
        SessionInformation.getInstance().setMainUser(null);
        SessionInformation.getInstance().setExpiredTime(null);
    }



    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
// coincidental cohesion vì hàm md5() nên để lại class utils để controller gọi đến khi sử dụng
// SOLID : vi phạm nguyên lý SRP vì class AuthenticationController thực hiện nhiều hơn 1 nhiệm vụ như vừa xác thực( login) vừa phải mã hóa  dữ liệu
 /*
 *    private String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            //Clean code : Đặt tên biến không rõ ràng như các biến  0xff,02x gây khó đọc hiểu code, dễ nhầm lẫn,
            // khi cần thay đổi thì phải tìm kiếm ở toàn bộ source để thay đổi, nên cầy thay thế bằng các biến hằng số (static final )
            for (byte b : hash) {
//              sb.append(String.format("%02x", b & 0xff));
                sb.append(String.format(Config.HEXADECIMAL, b & Config.HEXA_VALUE));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getInstance().getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
    }
*/
}
