package seedu.mycrm.logic.commands.contacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.mycrm.testutil.TypicalContacts.getTypicalMyCrm;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.contact.Contact;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code HideCommand}.
 */
public class UndoHideContactCommandTest {
    // For testing normal hidden lists.
    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    /**
     * Initialization of undo hide contact test.
     */
    public UndoHideContactCommandTest() {
        setFirstContactHidden(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToUndoHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        UndoHideContactCommand undoHideCommand = new UndoHideContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(UndoHideContactCommand.MESSAGE_UNDO_HIDE_CONTACT_SUCCESS,
                contactToUndoHide);

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.undoHideContact(contactToUndoHide);

        assertCommandSuccess(undoHideCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        UndoHideContactCommand undoHideContact = new UndoHideContactCommand(outOfBoundIndex);

        assertCommandFailure(undoHideContact, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidUnHiddenUnfilteredList_throwsCommandException() {
        Contact contactToUndoHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        // set this contact as not hidden.
        contactToUndoHide.setNotHidden();
        UndoHideContactCommand undoHideContact = new UndoHideContactCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(undoHideContact, model, Messages.MESSAGE_INVALID_CONTACT_UNDO_HIDE_REQUEST);
        // modify back to accept other tests.
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToUndoHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        UndoHideContactCommand undoHideCommand = new UndoHideContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(UndoHideContactCommand.MESSAGE_UNDO_HIDE_CONTACT_SUCCESS,
                contactToUndoHide);

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.undoHideContact(contactToUndoHide);

        assertCommandSuccess(undoHideCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyCrm().getContactList().size());

        UndoHideContactCommand undoHideCommand = new UndoHideContactCommand(outOfBoundIndex);

        assertCommandFailure(undoHideCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidUnHiddenFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Contact contactToUndoHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        // set this contact to not hidden.
        contactToUndoHide.setNotHidden();
        UndoHideContactCommand undoHideContact = new UndoHideContactCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(undoHideContact, model, Messages.MESSAGE_INVALID_CONTACT_UNDO_HIDE_REQUEST);
        // modify back to accept other tests.
        contactToUndoHide.setHidden();
    }

    @Test
    public void equals() {
        UndoHideContactCommand undoHideFirstCommand = new UndoHideContactCommand(INDEX_FIRST_CONTACT);
        UndoHideContactCommand undoHideSecondCommand = new UndoHideContactCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(undoHideFirstCommand.equals(undoHideFirstCommand));

        // same values -> returns true
        UndoHideContactCommand undoHideFirstCommandCopy = new UndoHideContactCommand(INDEX_FIRST_CONTACT);
        assertTrue(undoHideFirstCommand.equals(undoHideFirstCommandCopy));

        // different types -> returns false
        assertFalse(undoHideFirstCommand.equals(1));

        // null -> returns false
        assertFalse(undoHideFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(undoHideFirstCommand.equals(undoHideSecondCommand));
    }

    /**
     *  Set first contact as hidden.
     */
    private void setFirstContactHidden(Model model) {
        Contact firstContactToHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        model.hideContact(firstContactToHide);
    }

}
