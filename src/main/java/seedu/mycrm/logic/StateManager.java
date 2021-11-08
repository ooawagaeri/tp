package seedu.mycrm.logic;

import static seedu.mycrm.logic.commands.jobs.AddJobCommand.MESSAGE_DUPLICATE_JOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.AbortCommand;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.SelectCommand;
import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.logic.commands.contacts.FindContactCommand;
import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.commands.jobs.AddJobCommand;
import seedu.mycrm.logic.commands.jobs.EditJobCommand;
import seedu.mycrm.logic.commands.products.AddProductCommand;
import seedu.mycrm.logic.commands.products.FindProductCommand;
import seedu.mycrm.logic.commands.products.ListProductCommand;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.product.Product;

/**
 * Responsible for maintaining state for previous commands and
 * modifying behaviour based on current state.
 */
public class StateManager {

    private static final String NEW_JOB_CONTACT_MESSAGE = "Currently adding a new job. "
            + "Please provide a contact for the Job: %s.\n";

    private static final String NEW_JOB_PRODUCT_MESSAGE = "Currently adding a new job. "
            + "Please provide a product for the Job: %s.\n";

    private static final String EDIT_JOB_CONTACT_MESSAGE = "Currently editing the contact for job %s.\n";

    private static final String EDIT_JOB_PRODUCT_MESSAGE = "Currently editing the product for job %s.\n";

    private static final String PROVIDE_CONTACT_INSTR = "You can either assign a new contact to the job by "
            + "issuing a addContact command or select an existing one "
            + "from the contact list by issuing a select command.\n";

    private static final String CONTACT_PROVIDED_MESSAGE = "Following contact: %s will be assigned to the job.\n";

    private static final String PROVIDE_PRODUCT_INSTR = "You can either assign a a new product to the job by "
            + "issuing a addProduct command or select an existing one "
            + "from the contact list by issuing a select command.\n";

    private static final String PRODUCT_PROVIDED_MESSAGE = "Following product: %s will be assigned to the job.\n";

    private static final String ERROR_MESSAGE = "There was an error in the command issued. "
            + "Please try to issue the command again after correcting based on the info below.\n";

    private static final String COMMAND_NOT_ALLOWED_MESSAGE =
            "Command %s is now allowed right now\n" + "You can issue the abort command to stop the current operation\n";

    private static final String ADD_JOB_ABORTED = "New job %s will not added to MyCRM\n"
            + "However, any contact or product added during the operation using "
            + "addJob or addProduct have been added to MyCRM\n";

    private static final String EDIT_JOB_ABORTED = "No attributes of Job: %s will be edited\n"
            + "However, any contact or product added during the operation using "
            + "addJob or addProduct have been added to MyCRM\n";


    private enum State {
        NEW_JOB_CONTACT(NEW_JOB_CONTACT_MESSAGE, PROVIDE_CONTACT_INSTR,
            CONTACT_PROVIDED_MESSAGE, CommandType.CONTACTS),

        NEW_JOB_PRODUCT(NEW_JOB_PRODUCT_MESSAGE, PROVIDE_PRODUCT_INSTR,
            PRODUCT_PROVIDED_MESSAGE, CommandType.PRODUCTS),

        EDIT_JOB_CONTACT(EDIT_JOB_CONTACT_MESSAGE, PROVIDE_CONTACT_INSTR,
            CONTACT_PROVIDED_MESSAGE, CommandType.CONTACTS),

        EDIT_JOB_PRODUCT(EDIT_JOB_PRODUCT_MESSAGE, PROVIDE_PRODUCT_INSTR,
            PRODUCT_PROVIDED_MESSAGE, CommandType.PRODUCTS);

        private String userMessage;
        private String instructions;
        private String successMessage;
        private CommandType commandType;

        private State(String userMessage, String instructions, String successMessage, CommandType commandType) {
            this.userMessage = userMessage;
            this.instructions = instructions;
            this.successMessage = successMessage;
            this.commandType = commandType;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getInstructions() {
            return instructions;
        }

        public String getSuccessMessage() {
            return successMessage;
        }

        public CommandType getCommandType() {
            return commandType;
        }
    }

    private static Map<State, List<String>> allowedCommandsForState;
    private Job job;
    private Job jobToEdit;
    private Model model;
    private State currentState;
    private Queue<State> stateQueue;

    /**
     * Constructs a StateManager object.
     */
    public StateManager(Model model) {
        this.model = model;
        stateQueue = new LinkedList<>();
        initialiseAllowedCommandsForState();
    }

