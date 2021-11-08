package seedu.mycrm.logic.parser.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.Address;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;

/**
 * Parses input arguments and creates a new AddContactCommand object
 */
public class AddContactCommandParser implements Parser<AddContactCommand> {
    private static final String EMPTY_PREFIX = "EMPTY";
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddContactCommand}
     * and returns a {@code AddContactCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddContactCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        }

        Optional<String> nameWrapper = argMultimap.getValue(PREFIX_NAME);
        Name name;
        if (nameWrapper.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        } else if (nameWrapper.get().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        } else {
            name = Name.getName(nameWrapper.get());
        }

        Optional<String> phoneWrapper = argMultimap.getValue(PREFIX_PHONE);
        Phone phone;
        phone = phoneWrapper.orElse(EMPTY_PREFIX).equals(EMPTY_PREFIX)
                ? Phone.getEmptyPhone()
                : Phone.getPhone(phoneWrapper.get());

        Optional<String> emailWrapper = argMultimap.getValue(PREFIX_EMAIL);
        Email email = emailWrapper.orElse(EMPTY_PREFIX).equals(EMPTY_PREFIX)
                ? Email.getEmptyEmail()
                : Email.getEmail(emailWrapper.get());


        Optional<String> addressWrapper = argMultimap.getValue(PREFIX_ADDRESS);
        Address address = addressWrapper.orElse(EMPTY_PREFIX).equals(EMPTY_PREFIX)
                ? Address.getEmptyAddress()
                : Address.getAddress(addressWrapper.get());

        if (phone.isEmpty() && email.isEmpty() && address.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_AT_LEAST_ONE_COMPONENT));
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Contact contact = new Contact(name, phone, email, address, tagList);

        return new AddContactCommand(contact);
    }
}
