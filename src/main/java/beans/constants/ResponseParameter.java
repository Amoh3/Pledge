package beans.constants;

/**
 * Created by Abdel on 2017-03-14.
 */
public enum ResponseParameter {
    RESPONSE_STATUS("RST");

    private final String value;

    ResponseParameter(String val) {
        value = val;
    }

    public boolean equals(ResponseParameter responseParameter) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return this.toString().equals(responseParameter.toString());
    }

    public String toString() {
        return value;
    }
}