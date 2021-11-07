package seedu.mycrm.logic.parser.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.jobs.CompleteJobCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.job.JobDate;

public class CompleteJobCommandParser implements Parser<CompleteJobCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CompleteJobCommand
     * and returns a CompleteJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteJobCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split(" ", 2);
        Index index = parseIndex(splitArgs);
        JobDate completionDate = parseCompletionDate(splitArgs);

        return new CompleteJobCommand(index, completionDate);
    }

    private Index parseIndex(String[] args) throws ParseException {
        try {
            String indexString = args[0];
            Index index = ParserUtil.parseIndex(indexString);
            return index;
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteJobCommand.MESSAGE_USAGE), pe);
        }
    }

    private JobDate parseCompletionDate(String[] args) throws ParseException {
        JobDate completionDate = (args.length == 2)
                                 ? ParserUtil.parseJobDate(args[1], "Completion")
                                 : JobDate.getCurrentDate();
        return completionDate;
    }
}
