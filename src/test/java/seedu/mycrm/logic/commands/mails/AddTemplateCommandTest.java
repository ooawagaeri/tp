package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.mycrm.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
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
import seedu.mycrm.testutil.TemplateBuilder;

class AddTemplateCommandTest {
    @Test
    public void constructor_nullTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTemplateCommand(null));
    }

    @Test
    public void execute_templateAcceptedByModel_addSuccessful() throws Exception {
        AddTemplateCommandTest.ModelStubAcceptingTemplateAdded modelStub =
                new ModelStubAcceptingTemplateAdded();
        Template validTemplate = new TemplateBuilder().build();

        CommandResult commandResult = new AddTemplateCommand(validTemplate).execute(modelStub,
            new StateManager(modelStub));

        assertEquals(String.format(AddTemplateCommand.MESSAGE_SUCCESS, validTemplate),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validTemplate), modelStub.templatesAdded);
    }

    @Test
    public void execute_duplicateTemplate_throwsCommandException() {
        Template validTemplate = new TemplateBuilder().build();
        AddTemplateCommand addCommand = new AddTemplateCommand(validTemplate);
        AddTemplateCommandTest.ModelStub modelStub = new ModelStubWithTemplate(validTemplate);

        assertThrows(CommandException.class, AddTemplateCommand.MESSAGE_DUPLICATE_TEMPLATE, (
            ) -> addCommand.execute(modelStub, new StateManager(modelStub)));
    }

    @Test
    public void type() {
        Template validTemplate = new TemplateBuilder().build();
        AddTemplateCommand addCommand = new AddTemplateCommand(validTemplate);

        assertEquals(addCommand.getType(), CommandType.TEMPLATE);
        assertNotEquals(addCommand.getType(), CommandType.MAIL);
    }
    @Test
    public void equals() {
        Template alice = new TemplateBuilder().withSubject("To Alice").build();
        Template bob = new TemplateBuilder().withSubject("To Bob").build();
        AddTemplateCommand addAliceCommand = new AddTemplateCommand(alice);
        AddTemplateCommand addBobCommand = new AddTemplateCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddTemplateCommand addAliceCommandCopy = new AddTemplateCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different template -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
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
        public boolean hasTemplate(Template contact) {
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
    private static class ModelStubWithTemplate extends AddTemplateCommandTest.ModelStub {
        private final Template template;

        ModelStubWithTemplate(Template template) {
            requireNonNull(template);
            this.template = template;
        }

        @Override
        public boolean hasTemplate(Template template) {
            requireNonNull(template);
            return this.template.isSameTemplate(template);
        }
    }

    /**
     * A Model stub that always accept the template being added.
     */
    private static class ModelStubAcceptingTemplateAdded extends AddTemplateCommandTest.ModelStub {
        final ArrayList<Template> templatesAdded = new ArrayList<>();

        @Override
        public boolean hasTemplate(Template template) {
            requireNonNull(template);
            return templatesAdded.stream().anyMatch(template::isSameTemplate);
        }

        @Override
        public void addTemplate(Template template) {
            requireNonNull(template);
            templatesAdded.add(template);
        }

        @Override
        public ReadOnlyMyCrm getMyCrm() {
            return new MyCrm();
        }
    }
}
