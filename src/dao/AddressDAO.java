package dao;

import entities.Address;
import entities.Album;
import hibernate.LocalEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class AddressDAO {
    private static EntityManager entityManager = null;

    public static boolean createNewUserAddress(int userId, String firstname, String lastname,
                                           String address1, String address2,
                                           String city, String state, String zip) {
        boolean created = false;

        int addressId;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery createNewAddress = entityManager.createNamedStoredProcedureQuery("createNewAddress");
            createNewAddress.setParameter("_firstname", firstname);
            createNewAddress.setParameter("_lastname", lastname);
            createNewAddress.setParameter("_addressLineOne", address1);
            createNewAddress.setParameter("_addressLineTwo", address2);
            createNewAddress.setParameter("_city", city);
            createNewAddress.setParameter("_state", state);
            createNewAddress.setParameter("_zip", zip);
            createNewAddress.execute();
            addressId = (int) createNewAddress.getSingleResult();

            StoredProcedureQuery createNewUserAddress = entityManager.createNamedStoredProcedureQuery("createNewUserAddress");
            createNewUserAddress.setParameter("_customerID", userId);
            createNewUserAddress.setParameter("_addressID", addressId);
            createNewUserAddress.execute();

            created = true;
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return created;
    }

    public static List<Address> getAllUserAddresses(int userId) {
        List<Address> addresses = null;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getUserAddresses = entityManager.createNamedStoredProcedureQuery("getUserAddresses");
            getUserAddresses.setParameter("_customerID", userId);
            getUserAddresses.execute();
            addresses = (List<Address>) getUserAddresses.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return addresses;
    }

    public static Address getAddressByAddressID(int addressId) {
        Address address = null;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getAddressByAddressID = entityManager.createNamedStoredProcedureQuery("getAddressByAddressID");
            getAddressByAddressID.setParameter("_addressID", addressId);
            getAddressByAddressID.execute();
            address = (Address) getAddressByAddressID.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return address;
    }
}
