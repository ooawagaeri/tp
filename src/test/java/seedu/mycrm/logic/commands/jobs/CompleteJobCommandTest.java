package seedu.mycrm.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.mycrm.testutil.TypicalJobs.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;

public class CompleteJobCommandTest {
    private Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        CompleteJobCommand completeJobCommand = new CompleteJobCommand(INDEX_SECOND_JOB);


        assertCommandFailure(completeJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompleteJobCommand completeJobCommand = new CompleteJobCommand(INDEX_SECOND_JOB);

        // same object -> returns true
        assertTrue(completeJobCommand.equals(completeJobCommand));

        // same values -> returns true
        CompleteJobCommand completeJobCommandCopy = new CompleteJobCommand(INDEX_SECOND_JOB);
        assertTrue(completeJobCommand.equals(completeJobCommandCopy));

        // different types -> returns false
        assertFalse(completeJobCommand.equals(1));

        // null -> returns false
        assertFalse(completeJobCommand.equals(null));
    }

}
