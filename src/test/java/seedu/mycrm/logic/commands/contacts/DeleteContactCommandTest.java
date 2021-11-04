package seedu.mycrm.logic.commands.contacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.mycrm.testutil.TypicalContacts.getLinkedJobMyCrm;
import static seedu.mycrm.testutil.TypicalContacts.getOneTypicalMyCrm;
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
 * {@code DeleteCommand}.
 */
public class DeleteContactCommandTest {

    private final Model modelTypicalContact = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    private final Model modelOneTypicalContact = new ModelManager(getOneTypicalMyCrm(), new UserPrefs());
    private final Model modelContactLinkedJob = new ModelManager(getLinkedJobMyCrm(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToDelete = modelTypicalContact.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        DeleteContactCommand deleteCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);

        ModelManager expectedModel = new ModelManager(modelTypicalContact.getMyCrm(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);

        assertCommandSuccess(deleteCommand, modelTypicalContact, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelTypicalContact.getFilteredContactList().size() + 1);
        DeleteContactCommand deleteCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelTypicalContact, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Contact contactToDelete = modelOneTypicalContact.getFilteredContactList()
                        .get(INDEX_FIRST_CONTACT.getZeroBased());
        DeleteContactCommand deleteCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);

        ModelManager expectedModel = new ModelManager(modelOneTypicalContact.getMyCrm(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);

        showNoContact(expectedModel);

        assertCommandSuccess(deleteCommand, modelOneTypicalContact, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(modelTypicalContact, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < modelTypicalContact.getMyCrm().getContactList().size());

        DeleteContactCommand deleteCommand = new DeleteContactCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelTypicalContact, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDeleteContactLinkedToJob_throwsCommandException() {
        DeleteContactCommand deleteCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(deleteCommand, modelContactLinkedJob, Messages.MESSAGE_INVALID_CONTACT_DELETE_REQUEST);
    }

    @Test
    public void equals() {
        DeleteContactCommand deleteFirstCommand = new DeleteContactCommand(INDEX_FIRST_CONTACT);
        DeleteContactCommand deleteSecondCommand = new DeleteContactCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteContactCommand deleteFirstCommandCopy = new DeleteContactCommand(INDEX_FIRST_CONTACT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.updateFilteredContactList(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }
}
