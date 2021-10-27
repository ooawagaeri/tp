package seedu.mycrm.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.storage.JsonAdaptedTemplate.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;

public class JsonAdaptedTemplateTest {
    private static final String INVALID_SUBJECT = "C@mpleted";
    private static final String INVALID_BODY = " ";

    private static final String VALID_SUBJECT = COMPLETED.getSubject().toString();
    private static final String VALID_BODY = COMPLETED.getBody().toString();

    @Test
    public void toModelType_validTemplateDetails_returnsTemplate() throws Exception {
        JsonAdaptedTemplate template = new JsonAdaptedTemplate(COMPLETED);
        assertEquals(COMPLETED, template.toModelType());
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedTemplate template =
                new JsonAdaptedTemplate(INVALID_SUBJECT, VALID_BODY);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, template::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedTemplate template = new JsonAdaptedTemplate(null, VALID_BODY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, template::toModelType);
    }

    @Test
    public void toModelType_nullBody_throwsIllegalValueException() {
        JsonAdaptedTemplate template = new JsonAdaptedTemplate(VALID_SUBJECT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Body.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, template::toModelType);
    }

    @Test
    public void toModelType_invalidBody_throwsIllegalValueException() {
        JsonAdaptedTemplate template =
                new JsonAdaptedTemplate(VALID_SUBJECT, INVALID_BODY);
        String expectedMessage = Body.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, template::toModelType);
    }
}
