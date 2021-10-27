package seedu.mycrm.logic.parser.jobs;

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
        Index index;
        String splitArgs[];
        try {
            String trimmedArgs = args.trim();
            splitArgs = trimmedArgs.split(" ", 2);
             index = ParserUtil.parseIndex(splitArgs[0]);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteJobCommand.MESSAGE_USAGE), pe);
        }

        JobDate completionDate = null;
        if(splitArgs.length == 2) {
            completionDate = ParserUtil.parseJobDate(splitArgs[1]);
        }
        return new CompleteJobCommand(index, completionDate);
    }
}
