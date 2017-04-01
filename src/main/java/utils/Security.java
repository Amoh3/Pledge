package utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Abdel on 2017-03-08.
 */
public class Security {

    static ArrayList<String> queryTerms = new ArrayList<String>(
            Arrays.asList("SELECT", "DROP", "CREATE", "INSERT", "DELETE", "ALTER", ";"));

    public static boolean isSafeFromSQLInjection(String queryParameter) {

        queryParameter = queryParameter.toUpperCase();
        for (String queryTerm : queryTerms) {
            if (queryParameter.contains(queryTerm)) {
                return false;
            }
        }

        return true;
    }
}
