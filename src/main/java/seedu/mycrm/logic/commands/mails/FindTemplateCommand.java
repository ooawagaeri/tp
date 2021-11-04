package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.mail.SubjectContainsKeywordsPredicate;

/**
 * Finds and lists all templates in myCrm whose subject contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTemplateCommand extends Command {

    public static final String COMMAND_WORD = "findTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all templates whose subjects contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Completed";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    private final SubjectContainsKeywordsPredicate predicate;

    public FindTemplateCommand(SubjectContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredTemplateList(predicate);
        return stateManager.handleList(new CommandResult(String.format(Messages.MESSAGE_TEMPLATES_LISTED_OVERVIEW,
                model.getFilteredTemplateList().size()), COMMAND_TYPE));
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindTemplateCommand
                && predicate.equals(((FindTemplateCommand) other).predicate));
    }
}
