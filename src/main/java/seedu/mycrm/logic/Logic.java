package seedu.mycrm.logic;

import java.nio.file.Path;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the MyCrm.
     *
     * @see seedu.mycrm.model.Model#getMyCrm()
     */
    ReadOnlyMyCrm getMyCrm();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered list of templates */
    ObservableList<Template> getFilteredTemplateList();

    /** Returns an unmodifiable view of the filtered list of mails */
    ObservableList<Mail> getFilteredMailList();

    /** Returns an unmodifiable view of the filtered list of products */
    ObservableList<Product> getFilteredProductList();

    /** Returns an unmodifiable view of the filtered list of top three products */
    ObservableList<Product> getFilteredTopThreeProductList();

    /** Returns an unmodifiable view of the filtered list of jobs */
    ObservableList<Job> getFilteredJobList();

    /** Returns an unmodifiable view of the filtered list of incomplete jobs */
    ObservableList<Job> getFilteredIncompleteJobList();

    /** Returns an unmodifiable view of the filtered list of monthly completed jobs */
    ObservableList<Job> getFilteredMonthlyCompletedJobList();

    /** Returns an unmodifiable view of the filtered list of history commands */
    ObservableList<History> getFilteredHistoryList();

    double getRevenue(LocalDate date);

    /**
     * Returns the user prefs' myCrm file path.
     */
    Path getMyCrmFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Tracing UserInput from CommandBox.
     */
    void traceUserInput(History history);

}
