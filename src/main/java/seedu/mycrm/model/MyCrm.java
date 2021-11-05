package seedu.mycrm.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.UniqueContactList;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.history.HistoryList;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.UniqueJobList;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.mail.UniqueMailList;
import seedu.mycrm.model.mail.UniqueTemplateList;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.model.product.UniqueProductList;

/**
 * Wraps all data at the myCrm level
 * Duplicates are not allowed (by .isSameContact comparison)
 */
public class MyCrm implements ReadOnlyMyCrm {

    private final UniqueContactList contacts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        contacts = new UniqueContactList();
    }

    private final UniqueTemplateList templates;
    {
        templates = new UniqueTemplateList();
    }

    private final UniqueProductList products;
    {
        products = new UniqueProductList();
    }

    private final UniqueJobList jobs;
    {
        jobs = new UniqueJobList();
    }

    private final HistoryList histories;
    {
        histories = new HistoryList();
    }
    private final UniqueMailList mails;
    {
        mails = new UniqueMailList();
    }

    public MyCrm() {}

    /**
     * Creates an MyCrm using the Contacts in the {@code toBeCopied}
     */
    public MyCrm(ReadOnlyMyCrm toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code contacts} must not contain duplicate contacts.
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Replaces the contents of the template list with {@code templates}.
     * {@code templates} must not contain duplicate templates.
     */
    public void setTemplates(List<Template> templates) {
        this.templates.setTemplates(templates);
    }

    /**
     * Replaces the contents of the product list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    public void setProducts(List<Product> products) {
        this.products.setProducts(products);
    }

    /**
     * Replaces the contents of the jobs list with {@code jobs}.
     * {@code jobs} must not contain duplicate jobs.
     */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /**
     * Resets the existing data of this {@code MyCrm} with {@code newData}.
     */
    public void resetData(ReadOnlyMyCrm newData) {
        requireNonNull(newData);

        setContacts(newData.getContactList());
        setTemplates(newData.getTemplateList());
        setProducts(newData.getProductList());
        setJobs(newData.getJobList());
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the myCrm.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Returns true if a template with the same identity as {@code template} exists in the myCrm.
     */
    public boolean hasTemplate(Template template) {
        requireNonNull(template);
        return templates.contains(template);
    }

    /**
     * Returns true if a job with the same identity as {@code job} exists in the myCrm.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a contact to the myCrm.
     * The contact must not already exist in the myCrm.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }

    /**
     * Adds a template to the myCrm.
     * The template must not already exist in the myCrm.
     */
    public void addTemplate(Template t) {
        templates.add(t);
    }

    /**
     * Adds a mail to the myCrm.
     */
    public void addMail(Mail m) {
        mails.add(m);
    }

    /**
     * Adds a job to the myCrm.
     * The job must not already exist in the myCrm.
     */
    public void addJob(Job j) {
        jobs.add(j);
    }

    /**
     * Adds a entered command to the myCrm.
     * The entered command must not already exist in MyCrm.
     */
    public void addHistory(History history) {
        histories.add(history);
    }

    /**
     * Clears all entered command data in the myCrm.
     */
    public void clearHistory() {
        histories.clearHistory();
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the myCrm.
     * The contact identity of {@code editedContact} must not be the same as another existing contact in the myCrm.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Hides the given contact {@code target} in the list
     * {@code target} must exist in the myCrm.
     */
    public void hideContact(Contact target) {
        contacts.hideContact(target);
    }

    /**
     * Undoes hiding the given contact {@code target} in the list
     * {@code target} must exist in the myCrm.
     */
    public void undoHideContact(Contact target) {
        contacts.undoHideContact(target);
    }

    /**
     * Replaces the given template {@code target} in the list with {@code editedTemplate}.
     * {@code target} must exist in the myCrm.
     * The template identity of {@code editedTemplate} must not be the same as another existing template in the
     * myCrm.
     */
    public void setTemplate(Template target, Template editedTemplate) {
        requireNonNull(editedTemplate);

        templates.setTemplate(target, editedTemplate);
    }

    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the myCrm.
     * The job identity of {@code editedJob} must not be the same as another existing job in the
     * myCrm.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);
        jobs.setJob(target, editedJob);
    }

    /**
     * Removes {@code key} from this {@code MyCrm}.
     * {@code key} must exist in the myCrm.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    /**
     * Removes {@code key} from this {@code MyCrm}.
     * {@code key} must exist in the myCrm.
     */
    public void removeTemplate(Template key) {
        templates.remove(key);
    }

    /**
     * Adds a mail to the myCrm.
     */
    public void removeMail(Mail key) {
        mails.remove(key);
    }

    //// Product Methods

    /**
     * Adds a product to the myCrm.
     * The product must not already exist in MyCRM.
     */
    public void addProduct(Product p) {
        products.add(p);
    }

    /**
     * Returns true if a product with the same identity as {@code product} exists in MyCRM.
     */
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return products.contains(product);
    }

    /**
     * Removes {@code key} from this {@code MyCRM}.
     * {@code key} must exist in MyCRM.
     */
    public void removeProduct(Product key) {
        products.remove(key);
    }

    /**
     * Replaces the given product {@code target} in the list with {@code editedProduct}.
     * {@code target} must exist in MyCrm.
     * The product identity of {@code editedProduct} must not be the same as another existing product
     * in MyCrm.
     */
    public void setProduct(Product target, Product editedProduct) {
        requireNonNull(editedProduct);
        products.setProduct(target, editedProduct);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the myCrm.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
    }

    public double getRevenue(LocalDate date) {
        return jobs.getMonthlyRevenue(date);
    }

    //// util methods

    @Override
    public String toString() {
        return contacts.asUnmodifiableObservableList().size() + " contacts";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Template> getTemplateList() {
        return templates.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Product> getProductList() {
        return products.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<History> getHistoryList() {
        return histories.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Mail> getMailList() {
        return mails.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Product> getTopThreeProductList() {
        return jobs.getUnmodifiableTopThreeProductList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MyCrm // instanceof handles nulls
                && contacts.equals(((MyCrm) other).contacts));
    }

    @Override
    public int hashCode() {
        return contacts.hashCode();
    }
}
