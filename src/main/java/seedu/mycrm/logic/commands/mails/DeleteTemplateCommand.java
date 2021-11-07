package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.mail.Template;

/**
 * Deletes a template identified using it's displayed index from the myCrm.
 */
public class DeleteTemplateCommand extends Command {

    public static final String COMMAND_WORD = "deleteTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the template identified by the index number used in the displayed template list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TEMPLATE_SUCCESS = "Deleted Template: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    private final Index targetIndex;

    public DeleteTemplateCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Template> lastShownList = model.getFilteredTemplateList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
        }

        Template templateToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTemplate(templateToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TEMPLATE_SUCCESS, templateToDelete), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteTemplateCommand
                && targetIndex.equals(((DeleteTemplateCommand) other).targetIndex));
    }
}
