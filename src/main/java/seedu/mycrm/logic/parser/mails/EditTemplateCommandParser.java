package seedu.mycrm.logic.parser.mails;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;


public class EditTemplateCommandParser implements Parser<EditTemplateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTemplateCommand
     * and returns an EditTemplateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTemplateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT, PREFIX_BODY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTemplateCommand.MESSAGE_USAGE), pe);
        }

        EditTemplateCommand.EditTemplateDescriptor editTemplateDescriptor =
                new EditTemplateCommand.EditTemplateDescriptor();
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            editTemplateDescriptor.setSubject(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        if (argMultimap.getValue(PREFIX_BODY).isPresent()) {
            editTemplateDescriptor.setBody(ParserUtil.parseBody(argMultimap.getValue(PREFIX_BODY).get()));
        }

        if (!editTemplateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTemplateCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTemplateCommand(index, editTemplateDescriptor);
    }
}