    private static void initialiseAllowedCommandsForState() {
        allowedCommandsForState = new HashMap<>();

        allowedCommandsForState.put(State.NEW_JOB_CONTACT,
            new ArrayList<>(Arrays.asList(AddContactCommand.COMMAND_WORD,
                FindContactCommand.COMMAND_WORD, ListContactCommand.COMMAND_WORD,
                SelectCommand.COMMAND_WORD, AbortCommand.COMMAND_WORD)));

        allowedCommandsForState.put(State.NEW_JOB_PRODUCT,
            new ArrayList<>(Arrays.asList(AddProductCommand.COMMAND_WORD,
                FindProductCommand.COMMAND_WORD, ListProductCommand.COMMAND_WORD,
                SelectCommand.COMMAND_WORD, AbortCommand.COMMAND_WORD)));

        allowedCommandsForState.put(State.EDIT_JOB_CONTACT,
            new ArrayList<>(Arrays.asList(AddContactCommand.COMMAND_WORD,
                FindContactCommand.COMMAND_WORD, ListContactCommand.COMMAND_WORD,
                SelectCommand.COMMAND_WORD, AbortCommand.COMMAND_WORD)));

        allowedCommandsForState.put(State.EDIT_JOB_PRODUCT,
            new ArrayList<>(Arrays.asList(AddProductCommand.COMMAND_WORD,
                FindProductCommand.COMMAND_WORD, ListProductCommand.COMMAND_WORD,
                SelectCommand.COMMAND_WORD, AbortCommand.COMMAND_WORD)));
    }

    /**
     * Handles the behaviour of the addJob command based on whether a
     * product and client have been assigned.
     *
     * @param job Job to be added to MyCRM.
     * @return Result of the execution of the addJob command.
     */
    public CommandResult handleAddJob(Job job) throws CommandException {
        this.job = job;


        if (job.hasProduct() && job.hasClient()) {
            model.addJob(job);
            return new CommandResult(String.format(AddJobCommand.MESSAGE_SUCCESS, job), CommandType.JOBS);
        }

        if (!job.hasClient()) {
            stateQueue.add(State.NEW_JOB_CONTACT);
        }

        if (!job.hasProduct()) {
            stateQueue.add(State.NEW_JOB_PRODUCT);
        }

        try {
            return constructCommandResult(null, "");
        } catch (CommandException e) {
            throw new CommandException(getErrorMessage() + e.getMessage());
        }
    }


    /**
     * Handles the behaviour of the editJob command based on whether a product or client
     * of a job need to be edited.
     *
     * @param jobToEdit Existing job in MyCRM that needs to be edited.
     * @param editedJob Edited Job which will replace the existing job.
     * @param shouldEditContact Whether the job's contact is being edited.
     * @param shouldEditProduct Whether the job's product is being edited.
     * @return
     */
    public CommandResult handleEditJob(Job jobToEdit, Job editedJob,
            boolean shouldEditContact, boolean shouldEditProduct, CommandResult commandResult) throws CommandException {

        this.job = editedJob;
        this.jobToEdit = jobToEdit;

        if (!shouldEditContact && !shouldEditProduct) {
            model.setJob(jobToEdit, job);
            return new CommandResult(String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, job),
                    CommandType.JOBS);
        }

        if (shouldEditContact) {
            stateQueue.add(State.EDIT_JOB_CONTACT);
        }

        if (shouldEditProduct) {
            stateQueue.add(State.EDIT_JOB_PRODUCT);
        }

        try {
            return constructCommandResult(null, "");
        } catch (CommandException e) {
            throw new CommandException(getErrorMessage() + e.getMessage());
        }
    }

    /**
     * Handles the behaviour of the addProduct command if it is used in the context
     * of a addJob or editJob command.
     *
     * @param product Product that was added.
     * @param commandResult Result of the original addProduct command.
     * @return Modified result of the addProduct command based on current state.
     */
    public CommandResult handleProduct(Product product, CommandResult commandResult) throws CommandException {
        if (currentState == State.NEW_JOB_PRODUCT || currentState == State.EDIT_JOB_PRODUCT) {
            job.setProduct(product);
            try {
                return constructCommandResult(commandResult, String.format(currentState.getSuccessMessage(), product));
            } catch (CommandException e) {
                throw new CommandException(getErrorMessage() + e.getMessage());
            }


        } else {
            return commandResult;
        }
    }

    /**
     * Handles the behaviour of the addContact command if it is used in the context
     * of a addJob or editJob command.
     *
     * @param contact Contact that was added.
     * @param commandResult Result of the original addContact command.
     * @return Modified result of the addContact command based on current state.
     */
    public CommandResult handleContact(Contact contact, CommandResult commandResult) throws CommandException {
        if (currentState == State.NEW_JOB_CONTACT || currentState == State.EDIT_JOB_CONTACT) {
            job.setClient(contact);
            try {
                return constructCommandResult(commandResult, String.format(currentState.getSuccessMessage(), contact));
            } catch (CommandException e) {
                throw new CommandException(getErrorMessage() + e.getMessage());
            }
        } else {
            return commandResult;
        }
    }

