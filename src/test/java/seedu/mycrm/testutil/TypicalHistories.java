package seedu.mycrm.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.history.History;

public class TypicalHistories {

    public static final History HISTORY_ONE = new HistoryBuilder(HistoryBuilder.DefaultHistoryIndex.ONE).build();
    public static final History HISTORY_TWO = new HistoryBuilder(HistoryBuilder.DefaultHistoryIndex.TWO).build();
    public static final History HISTORY_THREE = new HistoryBuilder(HistoryBuilder.DefaultHistoryIndex.THREE).build();
    public static final History HISTORY_FOUR = new HistoryBuilder(HistoryBuilder.DefaultHistoryIndex.FOUR).build();

    private TypicalHistories() {}

    /**
     * Returns an {@code MyCrm} with all the typical histories.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm myCrm = new MyCrm();
        for (History h: getTypicalHistories()) {
            myCrm.addHistory(h);
        }
        return myCrm;
    }

    public static ArrayList<History> getTypicalHistories() {
        return new ArrayList<>(Arrays.asList(HISTORY_ONE, HISTORY_TWO, HISTORY_THREE, HISTORY_FOUR));
    }
}
