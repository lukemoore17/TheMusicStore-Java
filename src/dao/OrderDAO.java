package dao;

import entities.Address;
import entities.Album;
import entities.CustomerOrder;
import entities.CustomerOrderItem;
import hibernate.LocalEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private static EntityManager entityManager = null;

    public static boolean createUserOrder(int userId, int shipAddressId, int billAddressId, List<Album> orderItems) {
        boolean isCreated = false;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery createUserOrder = entityManager.createNamedStoredProcedureQuery("createUserOrder");
            createUserOrder.setParameter("_customerID", userId);
            createUserOrder.setParameter("_shipAddressID", shipAddressId);
            createUserOrder.setParameter("_billAddressID", billAddressId);
            createUserOrder.setParameter("_date", dateString);
            createUserOrder.execute();
            int orderId = (int) createUserOrder.getSingleResult();

            for (Album album : orderItems) {
                createUserOrderItem(orderId, album.getAlbumId(), entityManager);
            }

            isCreated = true;
        } catch (Exception ex) {
            System.out.println("Exception occurred while creating order: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return isCreated;
    }

    private static void createUserOrderItem(int orderId, int albumId, EntityManager entityManager) {
        StoredProcedureQuery createOrderItem = entityManager.createNamedStoredProcedureQuery("createUserOrderItem");
        createOrderItem.setParameter("_orderID", orderId);
        createOrderItem.setParameter("_albumID", albumId);
        createOrderItem.setParameter("_quantity", 1);
        createOrderItem.execute();
    }

    public static List<CustomerOrder> getAllUserOrders(int userId) {
        List<CustomerOrder> orders = null;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getUserOrders = entityManager.createNamedStoredProcedureQuery("getUserOrders");
            getUserOrders.setParameter("_customerID", userId);
            getUserOrders.execute();
            orders = (List<CustomerOrder>) getUserOrders.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return orders;
    }

    public static List<CustomerOrderItem> getUserOrderItemsByOrderID(int orderId) {
        List<CustomerOrderItem> orderItems =  null;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getOrderItems = entityManager.createNamedStoredProcedureQuery("getUserOrderItemsByOrderID");
            getOrderItems.setParameter("_orderID", orderId);
            getOrderItems.execute();
            orderItems = (List<CustomerOrderItem>) getOrderItems.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return orderItems;
    }
}
