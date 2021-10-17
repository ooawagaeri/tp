package seedu.mycrm.model.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;
import static seedu.mycrm.testutil.TypicalTemplates.THANK_YOU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.testutil.TemplateBuilder;

public class TemplateTest {
    @Test
    public void isSameTemplate() {
        // same object -> returns true
        assertTrue(THANK_YOU.isSameTemplate(THANK_YOU));

        // null -> returns false
        assertFalse(THANK_YOU.isSameTemplate(null));

        // same subject, all other attributes different -> returns true
        Template editedThanks = new TemplateBuilder(THANK_YOU).withBody(VALID_BODY_DONE).build();
        assertTrue(THANK_YOU.isSameTemplate(editedThanks));

        // different subject, all other attributes same -> returns false
        editedThanks = new TemplateBuilder(THANK_YOU).withSubject(VALID_SUBJECT_DONE).build();
        assertFalse(THANK_YOU.isSameTemplate(editedThanks));

        // subject differs in case, all other attributes same -> returns false
        Template editedBob = new TemplateBuilder(COMPLETED).withSubject(VALID_SUBJECT_COMPLETE.toLowerCase()).build();
        assertFalse(COMPLETED.isSameTemplate(editedBob));

        // subject has trailing spaces, all other attributes same -> returns false
        String subjectWithTrailingSpaces = VALID_SUBJECT_COMPLETE + " ";
        editedBob = new TemplateBuilder(COMPLETED).withSubject(subjectWithTrailingSpaces).build();
        assertFalse(COMPLETED.isSameTemplate(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Template thanksCopy = new TemplateBuilder(THANK_YOU).build();
        assertEquals(THANK_YOU, thanksCopy);

        // same object -> returns true
        assertEquals(THANK_YOU, THANK_YOU);

        // null -> returns false
        assertNotEquals(null, THANK_YOU);

        // different type -> returns false
        assertNotEquals(5, THANK_YOU);

        // different template -> returns false
        assertNotEquals(THANK_YOU, COMPLETED);

        // different subject -> returns false
        Template editedThanks = new TemplateBuilder(THANK_YOU).withSubject(VALID_SUBJECT_DONE).build();
        assertNotEquals(THANK_YOU, editedThanks);

        // different body -> returns false
        editedThanks = new TemplateBuilder(THANK_YOU).withBody(VALID_BODY_DONE).build();
        assertNotEquals(THANK_YOU, editedThanks);
    }
}
