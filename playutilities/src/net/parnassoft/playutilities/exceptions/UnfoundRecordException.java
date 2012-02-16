/**
 * Author: OMAROMAN
 * Date: 10/27/11
 * Time: 11:17 AM
 */
package net.parnassoft.playutilities.exceptions;

public class UnfoundRecordException extends  Exception {

     private String error;

    /**
     * Default constructor - initializes instance variable to unknown
     */
    public UnfoundRecordException() {
        super();             // call superclass constructor
        error = "unknown";
    }

    /**
     * Constructor receives some kind of message that is saved in an instance variable.
     * @param err -
     */
    public UnfoundRecordException(String err) {
        super(err);     // call super class constructor
        error = err;  // save message
    }

    /**
     * public method, callable by exception catcher. It returns the error message.
     * @return -
     */
    public String getError() {
        return error;
    }
}
