package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.User;
import nl.hu.bep.shopping.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Path("/authentication")
public class AuthenticationResource {
    private static final Logger logger = Logger.getLogger(AuthenticationResource.class.getName());
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest) {
        logger.info("Received logon request for username: " + logonRequest.username);

        try {
            Map<String, String> userInfo = MyUser.validateLogin(logonRequest.username, logonRequest.password); // Retrieve the user info map

            if (userInfo == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\":\"Invalid username or password\"}")
                        .build();
            }

            String token = JwtUtil.createToken(logonRequest.username, userInfo.get("role"));
            logger.info("Generated token for user: " + logonRequest.username + " with role: " + userInfo.get("role"));

            // Create response map to include JWT and user image
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("JWT", token);
            responseMap.put("image", userInfo.get("image")); // Include image in the response

            return Response.ok(responseMap).build();
        } catch (JwtException e) {
            logger.warning("Authentication failed: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Invalid username or password\"}")
                    .build();
        } catch (Exception e) {
            logger.severe("An error occurred during authentication: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Something went wrong with the fetch\"}")
                    .build();
        }
    }
}
