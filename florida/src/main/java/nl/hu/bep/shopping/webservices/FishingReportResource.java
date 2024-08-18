package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.FishingReportDAO;
import nl.hu.bep.shopping.model.FishingReport;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Path("/fishing_reports")
public class FishingReportResource {

    private static final Logger logger = Logger.getLogger(FishingReportResource.class.getName());

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFishingReports() {
        try {
            List<FishingReport> reports = FishingReportDAO.selectAllFishingReports();
            return Response.ok(reports).build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving fishing reports: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/get/{reportId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getFishingReportById(@PathParam("reportId") int reportId) {
        try {
            FishingReport report = FishingReportDAO.selectFishingReportById(reportId);
            if (report != null) {
                return Response.ok(report).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Fishing report not found\"}").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error retrieving fishing report: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response addFishingReport(FishingReport report) {
        try {
            FishingReportDAO.addFishingReport(report);
            logger.info("Added fishing report: " + report);
            return Response.ok("{\"message\":\"Fishing report added successfully\"}").build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error adding fishing report: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response editFishingReport(FishingReport report) {
        try {
            boolean updated = FishingReportDAO.updateFishingReport(report);
            if (updated) {
                logger.info("Edited fishing report: " + report);
                return Response.ok("{\"message\":\"Fishing report updated successfully\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Fishing report not found\"}").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error updating fishing report: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/delete/{reportId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response deleteFishingReport(@PathParam("reportId") int reportId) {
        try {
            FishingReportDAO.deleteFishingReport(reportId);
            logger.info("Deleted fishing report with ID: " + reportId);
            return Response.ok("{\"message\":\"Fishing report deleted successfully\"}").build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error deleting fishing report: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}
