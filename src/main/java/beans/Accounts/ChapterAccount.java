package beans.Accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Abdel on 2017-03-04.
 * account
 */
@Entity
@Table(name = "account_chapter")
public class ChapterAccount {


    private int account_id;
    private String school_name;
    private String password;
    private String schoolDistrict;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    @Column(name = "school_name", nullable = false)
    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String username) {
        this.school_name = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "school_district")
    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }
}
