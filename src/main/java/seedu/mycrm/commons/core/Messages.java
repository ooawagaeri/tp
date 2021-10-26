package seedu.mycrm.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_INVALID_CONTACT_HIDE_REQUEST = "The contact has already been hidden!";
    public static final String MESSAGE_INVALID_CONTACT_UNDO_HIDE_REQUEST = "The contact has not been hidden!";
    public static final String MESSAGE_INVALID_CONTACT_DELETE_REQUEST = "The contact cannot be deleted! "
            + "Because it has been linked to one or more jobs!";
    public static final String MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX = "The template index provided is invalid";
    public static final String MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX = "The product index provided is invalid";
    public static final String MESSAGE_INVALID_JOB_DISPLAYED_INDEX = "The job index provided is invalid";
    public static final String MESSAGE_INVALID_JOB_COMPLETE_REQUEST = "The job has already been completed!";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_JOBS_LISTED_OVERVIEW = "%1$d jobs listed!";
    public static final String MESSAGE_REMOVE_LINKED_PRODUCT = "The product is linked to one or more job";

}
