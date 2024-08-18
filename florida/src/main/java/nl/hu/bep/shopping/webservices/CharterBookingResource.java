package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.CharterBookingDAO;
import nl.hu.bep.shopping.model.CharterBooking;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

@Path("/charter_bookings")
public class CharterBookingResource {

    private static final Logger logger = Logger.getLogger(CharterBookingResource.class.getName());

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getAllCharterBookings() {
        try {
            List<CharterBooking> bookings = CharterBookingDAO.selectAllCharterBookings();
            return Response.ok(bookings).build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving charter bookings: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/get/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getCharterBookingById(@PathParam("bookingId") int bookingId) {
        try {
            CharterBooking booking = CharterBookingDAO.selectCharterBookingById(bookingId);
            if (booking != null) {
                return Response.ok(booking).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Charter booking not found\"}").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving charter booking: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCharterBooking(CharterBooking booking) {
        try {
            CharterBookingDAO.addCharterBooking(booking);
            logger.info("Added charter booking: " + booking);
            return Response.ok("{\"message\":\"Charter booking added successfully\"}").build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error adding charter booking: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")  // Assuming only admins can edit bookings
    public Response editCharterBooking(CharterBooking booking) {
        try {
            boolean updated = CharterBookingDAO.updateCharterBooking(booking);
            if (updated) {
                logger.info("Edited charter booking: " + booking);
                return Response.ok("{\"message\":\"Charter booking updated successfully\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Charter booking not found\"}").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error updating charter booking: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/delete/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")  // Assuming only admins can delete bookings
    public Response deleteCharterBooking(@PathParam("bookingId") int bookingId) {
        try {
            CharterBookingDAO.deleteCharterBooking(bookingId);
            logger.info("Deleted charter booking with ID: " + bookingId);
            return Response.ok("{\"message\":\"Charter booking deleted successfully\"}").build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error deleting charter booking: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}
