package dao;

import hibernate.LocalEntityManagerFactory;

import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.StoredProcedureQuery;

public class CustomerLoginDAO {
    private static EntityManager entityManager = null;

    public static boolean login(String username, String password) {
        boolean verified = false;
        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery verifyCredentials = entityManager.createNamedStoredProcedureQuery("verifyCredentials")
                    .setParameter("_username", username)
                    .setParameter("_password", password);
            verifyCredentials.execute();
            int rowsReturned = verifyCredentials.getResultList().size();
            //CustomerLogin user = (CustomerLogin) verifyCredentials.getSingleResult();
            if (rowsReturned == 1) {
                verified = true;
            } else {
                //System.err.println("No users or more than one user returned");
            }
        } catch (Exception ex) {
            System.out.println("Exception occurred while logging in: "
                    + ex.getMessage());
            return false;
        } finally {
            entityManager.close();
        }

        return verified;
    }

    public static Customer getUserInfoByUsername(String username) {
        Customer user = null;
        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getUser = entityManager.createNamedStoredProcedureQuery("getUserByUsername")
                    .setParameter("_username", username);
            getUser.execute();
            user = (Customer) getUser.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception occurred while getting user: "
                    + ex.getMessage());
        } finally {
            entityManager.close();
        }

        return user;
    }

    public static void changePassword(int userID, String password) {
        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery changePW = entityManager.createNamedStoredProcedureQuery("changePassword")
                    .setParameter("_customerID", userID)
                    .setParameter("_password", password);
            changePW.execute();
        } catch (Exception ex) {
            System.out.println("Exception occurred while changing password: "
                    + ex.getMessage());
            //ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public static boolean createNewUser(String firstname, String lastname, String username, String password, String email) {
        boolean created = false;

        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery createNewUser = entityManager.createNamedStoredProcedureQuery("createNewUser")
                    .setParameter("_firstname", firstname)
                    .setParameter("_lastname", lastname)
                    .setParameter("_username", username)
                    .setParameter("_password", password)
                    .setParameter("_email", email);
            createNewUser.execute();
            created = true;
        } catch (Exception ex) {
            System.out.println("Exception occurred while creating new user: "
                    + ex.getMessage());
            //ex.printStackTrace();
        } finally {
            entityManager.close();
        }

        return created;
    }
}
