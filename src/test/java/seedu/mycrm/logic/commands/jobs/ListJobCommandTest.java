package seedu.mycrm.logic.commands.jobs;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
import static seedu.mycrm.testutil.TypicalTemplates.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListJobCommand.
 */
public class ListJobCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListJobCommand(PREDICATE_SHOW_ALL_INCOMPLETE_JOBS), model,
                ListJobCommand.MESSAGE_SUCCESS_ONLY_PENDING, expectedModel);
    }

}
