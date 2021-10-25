package seedu.mycrm.logic.parser.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_RECEIVED_DATE;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.jobs.EditJobCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;

public class EditJobCommandParser {

    /**
     * Parses the given {@code args} of arguments in the context of the EditJobCommand
     * and returns an EditJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditJobCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_DESCRIPTION, PREFIX_DELIVERY_DATE,
                    PREFIX_RECEIVED_DATE, PREFIX_FEE, PREFIX_CONTACT_INDEX, PREFIX_PRODUCT_INDEX);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditJobCommand.MESSAGE_USAGE), pe);
        }

        EditJobCommand.EditJobDescriptor editJobDescriptor = new EditJobCommand.EditJobDescriptor();
        if (argMultimap.getValue(PREFIX_JOB_DESCRIPTION).isPresent()) {
            editJobDescriptor.setJobDescription(
                ParserUtil.parseJobDescription(argMultimap.getValue(PREFIX_JOB_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()) {
            editJobDescriptor.setDeliveryDate(
                ParserUtil.parseJobDate(argMultimap.getValue(PREFIX_DELIVERY_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_RECEIVED_DATE).isPresent()) {
            editJobDescriptor.setReceivedDate(
                ParserUtil.parseJobDate(argMultimap.getValue(PREFIX_RECEIVED_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_FEE).isPresent()) {
            editJobDescriptor.setFee(ParserUtil.parseJobFee(argMultimap.getValue(PREFIX_FEE).get()));
        }

        if (argMultimap.getValue(PREFIX_CONTACT_INDEX).isPresent()) {
            editJobDescriptor.setClientIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get()));
        }

        if (argMultimap.getValue(PREFIX_PRODUCT_INDEX).isPresent()) {
            editJobDescriptor.setProductIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PRODUCT_INDEX).get()));
        }

        if (!editJobDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditJobCommand.MESSAGE_NOT_EDITED);
        }

        return new EditJobCommand(index, editJobDescriptor);
    }
}
