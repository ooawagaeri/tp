package seedu.mycrm.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalHistories.HISTORY_ONE;
import static seedu.mycrm.testutil.TypicalHistories.HISTORY_TWO;

import org.junit.jupiter.api.Test;

class HistoryListTest {
    private final HistoryList historyList = new HistoryList();

    @Test
    void add_nullHistory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> historyList.add(null));
    }

    @Test
    void clearHistory_emptyHistoryList_success() {
        HistoryList expectedHistoryList = new HistoryList();
        historyList.clearHistory();
        assertEquals(expectedHistoryList, historyList);
    }

    @Test
    void clearHistory_listWithHistories_success() {
        historyList.add(HISTORY_ONE);
        historyList.add(HISTORY_TWO);
        HistoryList expectedHistoryList = new HistoryList();
        historyList.clearHistory();
        assertEquals(expectedHistoryList, historyList);
    }

    @Test
    void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> historyList.asUnmodifiableObservableList().remove(0));
    }
}
