package nl.hu.bep.shopping.webservices;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secure")
public class SecureResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response getSecureMessage() {
        return Response.ok("This is a secured endpoint").build();
    }
}
