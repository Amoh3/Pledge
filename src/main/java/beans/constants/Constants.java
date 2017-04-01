package beans.constants;

import beans.Party;

import java.util.HashMap;
import java.util.Map;

import static beans.Party.PartyType;

/**
 * Created by Abdel on 2017-03-12.
 */
public class Constants {

    public static String DATE_FORMAT_PATTERN = "mm-dd-yyy";
    public static String DELIMITER = "!~,~!";

    public static Map<Party.PartyType, String> roleTypeDescription = new HashMap<Party.PartyType, String>();

    static {

        roleTypeDescription.put(Party.PartyType.PRESIDENT, "SAMPLE PRESIDENT ROLE");
        roleTypeDescription.put(Party.PartyType.PUBLICITY, "SAMPLE PUBLICITY ROLE");
        roleTypeDescription.put(Party.PartyType.SECRETARY, "SAMPLE SECRETARY ROLE");
        roleTypeDescription.put(Party.PartyType.TREASURER, "SAMPLE TREASURER ROLE");
        roleTypeDescription.put(Party.PartyType.VICE_PRESIDENT,"SAMPLE VICE_PRES ROLE");
        roleTypeDescription.put(PartyType.EVENTS_COORDINATOR,"SAMPLE EVENTS_CORD ROLE");
        roleTypeDescription.put(PartyType.STUDENT_VOLUNTEER, "SAMPLE VOLUNTEER");

    }
}
