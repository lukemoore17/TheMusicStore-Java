package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "guest_customer", schema = "music_store", catalog = "")
public class GuestCustomer {
    private int guestCustomerId;
    private Address addressByShipAddressId;
    private Address addressByBillAddressId;
    private Collection<GuestOrder> guestOrdersByGuestCustomerId;

    @Id
    @Column(name = "GuestCustomerID", nullable = false)
    public int getGuestCustomerId() {
        return guestCustomerId;
    }

    public void setGuestCustomerId(int guestCustomerId) {
        this.guestCustomerId = guestCustomerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestCustomer that = (GuestCustomer) o;
        return guestCustomerId == that.guestCustomerId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(guestCustomerId);
    }

    @ManyToOne
    @JoinColumn(name = "ShipAddressID", referencedColumnName = "AddressID", nullable = false)
    public Address getAddressByShipAddressId() {
        return addressByShipAddressId;
    }

    public void setAddressByShipAddressId(Address addressByShipAddressId) {
        this.addressByShipAddressId = addressByShipAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "BillAddressID", referencedColumnName = "AddressID", nullable = false)
    public Address getAddressByBillAddressId() {
        return addressByBillAddressId;
    }

    public void setAddressByBillAddressId(Address addressByBillAddressId) {
        this.addressByBillAddressId = addressByBillAddressId;
    }

    @OneToMany(mappedBy = "guestCustomerByGuestCustomerId")
    public Collection<GuestOrder> getGuestOrdersByGuestCustomerId() {
        return guestOrdersByGuestCustomerId;
    }

    public void setGuestOrdersByGuestCustomerId(Collection<GuestOrder> guestOrdersByGuestCustomerId) {
        this.guestOrdersByGuestCustomerId = guestOrdersByGuestCustomerId;
    }
}
