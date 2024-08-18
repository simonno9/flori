package nl.hu.bep.shopping.filters;

import nl.hu.bep.shopping.utils.JwtUtil;
import nl.hu.bep.shopping.webservices.MySecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        MySecurityContext msc = new MySecurityContext(null, null, requestCtx.getSecurityContext().isSecure());
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer".length()).trim();
            try {
                Jws<Claims> claims = JwtUtil.validateToken(token);
                String user = claims.getBody().getSubject();
                String role = claims.getBody().get("role", String.class);
                msc = new MySecurityContext(user, role, requestCtx.getSecurityContext().isSecure());
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestCtx.setSecurityContext(msc);
    }
}

