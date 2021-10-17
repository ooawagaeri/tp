package seedu.mycrm.logic.commands.history;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.mycrm.testutil.TypicalProducts.getTypicalMyCrm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.products.ListProductCommand;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;

class HistoryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListProductCommand(), model, ListProductCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);
        assertCommandSuccess(new ListProductCommand(), model, ListProductCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
