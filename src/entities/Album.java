package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "getAlbums",
                procedureName = "my_spS_getAllAlbums",
                resultClasses = { Album.class })
})

@Entity
public class Album {
    private int albumId;
    private String albumName;
    private String artist;
    private String year;
    private double price;
    private String imageLink;
    private Collection<CustomerOrderItem> customerOrderItemsByAlbumId;
    private Collection<GuestOrderItem> guestOrderItemsByAlbumId;

    @Id
    @Column(name = "AlbumID", nullable = false)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "AlbumName", nullable = false, length = 60)
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Basic
    @Column(name = "Artist", nullable = false, length = 40)
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Basic
    @Column(name = "Year", nullable = false, length = 4)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "ImageLink", nullable = true, length = 45)
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return albumId == album.albumId &&
                Double.compare(album.price, price) == 0 &&
                Objects.equals(albumName, album.albumName) &&
                Objects.equals(artist, album.artist) &&
                Objects.equals(year, album.year) &&
                Objects.equals(imageLink, album.imageLink);
    }

    @Override
    public int hashCode() {

        return Objects.hash(albumId, albumName, artist, year, price, imageLink);
    }

    @OneToMany(mappedBy = "albumByAlbumId")
    public Collection<CustomerOrderItem> getCustomerOrderItemsByAlbumId() {
        return customerOrderItemsByAlbumId;
    }

    public void setCustomerOrderItemsByAlbumId(Collection<CustomerOrderItem> customerOrderItemsByAlbumId) {
        this.customerOrderItemsByAlbumId = customerOrderItemsByAlbumId;
    }

    @OneToMany(mappedBy = "albumByAlbumId")
    public Collection<GuestOrderItem> getGuestOrderItemsByAlbumId() {
        return guestOrderItemsByAlbumId;
    }

    public void setGuestOrderItemsByAlbumId(Collection<GuestOrderItem> guestOrderItemsByAlbumId) {
        this.guestOrderItemsByAlbumId = guestOrderItemsByAlbumId;
    }
}
