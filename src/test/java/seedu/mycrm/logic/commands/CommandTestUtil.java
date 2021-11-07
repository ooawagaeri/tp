package seedu.mycrm.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_RECEIVED_DATE;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TEMPLATE_INDEX;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_NAME;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.contacts.EditContactCommand;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand.EditTemplateDescriptor;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.NameContainsKeywordsPredicate;
import seedu.mycrm.model.mail.SubjectContainsKeywordsPredicate;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.ProductNameContainsKeywordsPredicate;
import seedu.mycrm.model.product.Type;
import seedu.mycrm.testutil.EditContactDescriptorBuilder;
import seedu.mycrm.testutil.EditTemplateDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_FIRST_TIER = "1st tier";
    public static final String VALID_TAG_SECOND_TIER = "2nd tier";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_SECOND_TIER = " " + PREFIX_TAG + VALID_TAG_SECOND_TIER;
    public static final String TAG_DESC_FIRST_TIER = " " + PREFIX_TAG + VALID_TAG_FIRST_TIER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "cooperation friend*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_SUBJECT_ISSUE = "An issue has occurred";
    public static final String VALID_SUBJECT_DONE = "Order is done";
    public static final String VALID_SUBJECT_COMPLETE = "Your order has been completed";
    public static final String VALID_BODY_DONE = "Your order is done and ready for collection";
    public static final String VALID_BODY_COMPLETE = "Your order has been completed and ready for collection";

    public static final String SUBJECT_DESC_COMPLETE = " " + PREFIX_SUBJECT + VALID_SUBJECT_COMPLETE;
    public static final String SUBJECT_DESC_DONE = " " + PREFIX_SUBJECT + VALID_SUBJECT_DONE;
    public static final String BODY_DESC_COMPLETE = " " + PREFIX_BODY + VALID_BODY_COMPLETE;
    public static final String BODY_DESC_DONE = " " + PREFIX_BODY + VALID_BODY_DONE;
    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT + "W#lcome";
    public static final String INVALID_BODY_DESC = " " + PREFIX_BODY + " ";
    public static final int HEAD_SUBJECT_SIZE = 2;

    public static final String VALID_JOB_INDEX_1 = "1";
    public static final String VALID_JOB_INDEX_2 = "2";
    public static final String VALID_TEMPLATE_INDEX_1 = "1";
    public static final String VALID_TEMPLATE_INDEX_2 = "2";
    public static final String JOB_INDEX_DESC_1 = " " + PREFIX_JOB_INDEX + VALID_JOB_INDEX_1;
    public static final String JOB_INDEX_DESC_2 = " " + PREFIX_JOB_INDEX + VALID_JOB_INDEX_2;
    public static final String TEMPLATE_INDEX_DESC_1 = " " + PREFIX_TEMPLATE_INDEX + VALID_TEMPLATE_INDEX_1;
    public static final String TEMPLATE_INDEX_DESC_2 = " " + PREFIX_TEMPLATE_INDEX + VALID_TEMPLATE_INDEX_2;
    public static final String INVALID_JOB_INDEX_DESC = " " + PREFIX_JOB_INDEX + " w";
    public static final String INVALID_TEMPLATE_INDEX_DESC = " " + PREFIX_TEMPLATE_INDEX + " #";

    public static final EditTemplateDescriptor DESC_DONE;
    public static final EditTemplateDescriptor DESC_COMPLETE;

    public static final EditContactCommand.EditContactDescriptor DESC_AMY;
    public static final EditContactCommand.EditContactDescriptor DESC_BOB;

    public static final ProductName VALID_PRODUCT_NAME = DEFAULT_PRODUCT_ONE_NAME;
    public static final Type VALID_PRODUCT_TYPE = DEFAULT_PRODUCT_ONE_TYPE;
    public static final Manufacturer VALID_PRODUCT_MANUFACTURER = DEFAULT_PRODUCT_ONE_MANUFACTURER;
    public static final Description VALID_PRODUCT_DESCRIPTION = DEFAULT_PRODUCT_ONE_DESCRIPTION;

    public static final String PRODUCT_NAME_DESC = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_NAME;
    public static final String PRODUCT_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE + VALID_PRODUCT_TYPE;
    public static final String PRODUCT_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER
            + VALID_PRODUCT_MANUFACTURER;
    public static final String PRODUCT_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION + VALID_PRODUCT_DESCRIPTION;

    public static final String PRODUCT_TWO_NAME_DESC = " " + PREFIX_PRODUCT_NAME + DEFAULT_PRODUCT_TWO_NAME;
    public static final String PRODUCT_TWO_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE + DEFAULT_PRODUCT_TWO_TYPE;
    public static final String PRODUCT_TWO_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER
            + DEFAULT_PRODUCT_TWO_MANUFACTURER;
    public static final String PRODUCT_TWO_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION
            + DEFAULT_PRODUCT_TWO_DESCRIPTION;

    public static final String PRODUCT_EMPTY_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE;
    public static final String PRODUCT_EMPTY_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER;
    public static final String PRODUCT_EMPTY_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION;

    public static final String INVALID_PRODUCT_NAME_DESC = " " + PREFIX_PRODUCT_NAME
            + PRODUCT_TYPE_DESC; // product name is empty

    public static final String VALID_JOB_DESCRIPTION = "Repair screen";
    public static final String VALID_JOB_FEE = "$30.00";
    public static final String VALID_JOB_RECEIVED_DATE = "20/10/2021";
    public static final String VALID_JOB_EXPECTED_COMPLETION_DATE = "25/10/2021";
    public static final String VALID_INDEX = "1";

    public static final String INVALID_JOB_DESCRIPTION = "   ";
    public static final String INVALID_JOB_FEE = "$-30.00";
    public static final String INVALID_JOB_RECEIVED_DATE = "20/10/21";
    public static final String INVALID_JOB_EXPECTED_COMPLETION_DATE = "25/10/21";
    public static final String INVALID_INDEX = "-1";

    public static final String VALID_JOB_DESCRIPTION_DESC = " " + PREFIX_JOB_DESCRIPTION + VALID_JOB_DESCRIPTION;
    public static final String VALID_JOB_FEE_DESC = " " + PREFIX_FEE + VALID_JOB_FEE;
    public static final String VALID_JOB_RECEIVED_DATE_DESC = " " + PREFIX_RECEIVED_DATE + VALID_JOB_RECEIVED_DATE;
    public static final String VALID_JOB_EXPECTED_COMPLETION_DATE_DESC = " " + PREFIX_EXPECTED_COMPLETION_DATE
            + VALID_JOB_EXPECTED_COMPLETION_DATE;
    public static final String VALID_JOB_CONTACT_INDEX_DESC = " " + PREFIX_CONTACT_INDEX + VALID_INDEX;
    public static final String VALID_JOB_PRODUCT_INDEX_DESC = " " + PREFIX_PRODUCT_INDEX + VALID_INDEX;

    public static final String INVALID_JOB_DESCRIPTION_DESC = " " + PREFIX_JOB_DESCRIPTION + INVALID_JOB_DESCRIPTION;
    public static final String INVALID_JOB_FEE_DESC = " " + PREFIX_FEE + INVALID_JOB_FEE;
    public static final String INVALID_JOB_RECEIVED_DATE_DESC = " " + PREFIX_RECEIVED_DATE + INVALID_JOB_RECEIVED_DATE;
    public static final String INVALID_JOB_EXPECTED_COMPLETION_DATE_DESC = " " + PREFIX_EXPECTED_COMPLETION_DATE
        + INVALID_JOB_EXPECTED_COMPLETION_DATE;
    public static final String INVALID_JOB_CONTACT_INDEX_DESC = " " + PREFIX_CONTACT_INDEX + INVALID_INDEX;
    public static final String INVALID_JOB_PRODUCT_INDEX_DESC = " " + PREFIX_PRODUCT_INDEX + INVALID_INDEX;

    static {
        DESC_AMY = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_SECOND_TIER).build();
        DESC_BOB = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FIRST_TIER, VALID_TAG_SECOND_TIER).build();
        DESC_DONE = new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_DONE)
                .withBody(VALID_BODY_DONE).build();
        DESC_COMPLETE = new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_COMPLETE)
                .withBody(VALID_BODY_COMPLETE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, new StateManager(actualModel));
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, command.getType());
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the myCrm, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MyCrm expectedMyCrm = new MyCrm(actualModel.getMyCrm());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel,
            new StateManager(actualModel)));
        assertEquals(expectedMyCrm, actualModel.getMyCrm());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }
    /**
     * Updates {@code model}'s filtered unhidden contacts list to show only the contact at the given {@code targetIndex}
     * in the {@code model}'s myCrm.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the template at the given {@code targetIndex} in the
     * {@code model}'s myCrm.
     */
    public static void showTemplateAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTemplateList().size());

        Template template = model.getFilteredTemplateList().get(targetIndex.getZeroBased());
        final String[] splitSubject = template.getSubject().subject.split("\\s+");
        model.updateFilteredTemplateList(new SubjectContainsKeywordsPredicate(Stream.of(splitSubject)
                .limit(HEAD_SUBJECT_SIZE)
                .collect(Collectors.toList())));

        assertEquals(1, model.getFilteredTemplateList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the product at the given {@code targetIndex} in the
     * {@code model}'s myCrm.
     */
    public static void showProductAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProductList().size());

        Product product = model.getFilteredProductList().get(targetIndex.getZeroBased());
        final String[] splitName = product.getName().orElse("").split("\\s+");
        model.updateFilteredProductList(new ProductNameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredProductList().size());
    }
}
