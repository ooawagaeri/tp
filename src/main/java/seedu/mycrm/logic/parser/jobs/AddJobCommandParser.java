package seedu.mycrm.logic.parser.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_RECEIVED_DATE;

import java.util.stream.Stream;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.jobs.AddJobCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.Prefix;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;

public class AddJobCommandParser implements Parser<AddJobCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddJobCommand}
     * and returns a {@code AddJobCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public AddJobCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] mandatoryPrefixes = { PREFIX_JOB_DESCRIPTION, PREFIX_FEE, PREFIX_EXPECTED_COMPLETION_DATE};
        Prefix[] allPrefixes = { PREFIX_JOB_DESCRIPTION, PREFIX_FEE, PREFIX_EXPECTED_COMPLETION_DATE,
            PREFIX_CONTACT_INDEX, PREFIX_PRODUCT_INDEX, PREFIX_RECEIVED_DATE };

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);
        validatePresenceOfMandatoryPrefixes(argumentMultimap, mandatoryPrefixes);
        Job job = parsePrefixesToCreateJob(argumentMultimap);
        Index contactIndex = parseIndexIfPresent(argumentMultimap, PREFIX_CONTACT_INDEX);
        Index productIndex = parseIndexIfPresent(argumentMultimap, PREFIX_PRODUCT_INDEX);

        return new AddJobCommand(job, contactIndex, productIndex);
    }


    private void validatePresenceOfMandatoryPrefixes(ArgumentMultimap argMultimap,
            Prefix[] mandatoryPrefixes) throws ParseException {

        if (!arePrefixesPresent(argMultimap, mandatoryPrefixes) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE));
        }
    }


    private Job parsePrefixesToCreateJob(ArgumentMultimap argMultimap) throws ParseException {
        JobDescription jobDescription = ParserUtil.parseJobDescription(
                argMultimap.getValue(PREFIX_JOB_DESCRIPTION).get());

        JobDate expectedCompletionDate = ParserUtil.parseJobDate(
                argMultimap.getValue(PREFIX_EXPECTED_COMPLETION_DATE).get(), "Expected Completion");

        JobFee fee = ParserUtil.parseJobFee(
                argMultimap.getValue(PREFIX_FEE).get());

        JobDate receivedDate = (argMultimap.getValue(PREFIX_RECEIVED_DATE).isPresent())
                               ? ParserUtil.parseJobDate(argMultimap.getValue(PREFIX_RECEIVED_DATE).get(), "Received")
                               : JobDate.getCurrentDate();

        Job job = new Job(jobDescription, expectedCompletionDate, receivedDate, fee);

        return job;
    }

    private Index parseIndexIfPresent(ArgumentMultimap argMultimap, Prefix indexPrefix) throws ParseException {
        Index index = (argMultimap.getValue(indexPrefix).isPresent())
                      ? ParserUtil.parseIndex(argMultimap.getValue(indexPrefix).get())
                      : null;

        return index;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
