package seedu.mycrm.model.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalMails.COMPLETED_MAIL;
import static seedu.mycrm.testutil.TypicalMails.THANK_YOU_MAIL;
import static seedu.mycrm.testutil.TypicalTemplates.THANK_YOU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.mail.exceptions.MailNotFoundException;
import seedu.mycrm.testutil.MailBuilder;

public class UniqueMailListTest {
    private final UniqueMailList uniqueMailList = new UniqueMailList();

    @Test
    public void contains_nullMail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMailList.contains(null));
    }

    @Test
    public void contains_mailNotInList_returnsFalse() {
        assertFalse(uniqueMailList.contains(COMPLETED_MAIL));
    }

    @Test
    public void contains_mailInList_returnsTrue() {
        uniqueMailList.add(COMPLETED_MAIL);
        assertTrue(uniqueMailList.contains(COMPLETED_MAIL));
    }

    @Test
    public void contains_mailWithSameIdentityFieldsInList_returnsFalse() {
        uniqueMailList.add(COMPLETED_MAIL);
        Mail editedAlice = new MailBuilder(COMPLETED_MAIL).withTemplate(THANK_YOU).build();
        assertFalse(uniqueMailList.contains(editedAlice));
    }

    @Test
    public void add_nullMail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMailList.add(null));
    }

    @Test
    public void add_multipleMail() {
        uniqueMailList.add(COMPLETED_MAIL);
        uniqueMailList.add(THANK_YOU_MAIL);
        assertFalse(uniqueMailList.contains(COMPLETED_MAIL));
        assertTrue(uniqueMailList.contains(THANK_YOU_MAIL));
    }

    @Test
    public void remove_nullMail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMailList.remove(null));
    }

    @Test
    public void remove_mailDoesNotExist_throwsMailNotFoundException() {
        assertThrows(MailNotFoundException.class, () -> uniqueMailList.remove(COMPLETED_MAIL));
    }

    @Test
    public void remove_existingMail_removesMail() {
        uniqueMailList.add(COMPLETED_MAIL);
        uniqueMailList.remove(COMPLETED_MAIL);
        UniqueMailList expectedUniqueMailList = new UniqueMailList();
        assertEquals(expectedUniqueMailList, uniqueMailList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMailList.asUnmodifiableObservableList().remove(0));
    }
}
