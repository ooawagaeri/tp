package seedu.mycrm.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.mycrm.testutil.TypicalJobs.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.testutil.JobBuilder;

public class UndoCompleteJobCommandTest {
    private Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        UndoCompleteJobCommand undoCompleteJobCommand = new UndoCompleteJobCommand(outOfBoundIndex);

        assertCommandFailure(undoCompleteJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS);
        Job jobToMarkIncomplete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        UndoCompleteJobCommand undoCompleteJobCommand = new UndoCompleteJobCommand(INDEX_FIRST_JOB);

        String expectedMessage = String.format(UndoCompleteJobCommand.MESSAGE_SUCCESS, jobToMarkIncomplete);

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        Job jobMarkedIncomplete = new JobBuilder(jobToMarkIncomplete).withCompletionStatus(false).build();
        jobMarkedIncomplete.setCompletionDate(null);
        expectedModel.setJob(jobToMarkIncomplete, jobMarkedIncomplete);
        expectedModel.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS);

        assertCommandSuccess(undoCompleteJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidJobFilteredList_failure() {
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
        UndoCompleteJobCommand undoCompleteJobCommand = new UndoCompleteJobCommand(INDEX_FIRST_JOB);

        String expectedMessage = Messages.MESSAGE_INVALID_JOB_UNDO_COMPLETE_REQUEST;

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);

        assertCommandFailure(undoCompleteJobCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        UndoCompleteJobCommand undoCompleteJobCommand = new UndoCompleteJobCommand(INDEX_FIRST_JOB);

        // same object -> returns true
        assertTrue(undoCompleteJobCommand.equals(undoCompleteJobCommand));

        // same values -> returns true
        UndoCompleteJobCommand undoCompleteJobCommandCopy = new UndoCompleteJobCommand(INDEX_FIRST_JOB);
        assertTrue(undoCompleteJobCommandCopy.equals(undoCompleteJobCommand));

        // different types -> returns false
        assertFalse(undoCompleteJobCommand.equals(1));

        // null -> returns false
        assertFalse(undoCompleteJobCommand.equals(null));
    }
}
