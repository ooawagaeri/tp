package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.mail.Template;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getPersonList();

    /**
     * Returns an unmodifiable view of the templates list.
     * This list will not contain any template persons.
     */
    ObservableList<Template> getTemplateList();
}
