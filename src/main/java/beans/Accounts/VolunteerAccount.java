package beans.Accounts;

import javax.persistence.*;

/**
 * Created by Abdel on 2017-03-31.
 */

@Entity
@Table(name = "account_volunteer")
public class VolunteerAccount {

    int id;
    String email;
    String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_volunteer_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
