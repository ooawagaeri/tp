package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.mail.Template;

/**
 * Adds a template to the myCrm.
 */
public class AddTemplateCommand extends Command {

    public static final String COMMAND_WORD = "addTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a template to the myCrm. "
            + "Parameters: "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_BODY + "BODY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUBJECT + "Completed Job "
            + PREFIX_BODY + "Hello, your product is ready! ";

    public static final String MESSAGE_SUCCESS = "New template added: %1$s";
    public static final String MESSAGE_DUPLICATE_TEMPLATE = "This template already exists in the myCrm";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    private final Template toAdd;

    /**
     * Creates an AddTemplateCommand to add the specified {@code Template}
     */
    public AddTemplateCommand(Template template) {
        requireNonNull(template);
        toAdd = template;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        if (model.hasTemplate(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEMPLATE);
        }

        model.addTemplate(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddTemplateCommand
                && toAdd.equals(((AddTemplateCommand) other).toAdd));
    }
}
