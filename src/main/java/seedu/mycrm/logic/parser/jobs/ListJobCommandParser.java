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

    /**
     * Parses the given {@code String} of arguments in the context of the ListJobCommand
     * and returns an ListJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListJobCommand parse(String args) throws ParseException {
        Predicate<Job> listPredicate = null;
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            listPredicate = PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
            return new ListJobCommand(listPredicate);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (nameKeywords.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListJobCommand.MESSAGE_USAGE));
        } else if (nameKeywords.length == 1) {
            if (SHOW_ALL_FLAG.equals(nameKeywords[0])) {
                System.out.println(nameKeywords[0]);
                listPredicate = PREDICATE_SHOW_ALL_JOBS;
            } else if (SHOW_COMPLETED_FLAG.equals(nameKeywords[0])) {
                System.out.println(nameKeywords[0]);
                listPredicate = PREDICATE_SHOW_ALL_COMPLETED_JOBS;
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListJobCommand.MESSAGE_USAGE));
            }
        }
        return new ListJobCommand(listPredicate);
    }
}
