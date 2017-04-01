package beans;

import beans.Accounts.VolunteerAccount;

import javax.persistence.*;

/**
 * Created by Abdel on 2017-03-31.
 */
@Entity
@Table(name = "party")
public class Party {

    private int id;
    private String firstName;
    private String lastName;

    private String partyType;

    private VolunteerAccount volunteerAccount;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "party_type")
    private String getPartyType() {
        return partyType;
    }

    private void setPartyType(String partyTypeVal) {
        this.partyType = partyTypeVal;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_volunteer_id")
    public VolunteerAccount getVolunteerAccount() {
        return volunteerAccount;
    }

    public void setVolunteerAccount(VolunteerAccount volunteerAccount) {
        this.volunteerAccount = volunteerAccount;
    }

    @Transient
    public PartyType getType(){
        return PartyType.fromValue(partyType);
    }

    @Transient
    public void setType(PartyType partyType){
        this.partyType = partyType.toString();
    }



    public enum PartyType{
        PRESIDENT("PRE"),
        VICE_PRESIDENT("VPR"),
        PUBLICITY("PUB"),
        TREASURER("TRE"),
        SECRETARY("SEC"),
        EVENTS_COORDINATOR("EVC"),
        STUDENT_VOLUNTEER("SVO");

        String value;

        PartyType(String value) {
            this.value = value;
        }

        public boolean equals(PartyType partyType) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return this.toString().equals(partyType.toString());
        }

        public String toString() {
            return value;
        }

        public static PartyType fromValue(String value) {
            for(PartyType e : PartyType.values()){
                if(e.value.equals(value)) return e;
            }
            return null;
        }



    }

}
