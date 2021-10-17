package seedu.mycrm.logic.parser.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;

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
import seedu.mycrm.model.job.JobDeliveryDate;
import seedu.mycrm.model.job.JobDescription;

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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_DESCRIPTION,
                PREFIX_CONTACT_INDEX, PREFIX_PRODUCT_INDEX, PREFIX_DELIVERY_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_DESCRIPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddJobCommand.MESSAGE_USAGE));
        }

        JobDescription jobDescription = ParserUtil.parseJobDescription(
                argMultimap.getValue(PREFIX_JOB_DESCRIPTION).get());

        JobDeliveryDate deliveryDate = null;
        if (arePrefixesPresent(argMultimap, PREFIX_DELIVERY_DATE)) {
            deliveryDate = ParserUtil.parseJobDeliveryDate(
                argMultimap.getValue(PREFIX_DELIVERY_DATE).get());
        }

        Index contactIndex = null;
        if (arePrefixesPresent(argMultimap, PREFIX_CONTACT_INDEX)) {
            contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
        }

        Index productIndex = null;
        if (arePrefixesPresent(argMultimap, PREFIX_PRODUCT_INDEX)) {
            productIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PRODUCT_INDEX).get());
        }

        Job job = new Job(jobDescription, deliveryDate);

        return new AddJobCommand(job, contactIndex, productIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
