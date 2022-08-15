package by.bntu.fitr.authenticationservice.jwt;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {

    public String encodeWithMD5(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordInBytes = md5.digest(password.getBytes());
        StringBuilder passwordBuilder = new StringBuilder();

        for (byte b : passwordInBytes) {
            passwordBuilder.append(String.format("%02X", b));
        }
        return passwordBuilder.toString();
    }
}
