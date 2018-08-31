package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "guest_order", schema = "music_store", catalog = "")
public class GuestOrder {
    private int orderId;
    private String date;
    private GuestCustomer guestCustomerByGuestCustomerId;

    @Id
    @Column(name = "OrderID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "Date", nullable = true, length = 20)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestOrder that = (GuestOrder) o;
        return orderId == that.orderId &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, date);
    }

    @ManyToOne
    @JoinColumn(name = "GuestCustomerID", referencedColumnName = "GuestCustomerID", nullable = false)
    public GuestCustomer getGuestCustomerByGuestCustomerId() {
        return guestCustomerByGuestCustomerId;
    }

    public void setGuestCustomerByGuestCustomerId(GuestCustomer guestCustomerByGuestCustomerId) {
        this.guestCustomerByGuestCustomerId = guestCustomerByGuestCustomerId;
    }
}
