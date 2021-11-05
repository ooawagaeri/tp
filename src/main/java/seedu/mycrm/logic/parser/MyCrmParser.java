package seedu.mycrm.logic.parser;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.mycrm.logic.commands.AbortCommand;
import seedu.mycrm.logic.commands.ClearCommand;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.ExitCommand;
import seedu.mycrm.logic.commands.ExportReportCommand;
import seedu.mycrm.logic.commands.HelpCommand;
import seedu.mycrm.logic.commands.PrintReportCommand;
import seedu.mycrm.logic.commands.SelectCommand;
import seedu.mycrm.logic.commands.ThemeCommand;
import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.logic.commands.contacts.DeleteContactCommand;
import seedu.mycrm.logic.commands.contacts.EditContactCommand;
import seedu.mycrm.logic.commands.contacts.FindContactCommand;
import seedu.mycrm.logic.commands.contacts.HideContactCommand;
import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.commands.contacts.UndoHideContactCommand;
import seedu.mycrm.logic.commands.history.ClearHistoryCommand;
import seedu.mycrm.logic.commands.history.HistoryCommand;
import seedu.mycrm.logic.commands.jobs.AddJobCommand;
import seedu.mycrm.logic.commands.jobs.CompleteJobCommand;
import seedu.mycrm.logic.commands.jobs.DeleteJobCommand;
import seedu.mycrm.logic.commands.jobs.EditJobCommand;
import seedu.mycrm.logic.commands.jobs.FindJobCommand;
import seedu.mycrm.logic.commands.jobs.ListJobCommand;
import seedu.mycrm.logic.commands.jobs.UndoCompleteJobCommand;
import seedu.mycrm.logic.commands.mails.AddTemplateCommand;
import seedu.mycrm.logic.commands.mails.DeleteTemplateCommand;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand;
import seedu.mycrm.logic.commands.mails.FindTemplateCommand;
import seedu.mycrm.logic.commands.mails.ListTemplateCommand;
import seedu.mycrm.logic.commands.mails.MailCommand;
import seedu.mycrm.logic.commands.products.AddProductCommand;
import seedu.mycrm.logic.commands.products.DeleteProductCommand;
import seedu.mycrm.logic.commands.products.EditProductCommand;
import seedu.mycrm.logic.commands.products.FindProductCommand;
import seedu.mycrm.logic.commands.products.ListProductCommand;
import seedu.mycrm.logic.parser.contacts.AddContactCommandParser;
import seedu.mycrm.logic.parser.contacts.DeleteContactCommandParser;
import seedu.mycrm.logic.parser.contacts.EditContactCommandParser;
import seedu.mycrm.logic.parser.contacts.FindContactCommandParser;
import seedu.mycrm.logic.parser.contacts.HideContactCommandParser;
import seedu.mycrm.logic.parser.contacts.ListContactCommandParser;
import seedu.mycrm.logic.parser.contacts.UndoHideContactCommandParser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.logic.parser.jobs.AddJobCommandParser;
import seedu.mycrm.logic.parser.jobs.CompleteJobCommandParser;
import seedu.mycrm.logic.parser.jobs.DeleteJobCommandParser;
import seedu.mycrm.logic.parser.jobs.EditJobCommandParser;
import seedu.mycrm.logic.parser.jobs.FindJobCommandParser;
import seedu.mycrm.logic.parser.jobs.ListJobCommandParser;
import seedu.mycrm.logic.parser.jobs.UndoCompleteJobCommandParser;
import seedu.mycrm.logic.parser.mails.AddTemplateCommandParser;
import seedu.mycrm.logic.parser.mails.DeleteTemplateCommandParser;
import seedu.mycrm.logic.parser.mails.EditTemplateCommandParser;
import seedu.mycrm.logic.parser.mails.FindTemplateCommandParser;
import seedu.mycrm.logic.parser.mails.MailCommandParser;
import seedu.mycrm.logic.parser.products.AddProductCommandParser;
import seedu.mycrm.logic.parser.products.DeleteProductCommandParser;
import seedu.mycrm.logic.parser.products.EditProductCommandParser;
import seedu.mycrm.logic.parser.products.FindProductCommandParser;

/**
 * Parses user input.
 */
public class MyCrmParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used to parse the command word from the user input
     *
     * @param userInput full user input string
     * @return the command word
     * @throws ParseException if the user input does not conform to the expected format
     */
    public static String parseCommandWord(String userInput) throws ParseException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return matcher.group("commandWord");
    }

    /**
     * Used to parse the arguments from the user input
     * @param userInput full user input string
     * @return the arguments of the command
     * @throws ParseException if the user input does not conform to the expected format
     */
    public static String parseArguments(String userInput) throws ParseException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return matcher.group("arguments");
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final String commandWord = parseCommandWord(userInput);
        final String arguments = parseArguments(userInput);

        switch (commandWord) {

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case HideContactCommand.COMMAND_WORD:
            return new HideContactCommandParser().parse(arguments);

        case UndoHideContactCommand.COMMAND_WORD:
            return new UndoHideContactCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ThemeCommand.COMMAND_WORD:
            return new ThemeCommandParser().parse(arguments);

        case AddProductCommand.COMMAND_WORD:
            return new AddProductCommandParser().parse(arguments);

        case ListProductCommand.COMMAND_WORD:
            return new ListProductCommand();

        case EditProductCommand.COMMAND_WORD:
            return new EditProductCommandParser().parse(arguments);

        case FindProductCommand.COMMAND_WORD:
            return new FindProductCommandParser().parse(arguments);

        case DeleteProductCommand.COMMAND_WORD:
            return new DeleteProductCommandParser().parse(arguments);

        case AddTemplateCommand.COMMAND_WORD:
            return new AddTemplateCommandParser().parse(arguments);

        case ListTemplateCommand.COMMAND_WORD:
            return new ListTemplateCommand();

        case EditTemplateCommand.COMMAND_WORD:
            return new EditTemplateCommandParser().parse(arguments);

        case DeleteTemplateCommand.COMMAND_WORD:
            return new DeleteTemplateCommandParser().parse(arguments);

        case FindTemplateCommand.COMMAND_WORD:
            return new FindTemplateCommandParser().parse(arguments);

        case MailCommand.COMMAND_WORD:
            return new MailCommandParser().parse(arguments);

        case AddJobCommand.COMMAND_WORD:
            return new AddJobCommandParser().parse(arguments);

        case FindJobCommand.COMMAND_WORD:
            return new FindJobCommandParser().parse(arguments);

        case ListJobCommand.COMMAND_WORD:
            return new ListJobCommandParser().parse(arguments);

        case CompleteJobCommand.COMMAND_WORD:
            return new CompleteJobCommandParser().parse(arguments);

        case UndoCompleteJobCommand.COMMAND_WORD:
            return new UndoCompleteJobCommandParser().parse(arguments);

        case EditJobCommand.COMMAND_WORD:
            return new EditJobCommandParser().parse(arguments);

        case DeleteJobCommand.COMMAND_WORD:
            return new DeleteJobCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ClearHistoryCommand.COMMAND_WORD:
            return new ClearHistoryCommand();

        case PrintReportCommand.COMMAND_WORD:
            return new PrintReportCommandParser().parse(arguments);

        case ExportReportCommand.COMMAND_WORD:
            return new ExportReportCommand();

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case AbortCommand.COMMAND_WORD:
            return new AbortCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
