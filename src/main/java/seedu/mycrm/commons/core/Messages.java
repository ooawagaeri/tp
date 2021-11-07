package seedu.mycrm.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid!";
    public static final String MESSAGE_INVALID_CONTACT_HIDE_REQUEST = "The contact has already been hidden! "
            + "Please enter another contact index";
    public static final String MESSAGE_INVALID_CONTACT_UNDO_HIDE_REQUEST = "The contact is not hidden! Please enter "
            + "another contact index!";
    public static final String MESSAGE_INVALID_CONTACT_DELETE_REQUEST = "The contact cannot be deleted! "
            + "Because it has been linked to one or more jobs!";
    public static final String MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX = "The template index provided is invalid";
    public static final String MESSAGE_INVALID_JOB_NO_EMAIL = "The job specified does not contain an email. Do "
            + "contact him/her through other means!";
    public static final String MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX = "The product index provided is invalid";
    public static final String MESSAGE_INVALID_JOB_DISPLAYED_INDEX = "The job index provided is invalid";
    public static final String MESSAGE_INVALID_JOB_COMPLETE_REQUEST = "The job has already been completed!";
    public static final String MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE = "Job's expected completion date cannot "
            + "be before its received date. Please provide a valid expected completion date "
            + "or change the job's received date.";
    public static final String MESSAGE_INVALID_JOB_COMPLETION_DATE = "Job's completion date cannot be before "
            + "its received date. Please provide a valid completion date or change the job's received date.";
    public static final String MESSAGE_INVALID_JOB_UNDO_COMPLETE_REQUEST = "The job has not been completed yet!";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_JOBS_LISTED_OVERVIEW = "%1$d jobs listed!";
    public static final String MESSAGE_PRODUCTS_LISTED_OVERVIEW = "%1$d products listed!";
    public static final String MESSAGE_TEMPLATES_LISTED_OVERVIEW = "%1$d templates listed!";
    public static final String MESSAGE_REMOVE_LINKED_PRODUCT = "The product is linked to one or more job.";
}
