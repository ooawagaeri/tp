package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_TEMPLATES;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;

/**
 * Lists all templates in the myCrm to the user.
 */
public class ListTemplateCommand extends Command {

    public static final String COMMAND_WORD = "listTemplate";

    public static final String MESSAGE_SUCCESS = "Listed all templates";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
