package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.CharterDAO;
import nl.hu.bep.shopping.model.Charter;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Path("/charters")
public class CharterResource {
    private static final Logger logger = Logger.getLogger(CharterResource.class.getName());

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"user", "admin"})
    public Response getAllCharters() {
        try {
            List<Charter> charters = CharterDAO.selectAllCharters();
            return Response.ok(charters).build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving charters: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharterById(@PathParam("id") int id) {
        try {
            Charter charter = CharterDAO.selectCharterById(id);
            if (charter == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Charter not found\"}").build();
            }
            return Response.ok(charter).build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving charter: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response addCharter(Charter charter) {
        try {
            CharterDAO.addCharter(charter);
            return Response.ok("{\"message\":\"Charter added successfully\"}").build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error adding charter: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response editCharter(Charter charter) {
        try {
            CharterDAO.editCharter(charter);
            return Response.ok("{\"message\":\"Charter updated successfully\"}").build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error updating charter: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response deleteCharter(@PathParam("id") int id) {
        try {
            CharterDAO.deleteCharter(id);
            return Response.ok("{\"message\":\"Charter deleted successfully\"}").build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error deleting charter: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}
