package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.ProductOrderDAO;
import nl.hu.bep.shopping.model.ProductOrder;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Path("/product_orders")
public class ProductOrderResource {
    private static final Logger logger = Logger.getLogger(ProductOrderResource.class.getName());

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getAllProductOrders() {
        try {
            List<ProductOrder> orders = ProductOrderDAO.selectAllProductOrders();
            return Response.ok(orders).build();
        } catch (RuntimeException e) {
            logger.severe("Error retrieving product orders: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }


    @GET
    @Path("/get/all/productid")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getAllProductOrdersatproduct_id() {
        try {
            List<ProductOrder> orders = ProductOrderDAO.selectAllProductOrders();
            return Response.ok(orders).build();
        } catch (RuntimeException e) {
            logger.severe("Error retrieving product orders: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response getProductOrderById(@PathParam("id") int orderId) {
        try {
            ProductOrder order = ProductOrderDAO.selectProductOrderById(orderId);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Product order not found\"}").build();
            }
            return Response.ok(order).build();
        } catch (RuntimeException e) {
            logger.severe("Error retrieving product order: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductOrder(ProductOrder order) {
        try {
            ProductOrderDAO.addProductOrder(order);
            logger.info("Added product order: " + order);
            return Response.ok("{\"message\":\"Product order added successfully\"}").build();
        } catch (RuntimeException | ClassNotFoundException | SQLException e) {
            logger.severe("Error adding product order: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductOrder(@PathParam("id") int orderId) {
        try {
            ProductOrderDAO.deleteProductOrder(orderId);
            return Response.ok("{\"message\":\"Product order deleted successfully\"}").build();
        } catch (RuntimeException e) {
            logger.severe("Error deleting product order: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }




    @GET
    @Path("/get/all/quantity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAllProductOrderQuantity(@PathParam("id") Integer productId) {
        try {
            Integer totalQuantity= ProductOrderDAO.selectAllProductOrderQuantityById(productId);
            if (totalQuantity == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Product order not found\"}").build();
            }
            return Response.ok(totalQuantity).build();
        } catch (RuntimeException e) {
            logger.severe("Error retrieving product order: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editProductOrder(ProductOrder order) {
        try {
            ProductOrderDAO.editProductOrder(order);
            return Response.ok("{\"message\":\"Product order updated successfully\"}").build();
        } catch (RuntimeException e) {
            logger.severe("Error editing product order: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}