    /**
     * Handles the behaviour of the select command based on the context of
     * selecting a product or contact.
     *
     * @param targetIndex Index of the displayed product or contact.
     * @return Result of the execution of the select command.
     * @throws CommandException If the index is invalid.
     */
    public CommandResult handleIndex(Index targetIndex) throws CommandException {
        if (currentState == State.NEW_JOB_CONTACT || currentState == State.EDIT_JOB_CONTACT) {
            List<Contact> lastShownContactList = model.getFilteredContactList();
            if (targetIndex.getZeroBased() >= lastShownContactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }

            Contact client = lastShownContactList.get(targetIndex.getZeroBased());
            job.setClient(client);
            return constructCommandResult(null, String.format(currentState.getSuccessMessage(), client));

        } else if (currentState == State.NEW_JOB_PRODUCT || currentState == State.EDIT_JOB_PRODUCT) {
            List<Product> lastShownProductList = model.getFilteredProductList();
            if (targetIndex.getZeroBased() >= lastShownProductList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
            }

            Product product = lastShownProductList.get(targetIndex.getZeroBased());
            job.setProduct(product);
            return constructCommandResult(null, String.format(currentState.getSuccessMessage(), product));
        } else {
            throw new CommandException("Invalid usage of Select Command\n" + SelectCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Handles the behaviour of list or find commands for product command.
     * Modifies the feedBackToUser string of the command result.
     *
     * @param commandResult Original result of the execution of a list or find command.
     * @return CommandResult with modified feedbackToUser string.
     */
    public CommandResult handleList(CommandResult commandResult) {
        if (currentState == null) {
            return commandResult;
        }
        String userFeedback = String.format(currentState.getUserMessage(), job);
        userFeedback += commandResult.getFeedbackToUser();
        return new CommandResult(userFeedback, commandResult.getCommandType());
    }

    /**
     * Handles the aborting of the addJob or editJob operation.
     *
     * @return Result of the execution of the abort command.
     * @throws CommandException If the usage of abort is done outside the context of a
     * addJob or editJob command.
     */
    public CommandResult handleAbort() throws CommandException {
        CommandResult result;
        if (isJobBeingEdited()) {
            result = new CommandResult(String.format(EDIT_JOB_ABORTED, jobToEdit), CommandType.JOBS);

        } else if (isJobBeingAdded()) {
            result = new CommandResult(String.format(ADD_JOB_ABORTED, job), CommandType.JOBS);
        } else {
            throw new CommandException("Invalid usage of Abort Command\n" + AbortCommand.MESSAGE_USAGE);
        }

        clearState();
        return result;
    }


    /**
     * Checks if the command issued is allowed based on the current state.
     *
     * @param commandWord Command Word of the command to be executed.
     * @return Whether the command is allowed based on the current state.
     */
    public boolean isCommandAllowedForState(String commandWord) {
        if (currentState == null) {
            return true;
        }
        List<String> allowedCommands = allowedCommandsForState.get(currentState);
        return allowedCommands.contains(commandWord);
    }

    /**
     * Returns an error message based on current state.
     */
    public String getErrorMessage() {
        String userFeedback = "";
        if (currentState != null) {
            userFeedback += String.format(currentState.getUserMessage(), job);
            userFeedback += ERROR_MESSAGE;
        }
        return userFeedback;
    }

    /**
     * Returns a command not allowed message based on current state.
     */
    public String getCommandNotAllowedMessage(String commandWord) {
        if (currentState == null) {
            return "";
        }

        String userFeedback = String.format(COMMAND_NOT_ALLOWED_MESSAGE, commandWord);
        userFeedback += String.format(currentState.getUserMessage(), job);
        return userFeedback;
    }

    private CommandResult constructCommandResult(CommandResult commandResult, String message) throws CommandException {
        State nextState = stateQueue.poll();

        String userFeedback;

        if (commandResult == null) {
            userFeedback = message;
        } else {
            userFeedback = commandResult.getFeedbackToUser() + message;
        }

        if (isJobBeingAdded() && nextState == null) {
            if (model.hasJob(job)) {
                throw new CommandException(MESSAGE_DUPLICATE_JOB);
            }
            model.addJob(job);
            userFeedback += String.format(AddJobCommand.MESSAGE_SUCCESS, job);
            clearState();
            return new CommandResult(userFeedback, CommandType.JOBS);
        } else if (isJobBeingEdited() && nextState == null) {
            if (model.hasJob(job)) {
                throw new CommandException(MESSAGE_DUPLICATE_JOB);
            }
            model.setJob(jobToEdit, job);
            userFeedback += String.format(EditJobCommand.MESSAGE_EDIT_JOB_SUCCESS, job);
            clearState();
            return new CommandResult(userFeedback, CommandType.JOBS);
        } else {
            userFeedback += String.format(nextState.getUserMessage(), job);
            userFeedback += nextState.getInstructions();
            currentState = nextState;
            return new CommandResult(userFeedback, nextState.getCommandType());
        }
    }

    private void clearState() {
        job = null;
        jobToEdit = null;
        stateQueue.clear();
        currentState = null;
    }

    private boolean isJobBeingEdited() {
        return (currentState == State.EDIT_JOB_CONTACT || currentState == State.EDIT_JOB_PRODUCT);
    }

    private boolean isJobBeingAdded() {
        return (currentState == State.NEW_JOB_CONTACT || currentState == State.NEW_JOB_PRODUCT);
    }
}
