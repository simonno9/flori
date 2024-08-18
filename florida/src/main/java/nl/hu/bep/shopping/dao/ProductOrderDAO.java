package nl.hu.bep.shopping.dao;

import nl.hu.bep.setup.DatabaseConnectionAzure;
import nl.hu.bep.shopping.model.ProductOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderDAO {

    public static List<ProductOrder> selectAllProductOrders() {
        List<ProductOrder> orders = new ArrayList<>();
        String query = "SELECT po.order_id, po.order_quantity, po.order_date, p.product_name, p.description, p.sizes, p.colors, po.product_id " +
                "FROM product_orders po " +
                "JOIN products p ON po.product_id = p.id";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductOrder order = new ProductOrder(
                        resultSet.getInt("order_id"),
                        resultSet.getInt("order_quantity"),
                        resultSet.getString("order_date"),
                        resultSet.getString("product_name"),
                        resultSet.getString("description"),
                        resultSet.getString("sizes"),
                        resultSet.getString("colors"),
                        resultSet.getInt("product_id")

                );
                orders.add(order);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving product orders", e);
        }

        return orders;
    }


    public static Integer selectAllProductOrderQuantityById(Integer productID) {
        String query="SELECT SUM(order_quantity) AS total_quantity FROM product_orders WHERE product_id = ?";
        Integer totalQuantity=0;

        try (Connection connection= DatabaseConnectionAzure.getConnection();
        PreparedStatement statement= connection.prepareStatement(query)) {

            statement.setInt(1, productID);

            try (ResultSet resultSet= statement.executeQuery()) {
                if (resultSet.next()) {
                    totalQuantity = resultSet.getInt("total_quantity");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving product orders", e);
        }

        return totalQuantity;
    }


    public static void deleteProductOrder(int orderId) {
        String query = "DELETE FROM product_orders WHERE order_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product order", e);
        }
    }

    public static void editProductOrder(ProductOrder order) {
        String query = "UPDATE product_orders SET order_quantity = ?, order_date = ?, product_id = ? WHERE order_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, order.getOrderQuantity());
            statement.setString(2, order.getOrderDate());
            statement.setInt(3, order.getProductId());
            statement.setInt(4, order.getOrderId());


            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating product order", e);
        }
    }

    public static void addProductOrder(ProductOrder order) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO product_orders (product_id, order_quantity, order_date) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, order.getProductId());
            statement.setInt(2, order.getOrderQuantity());
            statement.setString(3, order.getOrderDate());

            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product order", e);
        }
    }

    public static ProductOrder selectProductOrderById(int orderId) throws ClassNotFoundException, SQLException {
        String query = "SELECT po.order_id, po.order_quantity, po.order_date, p.product_name, p.description, p.sizes, p.colors, po.product_id " +
                "FROM product_orders po " +
                "JOIN products p ON po.product_id = p.id " +
                "WHERE po.order_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProductOrder(
                            resultSet.getInt("order_id"),
                            resultSet.getInt("order_quantity"),
                            resultSet.getString("order_date"),
                            resultSet.getString("product_name"),
                            resultSet.getString("description"),
                            resultSet.getString("sizes"),
                            resultSet.getString("colors"),
                            resultSet.getInt("product_id")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving product order", e);
        }
    }
}
