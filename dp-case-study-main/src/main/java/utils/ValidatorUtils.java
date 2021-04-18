package utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clean code: tách từ lớp PlaceOrderController đảm bảo SRP
public class ValidatorUtils {

    // Clean Code: đặt tên sai nghĩa nên chuyển validatePhoneNumber -> isValidPhoneNumber
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Clean Code: đặt tên sai nghĩa nên chuyển validateName -> isValidName
    public static boolean isValidName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // Clean Code: đặt tên sai nghĩa nên chuyển validateAddress -> isValidAddress
    public static boolean isValidAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
