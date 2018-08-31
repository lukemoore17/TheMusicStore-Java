package dao;

import entities.Album;
import hibernate.LocalEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class AlbumsDAO {
    private static EntityManager entityManager = null;
    private static List<Album> albums = null;

    public static List<Album> load() {
        try {
            entityManager = LocalEntityManagerFactory.createEntityManager();
            StoredProcedureQuery getAlbums = entityManager.createNamedStoredProcedureQuery("getAlbums");
            getAlbums.execute();
            albums = getAlbums.getResultList();
        } catch (Exception ex) {
            System.out.println("Exception occurred while fetching albums: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
        return albums;
    }
}
