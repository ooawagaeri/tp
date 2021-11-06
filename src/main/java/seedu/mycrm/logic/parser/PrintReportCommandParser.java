package seedu.mycrm.logic.parser;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.PrintReportCommand.SHOW_IN_PROGRESS_FLAG;
import static seedu.mycrm.logic.commands.PrintReportCommand.SHOW_PRODUCT_FLAG;

import seedu.mycrm.logic.commands.PrintReportCommand;
import seedu.mycrm.logic.parser.exceptions.ParseException;

public class PrintReportCommandParser implements Parser<PrintReportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PrintReportCommand
     * and returns an PrintReportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PrintReportCommand parse(String args) throws ParseException {
        String flag = null;
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new PrintReportCommand();
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (nameKeywords.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PrintReportCommand.MESSAGE_USAGE));
        } else if (nameKeywords.length == 1) {
            if (SHOW_IN_PROGRESS_FLAG.equals(nameKeywords[0])) {
                flag = SHOW_IN_PROGRESS_FLAG;
            } else if (SHOW_PRODUCT_FLAG.equals(nameKeywords[0])) {
                flag = SHOW_PRODUCT_FLAG;
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PrintReportCommand.MESSAGE_USAGE));
            }
        }
        return new PrintReportCommand(flag);
    }

}
