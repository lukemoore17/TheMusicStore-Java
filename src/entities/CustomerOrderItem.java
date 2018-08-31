package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_order_item", schema = "music_store", catalog = "")
public class CustomerOrderItem {
    private int customerOrderItemId;
    private int quantity;
    private Album albumByAlbumId;

    @Id
    @Column(name = "CustomerOrderItemID", nullable = false)
    public int getCustomerOrderItemId() {
        return customerOrderItemId;
    }

    public void setCustomerOrderItemId(int customerOrderItemId) {
        this.customerOrderItemId = customerOrderItemId;
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
        CustomerOrderItem that = (CustomerOrderItem) o;
        return customerOrderItemId == that.customerOrderItemId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerOrderItemId, quantity);
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
