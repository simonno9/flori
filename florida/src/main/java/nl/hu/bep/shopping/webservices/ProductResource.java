package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.dao.ProductDAO;
import nl.hu.bep.shopping.model.Product;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.sql.SQLException;


@Path("/products")
public class ProductResource {

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"user", "admin"})
    public  Response getAllProducts(){
        try{
            List<Product> products = ProductDAO.selectAllProduct();
            return Response.ok(products).build();
        }catch (RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();

        }
    }
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        try {
            Product product = ProductDAO.selectProductById(id);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Product not found\"}").build();
            }
            return Response.ok(product).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }


        private static final Logger logger = Logger.getLogger(ProductResource.class.getName());
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        try {
            ProductDAO.addProduct(product);
            logger.info("Added product: " + product);
            return Response.ok("{\"message\":\"Product added successfully\"}").build();
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Error adding product: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

        @POST
        @Path("/edit")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response editProduct(Product product) {
            logger.info("Received product data: " + product);

            try {
                ProductDAO.editProduct(product);
                return Response.ok("{\"message\":\"Product updated successfully\"}").build();
            } catch (RuntimeException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
            }
        }

}



