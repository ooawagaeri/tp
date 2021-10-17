package seedu.mycrm.model.mail.exceptions;

/**
 * Signals that the operation will result in duplicate Template (Templates are considered duplicates if they
 * have the same header).
 */
public class DuplicateTemplateException extends RuntimeException {
    public DuplicateTemplateException() {
        super("Operation would result in duplicate templates");
    }
}
