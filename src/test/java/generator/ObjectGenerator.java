package generator;

import beans.Accounts.ChapterAccount;
import beans.Accounts.VolunteerAccount;
import beans.Chapter;
import beans.Party;
import utils.RandomString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdel on 2017-03-31.
 *
 */
public class ObjectGenerator {

    public static ChapterAccount generateAccount() {
        ChapterAccount accountChapter = new ChapterAccount();
        accountChapter.setSchool_name("school_" + (int) (Math.random() * 1000));
        accountChapter.setPassword("password_test");
        accountChapter.setSchoolDistrict("TDSB");
        return accountChapter;
    }

    public static Party generateParty(Party.PartyType partyType, VolunteerAccount volunteerAccount) {
        Party party = new Party();
        party.setFirstName("first_name");
        party.setLastName("last_name");
        if (partyType != null) {
            party.setType(partyType);
        } else {
            party.setType(Party.PartyType.PRESIDENT);
        }

        if (volunteerAccount == null){
            volunteerAccount = new VolunteerAccount();
            volunteerAccount.setEmail(new RandomString(5).nextString() + "@gmail.com");
            volunteerAccount.setPassword("vPass");
        }
        party.setVolunteerAccount(volunteerAccount);
        return party;
    }

    public static Chapter generateChapter(ChapterAccount accountChapter, Party... parties) {
        Chapter chapter = new Chapter();

        if (accountChapter != null) {
            chapter.setAccountChapter(accountChapter);
        } else {
            chapter.setAccountChapter(generateAccount());
        }

        chapter.setExecutives(new ArrayList<>());
        chapter.setVolunteers(new ArrayList<>());

        if (parties != null && parties.length != 0) {
            for (Party party : parties) {
                if (party.getType().equals(Party.PartyType.STUDENT_VOLUNTEER)) {
                    chapter.getVolunteers().add(party);
                } else {
                    chapter.getExecutives().add(party);
                }
            }
        }

        if (chapter.getVolunteers().size() == 0) {
            List<Party> volunteers = new ArrayList<>();
            for (int size = 3; size > 0; size--) {
                volunteers.add(generateParty(Party.PartyType.STUDENT_VOLUNTEER, null));
            }
            chapter.setVolunteers(volunteers);
        }

        if (chapter.getExecutives().size() == 0) {
            List<Party> executives = new ArrayList<>();
            executives.add(generateParty(Party.PartyType.PRESIDENT, null));
            executives.add(generateParty(Party.PartyType.VICE_PRESIDENT, null));
            executives.add(generateParty(Party.PartyType.PUBLICITY, null));
            executives.add(generateParty(Party.PartyType.SECRETARY, null));
            executives.add(generateParty(Party.PartyType.TREASURER, null));
            executives.add(generateParty(Party.PartyType.EVENTS_COORDINATOR, null));
            chapter.setExecutives(executives);
        }

        return chapter;
    }
}
