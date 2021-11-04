package seedu.mycrm.logic.commands.contacts;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;
import static seedu.mycrm.testutil.TypicalContacts.getTypicalMyCrm;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
        expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_listAllContactsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_listAllContactsIsFiltered_showsSameList() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_listNotHiddenContactsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS),
                model, ListContactCommand.MESSAGE_SUCCESS_NOT_HIDDEN, expectedModel);
    }

    @Test
    public void execute_listNotHiddenContactsIsFiltered_showsSameList() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        assertCommandSuccess(new ListContactCommand(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS),
                model, ListContactCommand.MESSAGE_SUCCESS_NOT_HIDDEN, expectedModel);
    }
}
