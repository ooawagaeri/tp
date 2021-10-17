package seedu.mycrm.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.mycrm.testutil.TypicalJobs.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.Job;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteJobCommand}.
 */
public class DeleteJobCommandTest {

    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        DeleteJobCommand deleteJobCommand = new DeleteJobCommand(outOfBoundIndex);

        assertCommandFailure(deleteJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteJobCommand deleteCommand = new DeleteJobCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteJobCommand.MESSAGE_DELETE_JOB_SUCCESS, jobToDelete);

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.deleteJob(jobToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteJobCommand deleteFirstCommand = new DeleteJobCommand(INDEX_FIRST_JOB);
        DeleteJobCommand deleteSecondCommand = new DeleteJobCommand(INDEX_SECOND_JOB);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteJobCommand deleteFirstCommandCopy = new DeleteJobCommand(INDEX_FIRST_JOB);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no jobs left.
     */
    private void showNoJob(Model model) {
        model.updateFilteredJobList(p -> false);
        assertTrue(model.getFilteredJobList().isEmpty());
    }
}
