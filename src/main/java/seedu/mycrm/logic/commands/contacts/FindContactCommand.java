package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.NameContainsKeywordsPredicate;

/**
 * Finds and lists all contacts in myCrm whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findContact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final NameContainsKeywordsPredicate predicate;

    public FindContactCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return stateManager.handleList(new CommandResult(String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
            model.getFilteredContactList().size()), COMMAND_TYPE));
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
