package seedu.mycrm.logic.commands.history;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalHistories.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;

public class ClearHistoryCommandTest {

    @Test
    public void execute_emptyHistoryList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearHistoryCommand(), model, ClearHistoryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHistoryList_success() {
        Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel.setMyCrm(new MyCrm());

        assertCommandSuccess(new ClearHistoryCommand(), model, ClearHistoryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
