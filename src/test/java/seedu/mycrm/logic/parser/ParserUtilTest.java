package seedu.mycrm.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.Address;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobFee;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_CONTACT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CONTACT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseJobFee_validValue_returnsJobFee() throws Exception {
        String fee = "$30.00";
        JobFee expectedJobFee = new JobFee("$30.00");
        assertEquals(ParserUtil.parseJobFee(fee), expectedJobFee);

        // without dollar sign
        fee = "30.00";
        assertEquals(ParserUtil.parseJobFee(fee), expectedJobFee);

        // just below the maximum permissible value for job fee
        fee = "9999999";
        expectedJobFee = new JobFee("9999999");
        assertEquals(ParserUtil.parseJobFee(fee), expectedJobFee);

        // smallest denomination is 1 cent. Any more precision should be truncated
        fee = "9999999.999";
        expectedJobFee = new JobFee("9999999.99");
        assertEquals(ParserUtil.parseJobFee(fee), expectedJobFee);

        fee = "0.000001";
        expectedJobFee = new JobFee("0.00");
        assertEquals(ParserUtil.parseJobFee(fee), expectedJobFee);
    }


    @Test
    public void parseJobFee_invalidValue_throwsParseException() throws Exception {
        //negative values
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("-30.00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("-10000000"));

        // more than or equal to max value
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("10000000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("1000000000000"));

        // weird formats
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("30.00$"));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("30.$00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobFee("10.-01"));
    }


    @Test
    public void parseJobDate_validValue_returnsJobDate() throws Exception {
        String date = "10/12/2021";
        JobDate expectedDate = new JobDate("10/12/2021");
        assertEquals(ParserUtil.parseJobDate(date, ""), expectedDate);

        date = "01/12/2021";
        expectedDate = new JobDate("01/12/2021");
        assertEquals(ParserUtil.parseJobDate(date, ""), expectedDate);

        date = "10/01/2021";
        expectedDate = new JobDate("10/01/2021");
        assertEquals(ParserUtil.parseJobDate(date, ""), expectedDate);

        // single digit day and month
        date = "1/10/2021";
        expectedDate = new JobDate("1/10/2021");
        assertEquals(ParserUtil.parseJobDate(date, ""), expectedDate);

        date = "01/1/2021";
        expectedDate = new JobDate("01/1/2021");
        assertEquals(ParserUtil.parseJobDate(date, ""), expectedDate);
    }


    @Test
    public void parseJobDate_invalidValue_throwsParseException() throws Exception {


        // invalid month value
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("01/13/2021", ""));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("01/0/2021", ""));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("01/-1/2021", ""));

        // invalid day value
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("32/13/2021", ""));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("00/11/2021", ""));
        assertThrows(ParseException.class, () -> ParserUtil.parseJobDate("-1/11/2021", ""));
    }
}
