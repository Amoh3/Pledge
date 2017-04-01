package beans;

import beans.Accounts.ChapterAccount;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Abdel on 2017-03-04.
 * Chapter; club in skule
 */
@Entity
@Table(name = "chapter")
public class Chapter {

    private int id;
    private ChapterAccount accountChapter;
    private List<Party> parties = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    public ChapterAccount getAccountChapter() {
        return accountChapter;
    }

    public void setAccountChapter(ChapterAccount accountChapter) {
        this.accountChapter = accountChapter;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "link_chapter_party", joinColumns = @JoinColumn(name = "chapter_id"), inverseJoinColumns = @JoinColumn(name = "party_id"))
    private List<Party> getParties() {
        return parties;
    }

    private void setParties(List<Party> parties) {
        this.parties = parties;
    }

    @Transient
    public List<Party> getExecutives() {
        if (parties != null) {
            return parties.stream().filter(party -> !Party.PartyType.STUDENT_VOLUNTEER.equals(party.getType())).collect(Collectors.toList());
        }
        return null;
    }

    @Transient
    public List<Party> getVolunteers() {
        if (parties != null) {
            return  parties.stream().filter(party -> Party.PartyType.STUDENT_VOLUNTEER.equals(party.getType())).collect(Collectors.toList());
        }
        return null;
    }

    @Transient
    public void setExecutives(List<Party> executiveParties) {
        if (executiveParties != null) {
            executiveParties = executiveParties.stream().filter(party -> !Party.PartyType.STUDENT_VOLUNTEER.equals(party.getType())).collect(Collectors.toList());
            parties.addAll(executiveParties);
        }
    }

    @Transient
    public void setVolunteers(List<Party> volunteerParties) {
        if (volunteerParties != null) {
            volunteerParties = volunteerParties.stream().filter(party -> Party.PartyType.STUDENT_VOLUNTEER.equals(party.getType())).collect(Collectors.toList());
            ;
            parties.addAll(volunteerParties);
        }
    }

}
