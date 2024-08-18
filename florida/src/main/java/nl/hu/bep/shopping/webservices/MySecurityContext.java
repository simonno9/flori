package nl.hu.bep.shopping.webservices;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private String username;
    private String role;
    private boolean isSecure;

    public MySecurityContext(String username, String role, boolean isSecure) {
        this.username = username;
        this.role = role;
        this.isSecure = isSecure;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> username;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return false;
        }
        return this.role.equals(role);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }
}
