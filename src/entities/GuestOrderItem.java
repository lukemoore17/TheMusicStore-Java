package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "guest_order_item", schema = "music_store", catalog = "")
public class GuestOrderItem {
    private int guestOrderItemId;
    private int quantity;
    private Album albumByAlbumId;

    @Id
    @Column(name = "GuestOrderItemID", nullable = false)
    public int getGuestOrderItemId() {
        return guestOrderItemId;
    }

    public void setGuestOrderItemId(int guestOrderItemId) {
        this.guestOrderItemId = guestOrderItemId;
    }

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestOrderItem that = (GuestOrderItem) o;
        return guestOrderItemId == that.guestOrderItemId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(guestOrderItemId, quantity);
    }

    @ManyToOne
    @JoinColumn(name = "AlbumID", referencedColumnName = "AlbumID", nullable = false)
    public Album getAlbumByAlbumId() {
        return albumByAlbumId;
    }

    public void setAlbumByAlbumId(Album albumByAlbumId) {
        this.albumByAlbumId = albumByAlbumId;
    }
}
