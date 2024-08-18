package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.CategoryDAO;
import nl.hu.bep.shopping.model.CategoryService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import nl.hu.bep.setup.DatabaseConnectionAzure;

@Path("/categories")
public class CategoryResource {

    @Context
    SecurityContext securityContext;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getAllCategories() {
        try (Connection connection = DatabaseConnectionAzure.getConnection()) {
            List<CategoryService> categories = CategoryDAO.getAllCategories();
            if (categories != null && !categories.isEmpty()) {
                return Response.ok(categories).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"No categories found\"}")
                        .build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Database connection error: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getCategoryById(@PathParam("id") int id) {
        try (Connection connection = DatabaseConnectionAzure.getConnection()) {
            CategoryService category = CategoryDAO.getCategoryById(id);
            if (category != null) {
                return Response.ok(category).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"Category not found\"}")
                        .build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Database connection error: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response addCategory(CategoryService category) {
        if (securityContext != null && securityContext.isUserInRole("admin")) {
            // logic to add category
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Category added successfully\"}")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Unauthorized\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response deleteCategory(@PathParam("id") int id) {
        if (securityContext != null && securityContext.isUserInRole("admin")) {
            // logic to delete category
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Category deleted successfully\"}")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Unauthorized\"}")
                    .build();
        }
    }
}
