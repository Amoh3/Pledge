package beans.constants;

/**
 * Created by Abdel on 2017-03-14.
 */
public enum ResponseMessage {
    AUTHENTICATED("ACT"),
    OPERATION_SUCCESSFUL("OP000"),
    ERROR_USERNAME_EXISTS("ERR001"),
    ERROR_INCORRECT_CREDENTIALS("ERR002"),
    ERROR_GETTING_CONTRACTORS("ER003");

    private final String value;

    ResponseMessage(String val) {
        value = val;
    }

    public boolean equals(ResponseMessage ResponseMessage) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return this.toString().equals(ResponseMessage.toString());
    }

    public String toString() {
        return value;
    }
}