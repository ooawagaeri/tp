package seedu.address.logic.commands.mails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTemplateAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;
import static seedu.address.testutil.TypicalTemplates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mail.Template;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTemplateCommand}.
 */
public class DeleteTemplateCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Template templateToDelete = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        DeleteTemplateCommand deleteTemplateCommand = new DeleteTemplateCommand(INDEX_FIRST_TEMPLATE);

        String expectedMessage = String.format(DeleteTemplateCommand.MESSAGE_DELETE_TEMPLATE_SUCCESS, templateToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTemplate(templateToDelete);

        assertCommandSuccess(deleteTemplateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTemplateList().size() + 1);
        DeleteTemplateCommand deleteTemplateCommand = new DeleteTemplateCommand(outOfBoundIndex);

        assertCommandFailure(deleteTemplateCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);

        Template templateToDelete = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        DeleteTemplateCommand deleteTemplateCommand = new DeleteTemplateCommand(INDEX_FIRST_TEMPLATE);

        String expectedMessage = String.format(DeleteTemplateCommand.MESSAGE_DELETE_TEMPLATE_SUCCESS, templateToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTemplate(templateToDelete);
        showNoTemplate(expectedModel);

        assertCommandSuccess(deleteTemplateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);

        Index outOfBoundIndex = INDEX_SECOND_TEMPLATE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTemplateList().size());

        DeleteTemplateCommand deleteTemplateCommand = new DeleteTemplateCommand(outOfBoundIndex);

        assertCommandFailure(deleteTemplateCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTemplateCommand deleteFirstCommand = new DeleteTemplateCommand(INDEX_FIRST_TEMPLATE);
        DeleteTemplateCommand deleteSecondCommand = new DeleteTemplateCommand(INDEX_SECOND_TEMPLATE);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteTemplateCommand deleteFirstCommandCopy = new DeleteTemplateCommand(INDEX_FIRST_TEMPLATE);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different template -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no templates left.
     */
    private void showNoTemplate(Model model) {
        model.updateFilteredTemplateList(p -> false);

        assertTrue(model.getFilteredTemplateList().isEmpty());
    }
}
