package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "createNewAddress",
                procedureName = "my_spI_createNewAddress_ReturnAddressID",
                parameters = {
                        @StoredProcedureParameter(
                                name = "_firstname",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_lastname",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_addressLineOne",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_addressLineTwo",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_city",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_state",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_zip",
                                type = String.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "createNewUserAddress",
                procedureName = "my_spI_createNewCustomerAddress",
                parameters = {
                        @StoredProcedureParameter(
                                name = "_customerID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_addressID",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "getAddressByAddressID",
                procedureName = "my_spS_getAddressByAddressID",
                resultClasses = { Address.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_addressID",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "getUserAddresses",
                procedureName = "my_spS_getCustomerAddressesByCustomerID",
                resultClasses = { Address.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_customerID",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                })
})

@Entity
public class Address {
    private int addressId;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private Collection<CustomerAddress> customerAddressesByAddressId;
    private Collection<CustomerOrder> customerOrdersByAddressId;
    private Collection<CustomerOrder> customerOrdersByAddressId_0;
    private Collection<GuestCustomer> guestCustomersByAddressId;
    private Collection<GuestCustomer> guestCustomersByAddressId_0;

    @Id
    @Column(name = "AddressID", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, length = 25)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false, length = 25)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "AddressLine1", nullable = false, length = 50)
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "AddressLine2", nullable = true, length = 50)
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "City", nullable = false, length = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "State", nullable = false, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "Zip", nullable = false, length = 5)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId &&
                Objects.equals(firstName, address.firstName) &&
                Objects.equals(lastName, address.lastName) &&
                Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(addressId, firstName, lastName, addressLine1, addressLine2, city, state, zip);
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<CustomerAddress> getCustomerAddressesByAddressId() {
        return customerAddressesByAddressId;
    }

    public void setCustomerAddressesByAddressId(Collection<CustomerAddress> customerAddressesByAddressId) {
        this.customerAddressesByAddressId = customerAddressesByAddressId;
    }

    @OneToMany(mappedBy = "addressByShipAddressId")
    public Collection<CustomerOrder> getCustomerOrdersByAddressId() {
        return customerOrdersByAddressId;
    }

    public void setCustomerOrdersByAddressId(Collection<CustomerOrder> customerOrdersByAddressId) {
        this.customerOrdersByAddressId = customerOrdersByAddressId;
    }

    @OneToMany(mappedBy = "addressByBillAddressId")
    public Collection<CustomerOrder> getCustomerOrdersByAddressId_0() {
        return customerOrdersByAddressId_0;
    }

    public void setCustomerOrdersByAddressId_0(Collection<CustomerOrder> customerOrdersByAddressId_0) {
        this.customerOrdersByAddressId_0 = customerOrdersByAddressId_0;
    }

    @OneToMany(mappedBy = "addressByShipAddressId")
    public Collection<GuestCustomer> getGuestCustomersByAddressId() {
        return guestCustomersByAddressId;
    }

    public void setGuestCustomersByAddressId(Collection<GuestCustomer> guestCustomersByAddressId) {
        this.guestCustomersByAddressId = guestCustomersByAddressId;
    }

    @OneToMany(mappedBy = "addressByBillAddressId")
    public Collection<GuestCustomer> getGuestCustomersByAddressId_0() {
        return guestCustomersByAddressId_0;
    }

    public void setGuestCustomersByAddressId_0(Collection<GuestCustomer> guestCustomersByAddressId_0) {
        this.guestCustomersByAddressId_0 = guestCustomersByAddressId_0;
    }
}
