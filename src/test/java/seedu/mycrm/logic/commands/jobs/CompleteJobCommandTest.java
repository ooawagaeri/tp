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
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.testutil.JobBuilder;

public class CompleteJobCommandTest {
    private Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        CompleteJobCommand completeJobCommand = new CompleteJobCommand(outOfBoundIndex, null);


        assertCommandFailure(completeJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Job jobToMarkComplete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        CompleteJobCommand completeCommand = new CompleteJobCommand(INDEX_FIRST_JOB, new JobDate("13/12/2021"));

        String expectedMessage = String.format(CompleteJobCommand.MESSAGE_SUCCESS, jobToMarkComplete);

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        Job jobMarkedComplete = new JobBuilder(jobToMarkComplete).withCompletionDate("13/12/2021")
                .withCompletionStatus(true).build();
        expectedModel.setJob(jobToMarkComplete, jobMarkedComplete);
        expectedModel.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidJobFilteredList_failure() {
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS);
        Job jobToMarkComplete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        CompleteJobCommand completeCommand = new CompleteJobCommand(INDEX_FIRST_JOB, new JobDate("13/12/2021"));

        String expectedMessage = Messages.MESSAGE_INVALID_JOB_COMPLETE_REQUEST;

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS);

        assertCommandFailure(completeCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        CompleteJobCommand completeJobCommand = new CompleteJobCommand(INDEX_SECOND_JOB, null);

        // same object -> returns true
        assertTrue(completeJobCommand.equals(completeJobCommand));

        // same values -> returns true
        CompleteJobCommand completeJobCommandCopy = new CompleteJobCommand(INDEX_SECOND_JOB, null);
        assertTrue(completeJobCommand.equals(completeJobCommandCopy));

        // different types -> returns false
        assertFalse(completeJobCommand.equals(1));

        // null -> returns false
        assertFalse(completeJobCommand.equals(null));
    }
}
