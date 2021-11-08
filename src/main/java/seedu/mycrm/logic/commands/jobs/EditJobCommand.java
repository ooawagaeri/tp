package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_RECEIVED_DATE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.commons.util.CollectionUtil;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;
import seedu.mycrm.model.product.Product;

public class EditJobCommand extends Command {
    public static final String COMMAND_WORD = "editJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the repair job identified "
            + "by the index number used in the displayed job list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION] "
            + "[" + PREFIX_FEE + "FEE] "
            + "[" + PREFIX_EXPECTED_COMPLETION_DATE + "EXPECTED COMPLETION DATE] "
            + "[" + PREFIX_RECEIVED_DATE + "RECEIVED DATE] "
            + "[" + PREFIX_CONTACT_INDEX + "CONTACT INDEX] "
            + "[" + PREFIX_PRODUCT_INDEX + "PRODUCT INDEX]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_JOB_DESCRIPTION + "Repair laptop screen "
            + PREFIX_FEE + "$50.00 ";

    public static final String MESSAGE_EDIT_JOB_SUCCESS = "Repair job edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_JOB = "This repair job already exists in the MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index index;
    private final EditJobDescriptor editJobDescriptor;

    /**
     * @param index of the repair job in the filtered job list to edit
     * @param editJobDescriptor details to edit the job with
     */
    public EditJobCommand(Index index, EditJobCommand.EditJobDescriptor editJobDescriptor) {
        requireNonNull(index);
        requireNonNull(editJobDescriptor);

        this.index = index;
        this.editJobDescriptor = new EditJobCommand.EditJobDescriptor(editJobDescriptor);
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownJobList = model.getFilteredJobList();
        List<Contact> lastShownContactList = model.getFilteredContactList();
        List<Product> lastShownProductList = model.getFilteredProductList();

        if (index.getZeroBased() >= lastShownJobList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEdit = lastShownJobList.get(index.getZeroBased());
        Job editedJob = createEditedJob(jobToEdit, editJobDescriptor, lastShownContactList, lastShownProductList);


        if (editedJob.getExpectedCompletionDate().value.isBefore(editedJob.getReceivedDate().value)) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_EXPECTED_COMPLETION_DATE);
        }

