package seedu.mycrm.logic.commands.contacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showContactAtIndex;
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
public class HideContactCommandTest {
    // For testing normal unhidden lists.
    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        HideContactCommand hideCommand = new HideContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(HideContactCommand.MESSAGE_HIDE_CONTACT_SUCCESS, contactToHide);

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.hideContact(contactToHide);

        assertCommandSuccess(hideCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        HideContactCommand hideContact = new HideContactCommand(outOfBoundIndex);

        assertCommandFailure(hideContact, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidHiddenUnfilteredList_throwsCommandException() {
        Contact contactToHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        // set this contact hidden.
        contactToHide.setHidden();
        HideContactCommand hideContact = new HideContactCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(hideContact, model, Messages.MESSAGE_INVALID_CONTACT_HIDE_REQUEST);
        // modify back to not hidden to accept other test cases.
        contactToHide.setNotHidden();
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        HideContactCommand hideCommand = new HideContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(HideContactCommand.MESSAGE_HIDE_CONTACT_SUCCESS, contactToHide);

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.hideContact(contactToHide);

        assertCommandSuccess(hideCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidHiddenFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToHide = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        // set this contact hidden.
        contactToHide.setHidden();
        HideContactCommand hideContact = new HideContactCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(hideContact, model, Messages.MESSAGE_INVALID_CONTACT_HIDE_REQUEST);
        // modify back to not hidden to accept other test cases.
        contactToHide.setNotHidden();
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyCrm().getContactList().size());

        HideContactCommand hideCommand = new HideContactCommand(outOfBoundIndex);

        assertCommandFailure(hideCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        HideContactCommand hideFirstCommand = new HideContactCommand(INDEX_FIRST_CONTACT);
        HideContactCommand hideSecondCommand = new HideContactCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(hideFirstCommand.equals(hideFirstCommand));

        // same values -> returns true
        HideContactCommand hideFirstCommandCopy = new HideContactCommand(INDEX_FIRST_CONTACT);
        assertTrue(hideFirstCommand.equals(hideFirstCommandCopy));

        // different types -> returns false
        assertFalse(hideFirstCommand.equals(1));

        // null -> returns false
        assertFalse(hideFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(hideFirstCommand.equals(hideSecondCommand));
    }

}
