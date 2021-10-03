package seedu.address.logic.parser.mails;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.stream.Stream;

import seedu.address.logic.commands.mails.AddTemplateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.mail.Body;
import seedu.address.model.mail.Subject;
import seedu.address.model.mail.Template;

/**
 * Parses input arguments and creates a new AddTemplateCommand object
 */
public class AddTemplateCommandParser implements Parser<AddTemplateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTemplateCommand
     * and returns an AddTemplateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTemplateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT, PREFIX_BODY);

        if (!arePrefixesPresent(argMultimap, PREFIX_SUBJECT, PREFIX_BODY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTemplateCommand.MESSAGE_USAGE));
        }

        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        Body body = ParserUtil.parseBody(argMultimap.getValue(PREFIX_BODY).get());

        Template template = new Template(subject, body);

        return new AddTemplateCommand(template);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
