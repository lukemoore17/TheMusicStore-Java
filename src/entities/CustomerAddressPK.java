package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CustomerAddressPK implements Serializable {
    private int customerId;
    private int addressId;

    @Column(name = "CustomerID", nullable = false)
    @Id
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(name = "AddressID", nullable = false)
    @Id
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
        CustomerAddressPK that = (CustomerAddressPK) o;
        return customerId == that.customerId &&
                addressId == that.addressId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, addressId);
    }
}