        if (editedJob.isCompleted() && editedJob.getCompletionDate().value.isBefore(
                editedJob.getReceivedDate().value)) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_COMPLETION_DATE);
        }

        if (!jobToEdit.isSameJob(editedJob) && model.hasJob(editedJob)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        CommandResult result = new CommandResult(String.format(MESSAGE_EDIT_JOB_SUCCESS, editedJob), COMMAND_TYPE);

        return stateManager.handleEditJob(jobToEdit, editedJob, editJobDescriptor.shouldEditContact,
                editJobDescriptor.shouldEditProduct, result);
    }

    /**
     * Creates and returns a {@code Job} with the details of {@code jobToEdit}
     * edited with {@code editJobDescriptor}.
     */
    private static Job createEditedJob(Job jobToEdit, EditJobCommand.EditJobDescriptor editJobDescriptor,
            List<Contact> lastShownContactList, List<Product> lastShownProductList) throws CommandException {
        assert jobToEdit != null;

        JobDescription updatedJobDescription = editJobDescriptor.getJobDescription()
                .orElse(jobToEdit.getJobDescription());
        JobDate updatedExpectedCompletionDate = editJobDescriptor.getExpectedCompletionDate().orElse(
                jobToEdit.getExpectedCompletionDate());
        JobDate updatedReceivedDate = editJobDescriptor.getReceivedDate().orElse(jobToEdit.getReceivedDate());
        JobFee updatedFee = editJobDescriptor.getFee().orElse(jobToEdit.getFee());
        JobStatus jobStatus = jobToEdit.getJobStatus();
        JobDate completionDate = jobToEdit.getCompletionDate();

        Index clientIndex = editJobDescriptor.getClientIndex();
        Contact updatedClient = jobToEdit.getClient();

        if (clientIndex != null) {
            if (clientIndex.getZeroBased() >= lastShownContactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }

            updatedClient = lastShownContactList.get(clientIndex.getZeroBased());
        }

        Index productIndex = editJobDescriptor.getProductIndex();
        Product updatedProduct = jobToEdit.getProduct();

        if (productIndex != null) {
            if (productIndex.getZeroBased() >= lastShownProductList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
            }

            updatedProduct = lastShownProductList.get(productIndex.getZeroBased());
        }

        return new Job(updatedJobDescription, updatedClient, updatedProduct, updatedExpectedCompletionDate,
                jobStatus, updatedReceivedDate, completionDate, updatedFee);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditJobCommand)) {
            return false;
        }

        // state check
        EditJobCommand e = (EditJobCommand) other;
        return index.equals(e.index)
            && editJobDescriptor.equals(e.editJobDescriptor);
    }

    /**
     * Stores the details to edit the job with. Each non-empty field value will replace the
     * corresponding field value of the job.
     */
    public static class EditJobDescriptor {
        private JobDescription jobDescription;
        private JobDate expectedCompletionDate;
        private JobDate receivedDate;
        private JobFee fee;
        private Index clientIndex;
        private Index productIndex;
        private boolean shouldEditContact = false;
        private boolean shouldEditProduct = false;

        public EditJobDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditJobDescriptor(EditJobCommand.EditJobDescriptor toCopy) {
            setJobDescription(toCopy.jobDescription);
            setExpectedCompletionDate(toCopy.expectedCompletionDate);
            setReceivedDate(toCopy.receivedDate);
            setFee(toCopy.fee);
            setClientIndex(toCopy.clientIndex);
            setProductIndex(toCopy.productIndex);
            setEditContact(toCopy.shouldEditContact);
            setEditProduct(toCopy.shouldEditProduct);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobDescription, expectedCompletionDate, receivedDate,
                    fee, clientIndex, productIndex) || shouldEditProduct || shouldEditContact;
        }

        public void setJobDescription(JobDescription jobDescription) {
            this.jobDescription = jobDescription;
        }

        public Optional<JobDescription> getJobDescription() {
            return Optional.ofNullable(jobDescription);
        }

        public void setExpectedCompletionDate(JobDate expectedCompletionDate) {
            this.expectedCompletionDate = expectedCompletionDate;
        }

        public Optional<JobDate> getExpectedCompletionDate() {
            return Optional.ofNullable(expectedCompletionDate);
        }

        public void setReceivedDate(JobDate receivedDate) {
            this.receivedDate = receivedDate;
        }

        public Optional<JobDate> getReceivedDate() {
            return Optional.ofNullable(receivedDate);
        }

        public void setFee(JobFee fee) {
            this.fee = fee;
        }

        public Optional<JobFee> getFee() {
            return Optional.ofNullable(fee);
        }

        public void setClientIndex (Index clientIndex) {
            this.clientIndex = clientIndex;
        }

        public Index getClientIndex() {
            return clientIndex;
        }

        public void setProductIndex (Index productIndex) {
            this.productIndex = productIndex;
        }

        public Index getProductIndex() {
            return productIndex;
        }

        public void setEditProduct(boolean shouldEditProduct) {
            this.shouldEditProduct = shouldEditProduct;
        }

        public void setEditContact(boolean shouldEditContact) {
            this.shouldEditContact = shouldEditContact;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditJobCommand.EditJobDescriptor)) {
                return false;
            }

            // state check
            EditJobCommand.EditJobDescriptor e = (EditJobCommand.EditJobDescriptor) other;
            return getJobDescription().equals(e.getJobDescription())
                && getExpectedCompletionDate().equals(e.getExpectedCompletionDate())
                && getReceivedDate().equals(e.getReceivedDate())
                && getFee().equals(e.getFee())
                && Objects.equals(getClientIndex(), e.getClientIndex())
                && Objects.equals(getProductIndex(), e.getProductIndex())
                && shouldEditContact == e.shouldEditContact
                && shouldEditProduct == e.shouldEditProduct;
        }
    }
}
