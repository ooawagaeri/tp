package seedu.mycrm.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalJobs.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.testutil.JobBuilder;


class AddJobCommandTest {

    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_newJob_success() throws CommandException {
        Job newJob = new JobBuilder().withJobDescription("Fix Intel CPU")
            .withFee("$1000.00")
            .withReceivedDate("01/10/2021")
            .withExpectedCompletionDate("11/10/2021")
            .withClient(null)
            .withProduct(null)
            .build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);;
        // INTEL_CPU
        Index productIndex = Index.fromOneBased(1);

        AddJobCommand addCommand = new AddJobCommand(newJob, clientIndex, productIndex);

        String expectedMsg = String.format(AddJobCommand.MESSAGE_SUCCESS, newJob + " for " + ALICE.getName());
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.addJob(newJob);

        CommandResult commandResult = addCommand.execute(model, new StateManager(model));

        assertEquals(String.format(AddJobCommand.MESSAGE_SUCCESS, newJob), commandResult.getFeedbackToUser());
        assertEquals(expectedModel.getFilteredJobList(), model.getFilteredJobList());
    }


    @Test
    public void execute_someFieldsInvalidFilteredList_invalid() {
        Job newJob = new JobBuilder().withJobDescription("Fix Intel CPU")
            .withFee("$1000.00")
            .withReceivedDate("05/10/2021")
            .withExpectedCompletionDate("04/10/2021")
            .build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);
        // INTEL_CPU
        Index productIndex = Index.fromOneBased(1);

        AddJobCommand addCommand = new AddJobCommand(newJob, clientIndex, productIndex);

        String expectedMsg = Messages.MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE;
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());

        assertCommandFailure(addCommand, expectedModel, expectedMsg);
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        Job jobInList = model.getMyCrm().getJobList().get(0);
        assertCommandFailure(new AddJobCommand(jobInList, null, null),
                model, AddJobCommand.MESSAGE_DUPLICATE_JOB);
    }
}
