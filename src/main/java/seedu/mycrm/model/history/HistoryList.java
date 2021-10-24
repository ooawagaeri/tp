package seedu.mycrm.model.history;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoryList implements Iterable<History> {

    private final ObservableList<History> internalList = FXCollections.observableArrayList();
    private final ObservableList<History> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a entered command to the list.
     * The command must not already exist in the list.
     */
    public void add(History toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Clear all items in internalList of History.
     */
    public void clearHistory() {
        internalList.clear();
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
                || (other instanceof HistoryList // instanceof handles nulls
                && internalList.equals(((HistoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
