package seedu.mycrm.logic.commands.mails;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showTemplateAtIndex;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.mycrm.testutil.TypicalTemplates.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTemplateCommand.
 */
public class ListTemplateCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTemplateCommand(), model, ListTemplateCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);
        assertCommandSuccess(new ListTemplateCommand(), model, ListTemplateCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
