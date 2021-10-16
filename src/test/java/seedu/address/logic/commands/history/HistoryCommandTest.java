package seedu.address.logic.commands.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.products.ListProductCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalProducts.getTypicalMyCrm;

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