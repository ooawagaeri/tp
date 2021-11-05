package seedu.mycrm.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.ReadOnlyUserPrefs;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.ContactBuilder;


class AddContactCommandTest {
    @Test
    public void constructor_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddContactCommand(null));
    }

    @Test
    public void execute_contactAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingContactAdded modelStub = new ModelStubAcceptingContactAdded();
        Contact validContact = new ContactBuilder().build();

        CommandResult commandResult = new AddContactCommand(validContact).execute(modelStub,
            new StateManager(modelStub));

        assertEquals(String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validContact), modelStub.contactsAdded);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact validContact = new ContactBuilder().build();
        AddContactCommand addCommand = new AddContactCommand(validContact);
        ModelStub modelStub = new ModelStubWithContact(validContact);

        assertThrows(CommandException.class, AddContactCommand.MESSAGE_DUPLICATE_CONTACT, () ->
                addCommand.execute(modelStub, new StateManager(modelStub)));
    }

    @Test
    public void equals() {
        Contact alice = new ContactBuilder().withName("Alice").build();
        Contact bob = new ContactBuilder().withName("Bob").build();
        AddContactCommand addAliceCommand = new AddContactCommand(alice);
        AddContactCommand addBobCommand = new AddContactCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddContactCommand addAliceCommandCopy = new AddContactCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different contact -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMyCrmFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyCrmFilePath(Path myCrmFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTemplate(Template template) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMail(Mail mail) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTemplate(Template target, Template editedTemplate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyCrm(ReadOnlyMyCrm newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMyCrm getMyCrm() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTemplate(Template template) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJob(Job toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTemplate(Template target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteJob(Job target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJob(Job job, Job editedJob) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void hideContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoHideContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHistory(History history) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getRevenue(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredTopThreeProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredIncompleteJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredAllJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredMonthlyCompletedJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Job> getLatestJobPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Mail> getFilteredMailList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<History> getFilteredHistoryList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTemplateList(Predicate<Template> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMailList(Predicate<Mail> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHistoryList(Predicate<History> predicate) {
            throw new AssertionError("This method should not be called.");
        };
    }

    /**
     * A Model stub that contains a single contact.
     */
    private class ModelStubWithContact extends ModelStub {
        private final Contact contact;

        ModelStubWithContact(Contact contact) {
            requireNonNull(contact);
            this.contact = contact;
        }

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return this.contact.isSameContact(contact);
        }
    }

    /**
     * A Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingContactAdded extends ModelStub {
        final ArrayList<Contact> contactsAdded = new ArrayList<>();

        @Override
        public boolean hasContact(Contact contact) {
            requireNonNull(contact);
            return contactsAdded.stream().anyMatch(contact::isSameContact);
        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public ReadOnlyMyCrm getMyCrm() {
            return new MyCrm();
        }
    }
}
