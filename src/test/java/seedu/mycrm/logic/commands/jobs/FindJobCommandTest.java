package seedu.mycrm.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.commons.core.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalJobs.COMPLETED;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.JobContainsKeywordsPredicate;
import seedu.mycrm.testutil.TypicalJobs;

class FindJobCommandTest {
    private Model model = new ModelManager(TypicalJobs.getTypicalMyCrm(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalJobs.getTypicalMyCrm(), new UserPrefs());

    @Test
    public void equals() {
        JobContainsKeywordsPredicate firstPredicate =
                new JobContainsKeywordsPredicate(Collections.singletonList("first"));
        JobContainsKeywordsPredicate secondPredicate =
                new JobContainsKeywordsPredicate(Collections.singletonList("second"));

        FindJobCommand findFirstCommand = new FindJobCommand(firstPredicate);
        FindJobCommand findSecondCommand = new FindJobCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindJobCommand findFirstCommandCopy = new FindJobCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noJobFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        JobContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindJobCommand command = new FindJobCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_multipleKeywords_multipleJobsFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 1);
        JobContainsKeywordsPredicate predicate = preparePredicate("Completed");
        FindJobCommand command = new FindJobCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(COMPLETED), model.getFilteredJobList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private JobContainsKeywordsPredicate preparePredicate(String userInput) {
        return new JobContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
