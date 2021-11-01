package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.mycrm.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.mycrm.logic.commands.CommandTestUtil.TAG_DESC_FIRST_TIER;
import static seedu.mycrm.logic.commands.CommandTestUtil.TAG_DESC_SECOND_TIER;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TAG_FIRST_TIER;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TAG_SECOND_TIER;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalContacts.AMY;
import static seedu.mycrm.testutil.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.contacts.AddContactCommand;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;
import seedu.mycrm.testutil.ContactBuilder;

class AddContactCommandParserTest {
    private AddContactCommandParser parser = new AddContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Contact expectedContact = new ContactBuilder(BOB).withTags(VALID_TAG_SECOND_TIER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContact));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContact));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContact));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContact));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContact));

        // multiple tags - all accepted
        Contact expectedContactMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_SECOND_TIER,
                VALID_TAG_FIRST_TIER).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER, new AddContactCommand(expectedContactMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContactWithoutTags = new ContactBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddContactCommand(expectedContactWithoutTags));

        // no address
        Contact expectedContactWithoutAddress = new ContactBuilder(AMY).withAddress(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_SECOND_TIER,
                new AddContactCommand(expectedContactWithoutAddress));

        // no email
        Contact expectedContactWithoutEmail = new ContactBuilder(AMY).withEmail(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_SECOND_TIER,
                new AddContactCommand(expectedContactWithoutEmail));

        // no phone
        Contact expectedContactWithoutPhone = new ContactBuilder(AMY).withPhone(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_SECOND_TIER,
                new AddContactCommand(expectedContactWithoutPhone));

        // only address
        Contact expectedContactWithOnlyAddress = new ContactBuilder(AMY).withPhone(null)
                .withEmail(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ADDRESS_DESC_AMY,
                new AddContactCommand(expectedContactWithOnlyAddress));

        // only email
        Contact expectedContactWithOnlyEmail = new ContactBuilder(AMY).withPhone(null)
                .withAddress(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY,
                new AddContactCommand(expectedContactWithOnlyEmail));

        // only Phone
        Contact expectedContactWithOnlyPhone = new ContactBuilder(AMY).withEmail(null)
                .withAddress(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddContactCommand(expectedContactWithOnlyPhone));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        String expectedMessageMissingComponent = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddContactCommand.MESSAGE_AT_LEAST_ONE_COMPONENT);
        // missing at least one field of phone, email, or address
        assertParseFailure(parser, NAME_DESC_BOB,
                expectedMessageMissingComponent);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER, Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + INVALID_ADDRESS_DESC
                + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER, Email.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_SECOND_TIER, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FIRST_TIER + TAG_DESC_SECOND_TIER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
    }
}
