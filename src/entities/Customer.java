package entities;

import javax.persistence.*;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "getUserByUsername",
                procedureName = "my_spS_getUserByUsername",
                resultClasses = { Customer.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_username",
                                type = String.class,
                                mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "createNewUser",
                procedureName = "my_spI_createNewUser",
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
                                name = "_username",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_password",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_email",
                                type = String.class,
                                mode = ParameterMode.IN)
                })
})

@Entity
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;

    @Id
    @Column(name = "CustomerID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, firstName, lastName);
    }
}
