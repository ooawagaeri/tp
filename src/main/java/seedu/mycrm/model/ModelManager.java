package seedu.mycrm.model;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;

/**
 * Represents the in-memory model of the myCrm data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MyCrm myCrm;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Template> filteredTemplates;
    private final FilteredList<Mail> filteredMails;
    private final FilteredList<Job> filteredJobs;
    private final FilteredList<Job> filteredIncompleteJob;
    private final FilteredList<Job> filteredAllJobs;
    private final FilteredList<Job> filteredMonthlyCompletedJobs;
    private final FilteredList<Product> filteredProducts;
    private final FilteredList<History> filteredHistories;

    private Predicate<Job> latestJobPredicate;

    /**
     * Initializes a ModelManager with the given myCrm and userPrefs.
     */
    public ModelManager(ReadOnlyMyCrm myCrm, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(myCrm, userPrefs);

        logger.fine("Initializing with myCrm: " + myCrm + " and user prefs " + userPrefs);

        this.myCrm = new MyCrm(myCrm);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.myCrm.getContactList(), PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
        filteredTemplates = new FilteredList<>(this.myCrm.getTemplateList());
        filteredMails = new FilteredList<>(this.myCrm.getMailList());
        filteredJobs = new FilteredList<>(this.myCrm.getJobList(), PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
        filteredIncompleteJob = new FilteredList<>(this.myCrm.getJobList(), PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
        filteredAllJobs = new FilteredList<>(this.myCrm.getJobList());
        filteredMonthlyCompletedJobs =
                new FilteredList<>(this.myCrm.getJobList(), PREDICATE_SHOW_ALL_MONTHLY_COMPLETED_JOBS);
        filteredProducts = new FilteredList<>(this.myCrm.getProductList());
        filteredHistories = new FilteredList<>(this.myCrm.getHistoryList());
    }

    public ModelManager() {
        this(new MyCrm(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getMyCrmFilePath() {
        return userPrefs.getMyCrmFilePath();
    }

    @Override
    public void setMyCrmFilePath(Path myCrmFilePath) {
        requireNonNull(myCrmFilePath);
        userPrefs.setMyCrmFilePath(myCrmFilePath);
    }

    //=========== MyCRM ================================================================================

    @Override
    public void setMyCrm(ReadOnlyMyCrm myCrm) {
        this.myCrm.resetData(myCrm);
    }

    @Override
    public ReadOnlyMyCrm getMyCrm() {
        return myCrm;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return myCrm.hasContact(contact);
    }

    @Override
    public boolean hasTemplate(Template template) {
        requireNonNull(template);
        return myCrm.hasTemplate(template);
    }

    @Override
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return myCrm.hasJob(job);
    }

    @Override
    public void deleteContact(Contact target) {
        myCrm.removeContact(target);
    }

    @Override
    public void deleteJob(Job target) {
        myCrm.removeJob(target);
    }


    @Override
    public void addContact(Contact contact) {
        myCrm.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
    }

    @Override
    public void addTemplate(Template template) {
        myCrm.addTemplate(template);
        updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
    }

    @Override
    public void deleteTemplate(Template target) {
        myCrm.removeTemplate(target);
    }

    @Override
    public void setTemplate(Template target, Template editedTemplate) {
        requireAllNonNull(target, editedTemplate);
        myCrm.setTemplate(target, editedTemplate);
    }

    @Override
    public void addMail(Mail mail) {
        myCrm.addMail(mail);
        updateFilteredMailList(PREDICATE_SHOW_ALL_MAILS);
    }

    @Override
    public void addJob(Job job) {
        myCrm.addJob(job);
        updateFilteredJobList(PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
    }

    @Override
    public void setJob(Job target, Job editedJob) {
        requireAllNonNull(target, editedJob);
        myCrm.setJob(target, editedJob);
    }

    @Override
    public void addHistory(History history) {
        myCrm.addHistory(history);
        updateFilteredHistoryList(PREDICATE_SHOW_ALL_HISTORIES);
    }

    @Override
    public void clearHistory() {
        myCrm.clearHistory();
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        myCrm.setContact(target, editedContact);

    }

    @Override
    public void hideContact(Contact target) {
        requireAllNonNull(target);

        myCrm.hideContact(target);
        updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
    }

    @Override
    public double getRevenue(LocalDate date) {
        return myCrm.getRevenue(date);
    }

    @Override
    public void undoHideContact(Contact target) {
        requireAllNonNull(target);

        myCrm.undoHideContact(target);
        updateFilteredContactList(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);

    }

    //=========== Products ================================================================================

    @Override
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return myCrm.hasProduct(product);
    }

    @Override
    public void addProduct(Product product) {
        requireNonNull(product);
        myCrm.addProduct(product);
        updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
    }

    @Override
    public void deleteProduct(Product product) {
        requireNonNull(product);
        myCrm.removeProduct(product);
    }

    @Override
    public void setProduct(Product target, Product editedProduct) {
        requireAllNonNull(target, editedProduct);
        myCrm.setProduct(target, editedProduct);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of not hidden {@code Contact} backed by the internal list of
     * {@code MyCrm}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Template} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Template> getFilteredTemplateList() {
        return filteredTemplates;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Mail} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Mail> getFilteredMailList() {
        return filteredMails;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Product} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Product> getFilteredProductList() {
        return filteredProducts;
    }

    @Override
    public ObservableList<Product> getFilteredTopThreeProductList() {
        return new FilteredList<>(this.myCrm.getTopThreeProductList());
    }

    @Override
    public ObservableList<Job> getFilteredJobList() {
        return filteredJobs;
    }

    @Override
    public ObservableList<Job> getFilteredAllJobList() {
        return filteredAllJobs;
    }

    @Override
    public ObservableList<Job> getFilteredIncompleteJobList() {
        return filteredIncompleteJob;
    }

    @Override
    public ObservableList<Job> getFilteredMonthlyCompletedJobList() {
        return filteredMonthlyCompletedJobs;
    }

    /**
     * Returns an unmodifiable view of the list of {@code History} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<History> getFilteredHistoryList() {
        return filteredHistories;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTemplateList(Predicate<Template> predicate) {
        requireNonNull(predicate);
        filteredTemplates.setPredicate(predicate);
    }

    @Override
    public void updateFilteredMailList(Predicate<Mail> predicate) {
        requireNonNull(predicate);
        filteredMails.setPredicate(predicate);
    }

    @Override
    public void updateFilteredProductList(Predicate<Product> predicate) {
        requireNonNull(predicate);
        filteredProducts.setPredicate(predicate);
    }

    @Override
    public void updateFilteredJobList(Predicate<Job> predicate) {
        requireNonNull(predicate);
        latestJobPredicate = predicate;
        filteredJobs.setPredicate(predicate);
    }

    @Override
    public void updateFilteredHistoryList(Predicate<History> predicate) {
        requireNonNull(predicate);
        filteredHistories.setPredicate(predicate);
    }

    @Override
    public Predicate<Job> getLatestJobPredicate() {
        return this.latestJobPredicate;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return myCrm.equals(other.myCrm)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts);
    }

}
