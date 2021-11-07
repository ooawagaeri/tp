package seedu.mycrm.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.commons.util.StringUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.Address;
import seedu.mycrm.model.contact.Email;
import seedu.mycrm.model.contact.Name;
import seedu.mycrm.model.contact.Phone;
import seedu.mycrm.model.contact.tag.Tag;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String subject} into an {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses a {@code String body} into an {@code Body}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code body} is invalid.
     */
    public static Body parseBody(String body) throws ParseException {
        requireNonNull(body);
        String trimmedBody = body.trim();
        if (!Body.isValidBody(trimmedBody)) {
            throw new ParseException(Body.MESSAGE_CONSTRAINTS);
        }
        return new Body(trimmedBody);
    }

    /**
     * Parses a {@code String jobDescription} into an {@code JobDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobDescription} is invalid.
     */
    public static JobDescription parseJobDescription(String jobDescription) throws ParseException {
        requireNonNull(jobDescription);
        String trimmedJobDescription = jobDescription.trim();
        if (!JobDescription.isValidJobDescription(trimmedJobDescription)) {
            throw new ParseException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        return new JobDescription(trimmedJobDescription);
    }

    /**
     * Parses a {@code String date} into an {@code JobDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static JobDate parseJobDate(String date, String attributeName) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!JobDate.isValidJobDate(date)) {
            throw new ParseException(attributeName + " " + JobDate.MESSAGE_CONSTRAINTS);
        }
        return new JobDate(trimmedDate);
    }

    /**
     * Parses a {@code String date} into an {@code JobDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static JobFee parseJobFee(String fee) throws ParseException {
        requireNonNull(fee);
        if (!JobFee.isValidJobFee(fee)) {
            throw new ParseException(JobFee.MESSAGE_CONSTRAINTS);
        }
        return new JobFee(fee);
    }

    /**
     * Parses {@code string} and returns it. Leading and trailing whitespaces will be trimmed.
     */
    public static String parseString(String line) throws ParseException {
        return line.trim();
    }
}
