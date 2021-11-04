package seedu.mycrm.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    Predicate<Contact> PREDICATE_SHOW_NOT_HIDDEN_CONTACTS = contact -> !contact.checkIsHidden();
    Predicate<Template> PREDICATE_SHOW_ALL_TEMPLATES = unused -> true;
    Predicate<Product> PREDICATE_SHOW_ALL_PRODUCTS = unused -> true;
    Predicate<Job> PREDICATE_SHOW_ALL_JOBS = unused -> true;
    Predicate<Job> PREDICATE_SHOW_ALL_INCOMPLETE_JOBS = job -> !(job.isCompleted());
    Predicate<Job> PREDICATE_SHOW_ALL_COMPLETED_JOBS = job -> job.isCompleted();
    Predicate<Job> PREDICATE_SHOW_ALL_MONTHLY_COMPLETED_JOBS = job -> job.isCompletedThisMonth(LocalDate.now());
    Predicate<History> PREDICATE_SHOW_ALL_HISTORIES = unused -> true;
    Predicate<Mail> PREDICATE_SHOW_ALL_MAILS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' myCrm file path.
     */
    Path getMyCrmFilePath();

    /**
     * Sets the user prefs' myCrm file path.
     */
    void setMyCrmFilePath(Path myCrmFilePath);

    /**
     * Replaces myCrm data with the data in {@code myCrm}.
     */
    void setMyCrm(ReadOnlyMyCrm myCrm);

    /** Returns the MyCrm */
    ReadOnlyMyCrm getMyCrm();

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the myCrm.
     */
    boolean hasContact(Contact contact);

    /**
     * Returns true if a template with the same identity as {@code template} exists in the myCrm.
     */
    boolean hasTemplate(Template toAdd);

    /**
     * Returns true if a job with the same identity as {@code job} exists in the myCrm.
     */
    boolean hasJob(Job toAdd);

    /**
     * Deletes the given contact.
     * The contact must exist in the myCrm.
     */
    void deleteContact(Contact target);

    /**
     * Deletes the given job.
     * The job must exist in the myCrm.
     */
    void deleteJob(Job target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the myCrm.
     */
    void addContact(Contact contact);

    /**
     * Adds the given template.
     * {@code template} must not already exist in the myCrm.
     */
    void addTemplate(Template template);

    /**
     * Deletes the given template.
     * The template must exist in the myCrm.
     */
    void deleteTemplate(Template target);

    /**
     * Adds the given mail.
     * {@code mail} must not already exist in the myCrm.
     */
    void addMail(Mail mail);

    /**
     * Replaces the given template {@code target} with {@code editedTemplate}.
     * {@code target} must exist in the myCrm.
     * The template identity of {@code editedTemplate} must not be the same as another existing template
     * in the myCrm.
     */
    void setTemplate(Template target, Template editedTemplate);

    /**
     * Adds the given job.
     * {@code job} must not already exist in the myCrm.
     */
    void addJob(Job job);

    /**
     * Replaces the given job {@code target} with {@code editedJob}.
     * {@code target} must exist in myCrm.
     * The contact identity of {@code editedJob} must not be the same as another existing
     * job in the myCRM.
     */
    void setJob(Job target, Job editedJob);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the myCrm.
     * The contact identity of {@code editedContact} must not be the same as another existing contact
     * in the myCrm.
     */
    void setContact(Contact target, Contact editedContact);

    /**
     * Hides the given contact {@code target}.
     * {@code target} must exist in the myCrm.
     */
    void hideContact(Contact target);

    /**
     * Undo hiding the given contact {@code target}.
     * {@code target} must exist in the myCrm.
     */
    void undoHideContact(Contact target);


    /**
     * Returns true if a product with the same identity as {@code product} exists in MyCrm.
     */
    boolean hasProduct(Product product);

    /**
     * Adds the given product.
     * {@code product} must not already exist in MyCrm.
     */
    void addProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in MyCrm.
     */
    void deleteProduct(Product product);

    /**
     * Replaces the given product {@code target} with {@code editedProduct}.
     * {@code target} must exist in MyCrm.
     * The product identity of {@code editedProduct} must not be the same as another existing product
     * in MyCrm.
     */
    void setProduct(Product target, Product editedProduct);

    /**
     * Adds the entered command.
     */
    void addHistory(History history);

    /**
     * Clears history command data.
     */
    void clearHistory();

    double getRevenue(LocalDate date);

    /** Returns an unmodifiable view of the filtered unhidden contact list */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered template list */
    ObservableList<Template> getFilteredTemplateList();

    /** Returns an unmodifiable view of the filtered mail list */
    ObservableList<Mail> getFilteredMailList();

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredTopThreeProductList();

    /** Returns an unmodifiable view of the filtered job list */
    ObservableList<Job> getFilteredJobList();

    /** Returns an unmodifiable view of the filtered incomplete job list */
    ObservableList<Job> getFilteredIncompleteJobList();

    /** Returns an unmodifiable view of the filtered job list */
    ObservableList<Job> getFilteredAllJobList();

    /** Returns an unmodifiable view of the filtered monthly completed job list */
    ObservableList<Job> getFilteredMonthlyCompletedJobList();

    /** Returns an unmodifiable view of the filtered history command list */
    ObservableList<History> getFilteredHistoryList();

    /** Returns the latest predicate of the filtered job list */
    Predicate<Job> getLatestJobPredicate();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTemplateList(Predicate<Template> predicate);

    /**
     * Updates the filter of the filtered mail list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMailList(Predicate<Mail> predicate);

    /**
     * Updates the filter of the filtered product list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProductList(Predicate<Product> predicate);

    /**
     * Updates the filter of the filtered job list to filter by the given {@code predicate}. Update the latest job
     * predicate.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredJobList(Predicate<Job> predicate);

    /**
     * Updates the filter of the filtered history command list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHistoryList(Predicate<History> predicate);
}
