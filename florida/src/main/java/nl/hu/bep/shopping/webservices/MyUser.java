package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.UserDAO;
import nl.hu.bep.shopping.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MyUser {
    private static final Logger logger = Logger.getLogger(MyUser.class.getName());

    public static Map<String, String> validateLogin(String username, String password) {
        logger.info("Validating login for username: " + username);

        User user = UserDAO.getUser(username);
        logger.info("User fetched from database: " + (user != null ? user.getUsername() : "null"));

        if (user != null && user.getPassword().equals(password)) {
            String role = user.getRole();
            String image = user.getImage();

            logger.info("User validated. Role: " + role);

            // Create a map to store the role and image
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("role", role);
            userInfo.put("image", image);

            return userInfo;
        }

        logger.warning("User validation failed for username: " + username);
        return null;
    }
}
