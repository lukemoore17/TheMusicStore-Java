package entities;

import javax.persistence.*;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "createUserOrder",
                procedureName = "my_spI_createNewCustomerOrder_ReturnOrderID",
                parameters = {
                        @StoredProcedureParameter(
                                name = "_customerID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_shipAddressID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_billAddressID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_date",
                                type = String.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "createUserOrderItem",
                procedureName = "my_spI_createNewCustomerOrderItem",
                parameters = {
                        @StoredProcedureParameter(
                                name = "_orderID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_albumID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_quantity",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "getUserOrders",
                procedureName = "my_spS_getCustomerOrdersByCustomerID",
                resultClasses = { CustomerOrder.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_customerID",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "getUserOrderItemsByOrderID",
                procedureName = "my_spS_getCustomerOrderItemsByOrderID",
                resultClasses = { CustomerOrderItem.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_orderID",
                                type = Integer.class,
                                mode = ParameterMode.IN)
                })
})

@Entity
@Table(name = "customer_order", schema = "music_store", catalog = "")
public class CustomerOrder {
    private int orderId;
    private String date;
    private Address addressByShipAddressId;
    private Address addressByBillAddressId;

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
        CustomerOrder that = (CustomerOrder) o;
        return orderId == that.orderId &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, date);
    }

    @ManyToOne
    @JoinColumn(name = "ShipAddressID", referencedColumnName = "AddressID")
    public Address getAddressByShipAddressId() {
        return addressByShipAddressId;
    }

    public void setAddressByShipAddressId(Address addressByShipAddressId) {
        this.addressByShipAddressId = addressByShipAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "BillAddressID", referencedColumnName = "AddressID")
    public Address getAddressByBillAddressId() {
        return addressByBillAddressId;
    }

    public void setAddressByBillAddressId(Address addressByBillAddressId) {
        this.addressByBillAddressId = addressByBillAddressId;
    }
}
