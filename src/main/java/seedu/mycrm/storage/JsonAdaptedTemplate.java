package seedu.mycrm.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.model.mail.Template;

/**
 * Jackson-friendly version of {@link Template}.
 */
class JsonAdaptedTemplate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Template's %s field is missing!";

    private final String subject;
    private final String body;

    /**
     * Constructs a {@code JsonAdaptedTemplate} with the given template details.
     */
    @JsonCreator
    public JsonAdaptedTemplate(@JsonProperty("subject") String subject, @JsonProperty("body") String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Converts a given {@code Template} into this class for Jackson use.
     */
    public JsonAdaptedTemplate(Template source) {
        subject = source.getSubject().toString();
        body = source.getBody().toString();
    }

    /**
     * Converts this Jackson-friendly adapted template object into the model's {@code Template} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted template.
     */
    public Template toModelType() throws IllegalValueException {

        validateSubject();
        final Subject modelSubject = new Subject(subject);

        validateBody();
        final Body modelBody = new Body(body);

        return new Template(modelSubject, modelBody);
    }

    /**
     * Checks subject of {@code Job} of {@code JsonAdaptedTemplate}.
     *
     * @throws IllegalValueException if there are subject constraints violated in the adapted job.
     */
    private void validateSubject() throws IllegalValueException {
        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks body of {@code Job} of {@code JsonAdaptedTemplate}.
     *
     * @throws IllegalValueException if there are body constraints violated in the adapted job.
     */
    private void validateBody() throws IllegalValueException {
        if (body == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Body.class.getSimpleName()));
        }
        if (!Body.isValidBody(body)) {
            throw new IllegalValueException(Body.MESSAGE_CONSTRAINTS);
        }
    }

}
