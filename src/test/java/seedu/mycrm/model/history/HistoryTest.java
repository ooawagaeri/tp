package seedu.mycrm.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.mycrm.testutil.TypicalHistories.HISTORY_ONE;

import org.junit.jupiter.api.Test;

class HistoryTest {

    @Test
    void equals() {
        // same object -> returns true
        assertEquals(HISTORY_ONE, HISTORY_ONE);

        // null -> returns false
        assertNotEquals(null, HISTORY_ONE);

        // different type -> returns false
        assertNotEquals(5, HISTORY_ONE);
    }
}
