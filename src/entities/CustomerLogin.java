package entities;

import javax.persistence.*;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "verifyCredentials",
                procedureName = "my_spS_verifyCredentials",
                resultClasses = { CustomerLogin.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "_username",
                                type = String.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_password",
                                type = String.class,
                                mode = ParameterMode.IN
                        )
                }),
        @NamedStoredProcedureQuery(
                name = "changePassword",
                procedureName = "my_spU_customerPassword",
                parameters = {
                        @StoredProcedureParameter(
                                name = "_customerID",
                                type = Integer.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "_password",
                                type = String.class,
                                mode = ParameterMode.IN
                        )
                })
})

@Entity
@Table(name = "customer_login", schema = "music_store", catalog = "")
public class CustomerLogin {
    private int customerId;
    private String username;
    private String password;
    private String email;

    @Id
    @Column(name = "CustomerID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "Username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 254)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerLogin that = (CustomerLogin) o;
        return customerId == that.customerId &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, username, password, email);
    }
}
