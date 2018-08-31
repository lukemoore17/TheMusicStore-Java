package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_address", schema = "music_store", catalog = "")
@IdClass(CustomerAddressPK.class)
public class CustomerAddress {
    private int customerId;
    private int addressId;
    private Address addressByAddressId;

    @Id
    @Column(name = "CustomerID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Id
    @Column(name = "AddressID", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAddress that = (CustomerAddress) o;
        return customerId == that.customerId &&
                addressId == that.addressId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, addressId);
    }

    @ManyToOne
    @JoinColumn(name = "AddressID", referencedColumnName = "AddressID", nullable = false, insertable = false, updatable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
