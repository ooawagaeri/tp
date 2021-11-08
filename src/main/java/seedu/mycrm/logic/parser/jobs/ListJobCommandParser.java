package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.jobs.ListJobCommand.SHOW_ALL_FLAG;
import static seedu.mycrm.logic.commands.jobs.ListJobCommand.SHOW_COMPLETED_FLAG;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_JOBS;

import java.util.function.Predicate;

import seedu.mycrm.logic.commands.jobs.ListJobCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.job.Job;

public class ListJobCommandParser implements Parser<ListJobCommand> {
    private static final String EMPTY_STRING = "";

    /**
     * Parses the given {@code String} of arguments in the context of the ListJobCommand
     * and returns an ListJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListJobCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] flags = trimmedArgs.split("\\s+");
        String flag = flags[0];

        // Check if more than one flag was provided
        if (flags.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListJobCommand.MESSAGE_USAGE));
        }

        Predicate<Job> listJobPredicate = getListJobPredicate(flag);

        return new ListJobCommand(listJobPredicate);
    }

    private Predicate<Job> getListJobPredicate(String flag) throws ParseException {
        switch (flag) {
        case EMPTY_STRING:
            return PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
        case SHOW_ALL_FLAG:
            return PREDICATE_SHOW_ALL_JOBS;
        case SHOW_COMPLETED_FLAG:
            return PREDICATE_SHOW_ALL_COMPLETED_JOBS;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListJobCommand.MESSAGE_USAGE));
        }
    }
}
