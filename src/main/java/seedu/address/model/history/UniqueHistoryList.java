package seedu.address.model.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueHistoryList implements Iterable<History> {

    private final ObservableList<History> internalList = FXCollections.observableArrayList();
    private final ObservableList<History> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent history command as the given argument.
     */
    public boolean contains(History toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a entered command to the list.
     * The command must not already exist in the list.
     */
    public void add(History toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
        }
    }

    public void setContacts(UniqueHistoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code histories}.
     * {@code histories} must not contain duplicate commands.
     */
    public void setContacts(List<History> histories) {
        requireAllNonNull(histories);
        if (historiesAreUnique(histories)) {
            internalList.setAll(histories);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<History> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<History> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueHistoryList // instanceof handles nulls
                && internalList.equals(((UniqueHistoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code histories} contains only unique history commands.
     */
    private boolean historiesAreUnique(List<History> histories) {
        for (int i = 0; i < histories.size() - 1; i++) {
            for (int j = i + 1; j < histories.size(); j++) {
                if (histories.get(i).equals(histories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
