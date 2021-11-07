package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.contact.Contact;

/**
 * Inserts a contact with name, phone, email, address or tags specified into the myCrm.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "addContact";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to MyCRM. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "1st Tier "
            + PREFIX_TAG + "Premium";

    public static final Object MESSAGE_AT_LEAST_ONE_COMPONENT = "You have to give at least one info "
            + "for this contact!!!";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s\n";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    private final Contact contactToAdd;

    /**
     * Creates an AddContact to add the specified {@code Contact}
     */
    public AddContactCommand(Contact contact) {
        requireNonNull(contact);
        contactToAdd = contact;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        if (model.hasContact(contactToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addContact(contactToAdd);
        CommandResult commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, contactToAdd), COMMAND_TYPE);
        return stateManager.handleContact(contactToAdd, commandResult);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && contactToAdd.equals(((AddContactCommand) other).contactToAdd));
    }
}
