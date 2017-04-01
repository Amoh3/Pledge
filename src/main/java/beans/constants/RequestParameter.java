package beans.constants;

/**
 * Created by Abdel on 2017-03-14.
 */
public enum RequestParameter {


    // party parameters
    USERNAME("USR"), PASSWORD("PAS"), SCHOOL("SKL");

    private final String value;

    RequestParameter(String val) {
        value = val;
    }

    public boolean equals(RequestParameter requestParameter) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return this.toString().equals(requestParameter.toString());
    }

    public String toString() {
        return value;
    }
}
