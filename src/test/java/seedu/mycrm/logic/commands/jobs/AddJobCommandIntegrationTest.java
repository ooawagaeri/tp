package seedu.mycrm.logic.commands.jobs;

import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalJobs.BENSON_JOB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.testutil.TypicalJobs;

public class AddJobCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalJobs.getTypicalMyCrm(), new UserPrefs());
    }

    @Test
    public void execute_newJob_success() {
        Job validJob = BENSON_JOB;

        Model expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.addJob(validJob);

        assertCommandSuccess(new AddJobCommand(validJob, null, null), model,
            String.format(AddJobCommand.MESSAGE_SUCCESS, validJob), expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Job jobInList = model.getMyCrm().getJobList().get(0);
        assertCommandFailure(new AddJobCommand(jobInList, null, null),
                model, AddJobCommand.MESSAGE_DUPLICATE_JOB);
    }
}
