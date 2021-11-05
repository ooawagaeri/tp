package seedu.mycrm.model;

import javafx.collections.ObservableList;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;

/**
 * Unmodifiable view of an myCrm
 */
public interface ReadOnlyMyCrm {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the templates list.
     * This list will not contain any template contacts.
     */
    ObservableList<Template> getTemplateList();

    /**
     * Returns an unmodifiable view of the products list.
     * This list will not contain any duplicate products.
     */
    ObservableList<Product> getProductList();

    /**
     * Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */
    ObservableList<Job> getJobList();

    /**
     * Returns an unmodifiable view of the history commands list.
     * This list will not contain any duplicate commands.
     */
    ObservableList<History> getHistoryList();

    /**
     +Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */
    ObservableList<Mail> getMailList();

    /**
     +Returns an unmodifiable view of the top three product list.
     * This list will not contain any duplicate products.
     */
    ObservableList<Product> getTopThreeProductList();
}

