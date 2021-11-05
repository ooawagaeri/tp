package seedu.mycrm.model.mail;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.mycrm.model.mail.exceptions.MailNotFoundException;

/**
 * Placeholder list of mails to display only the latest mail generated.
 * A list of mails that enforces uniqueness between its elements and does not allow nulls.
 * A mail is considered unique by comparing using {@code Mail#isSameMail(Mail)}. As such, adding and
 * updating of mails uses Mail#isSameMail(Mail) for equality to ensure that the mail being added
 * or updated is unique in terms of identity in the UniqueMailList. However, the removal of a mail uses
 * Mail#equals (Object) to ensure that the mail with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Mail#isSameMail(Mail)
 */
public class UniqueMailList implements Iterable<Mail> {

    private final ObservableList<Mail> internalList = FXCollections.observableArrayList();
    private final ObservableList<Mail> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent mail as the given argument.
     *
     * @param toCheck target mail to check.
     */
    public boolean contains(Mail toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMail);
    }

    /**
     * Adds a mail to the list.
     * The number of mails in list cannot exceed 1.
     *
     * @param toAdd target mail to add.
     */
    public void add(Mail toAdd) {
        requireNonNull(toAdd);

        // Remove's previous entry
        if (internalList.size() > 0) {
            remove(internalList.get(0));
        }

        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent mail from the list.
     * The mail must exist in the list.
     *
     * @param toRemove target mail to remove.
     */
    public void remove(Mail toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MailNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Mail> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Mail> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMailList // instanceof handles nulls
                && internalList.equals(((UniqueMailList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
