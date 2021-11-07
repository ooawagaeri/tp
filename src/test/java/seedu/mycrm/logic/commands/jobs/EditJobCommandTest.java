package seedu.mycrm.logic.commands.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.testutil.EditJobDescriptorBuilder;
import seedu.mycrm.testutil.JobBuilder;
import seedu.mycrm.testutil.TypicalJobs;

public class EditJobCommandTest {

    private final Model model = new ModelManager(TypicalJobs.getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedFilteredList_success() {
        // By default, job list is filtered to show only incomplete jobs.
        // CARL JOB
        Job jobToEdit = model.getFilteredJobList().get(0);
        Job editedJob = new JobBuilder(jobToEdit).withJobDescription("Fix Intel CPU")
                .withFee("$1000.00")
                .withReceivedDate("01/10/2021")
                .withExpectedCompletionDate("11/10/2021")
                .withClient(ALICE)
                .withProduct(INTEL_CPU)
                .build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);
        // INTEL_CPU
        Index productIndex = Index.fromOneBased(2);

        EditJobCommand.EditJobDescriptor descriptor =
                new EditJobDescriptorBuilder(editedJob, clientIndex, productIndex).build();
        EditJobCommand editCommand = new EditJobCommand(INDEX_FIRST_JOB, descriptor);

        String expectedMsg = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedFilteredList_success() {
        // By default, job list is filtered to show only incomplete jobs.
        // CARL JOB
        Job jobToEdit = model.getFilteredJobList().get(0);
        Job editedJob = new JobBuilder(jobToEdit).withJobDescription("Fix Intel CPU")
                .withClient(ALICE)
                .withProduct(INTEL_CPU)
                .build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);
        // INTEL_CPU
        Index productIndex = Index.fromOneBased(2);

        EditJobCommand.EditJobDescriptor descriptor =
            new EditJobDescriptorBuilder(editedJob, clientIndex, productIndex).build();
        EditJobCommand editCommand = new EditJobCommand(INDEX_FIRST_JOB, descriptor);

        String expectedMsg = String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, editedJob);
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editCommand, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_someFieldsInvalidFilteredList_invalid() {
        // By default, job list is filtered to show only incomplete jobs.
        // CARL JOB
        Job jobToEdit = model.getFilteredJobList().get(0);

        // Invalid expected completion date because it is before the received date
        Job editedJob = new JobBuilder(jobToEdit).withJobDescription("Fix Intel CPU")
                .withFee("$1000.00")
                .withReceivedDate("05/10/2021")
                .withExpectedCompletionDate("04/10/2021")
                .withClient(ALICE)
                .withProduct(INTEL_CPU)
                .build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);
        // INTEL_CPU
        Index productIndex = Index.fromOneBased(2);

        EditJobCommand.EditJobDescriptor descriptor =
            new EditJobDescriptorBuilder(editedJob, clientIndex, productIndex).build();
        EditJobCommand editCommand = new EditJobCommand(INDEX_FIRST_JOB, descriptor);

        String expectedMsg = Messages.MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE;
        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandFailure(editCommand, expectedModel, expectedMsg);
    }

    @Test
    public void execute_duplicateJobFilteredList_failure() {
        // By default, job list is filtered to show only incomplete jobs.
        // CARL JOB
        Job jobToEdit = model.getFilteredJobList().get(0);
        Job editedJob = new JobBuilder(jobToEdit).build();

        // ALICE
        Index clientIndex = Index.fromOneBased(1);
        // ASUS_GPU
        Index productIndex = Index.fromOneBased(2);

        EditJobCommand.EditJobDescriptor descriptor =
            new EditJobDescriptorBuilder(editedJob, clientIndex, productIndex).build();
        EditJobCommand editCommand = new EditJobCommand(INDEX_FIRST_JOB, descriptor);

        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandFailure(editCommand, expectedModel, EditJobCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        EditJobCommand cmd = new EditJobCommand(outOfBoundIndex, new EditJobCommand.EditJobDescriptor());

        assertCommandFailure(cmd, model, MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

}
