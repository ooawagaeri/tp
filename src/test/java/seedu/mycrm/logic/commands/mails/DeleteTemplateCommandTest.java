package seedu.mycrm.logic.commands.mails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showTemplateAtIndex;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;
import static seedu.mycrm.testutil.TypicalTemplates.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.mail.Template;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTemplateCommand}.
 */
public class DeleteTemplateCommandTest {

    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Template templateToDelete = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        DeleteTemplateCommand deleteTemplateCommand = new DeleteTemplateCommand(INDEX_FIRST_TEMPLATE);

        String expectedMessage = String.format(DeleteTemplateCommand.MESSAGE_DELETE_TEMPLATE_SUCCESS, templateToDelete);

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
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

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.deleteTemplate(templateToDelete);
        showNoTemplate(expectedModel);

        assertCommandSuccess(deleteTemplateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);

        Index outOfBoundIndex = INDEX_SECOND_TEMPLATE;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyCrm().getTemplateList().size());

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
