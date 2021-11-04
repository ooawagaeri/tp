package seedu.mycrm.model.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalJobs.BENSON_JOB;
import static seedu.mycrm.testutil.TypicalMails.COMPLETED_JOB;
import static seedu.mycrm.testutil.TypicalMails.COMPLETED_MAIL;
import static seedu.mycrm.testutil.TypicalMails.COMPLETED_TEMPLATE;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;
import static seedu.mycrm.testutil.TypicalTemplates.DONE;
import static seedu.mycrm.testutil.TypicalTemplates.THANK_YOU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.job.Job;
import seedu.mycrm.testutil.JobBuilder;
import seedu.mycrm.testutil.MailBuilder;
import seedu.mycrm.testutil.TemplateBuilder;

public class MailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Job validJob = new JobBuilder().build();
        Template validTemplate = new TemplateBuilder().build();

        assertThrows(NullPointerException.class, () -> new Mail(null, null));
        assertThrows(NullPointerException.class, () -> new Mail(validJob, null));
        assertThrows(NullPointerException.class, () -> new Mail(null, validTemplate));
    }

    @Test
    public void isSameMail() {
        // same object -> returns true
        assertTrue(COMPLETED_MAIL.isSameMail(COMPLETED_MAIL));

        // null -> returns false
        assertFalse(COMPLETED_MAIL.isSameMail(null));

        // different template, all other attributes same -> returns false
        Mail editedCompleted = new MailBuilder(COMPLETED_MAIL).withJob(BENSON_JOB).build();
        assertFalse(COMPLETED_MAIL.isSameMail(editedCompleted));

        // different template, all other attributes same -> returns false
        editedCompleted = new MailBuilder(COMPLETED_MAIL).withTemplate(DONE).build();
        assertFalse(COMPLETED_MAIL.isSameMail(editedCompleted));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Mail completedCopy = new MailBuilder(COMPLETED_MAIL).build();
        assertEquals(COMPLETED_MAIL, completedCopy);

        // same object -> returns true
        assertEquals(COMPLETED_MAIL, COMPLETED_MAIL);

        // null -> returns false
        assertNotEquals(null, COMPLETED_MAIL);

        // different type -> returns false
        assertNotEquals(5, COMPLETED_MAIL);

        // different job -> returns false
        Mail editedMail = new MailBuilder(COMPLETED_MAIL).withJob(BENSON_JOB).build();
        assertNotEquals(COMPLETED_MAIL, editedMail);

        // different template -> returns false
        editedMail = new MailBuilder(COMPLETED_MAIL).withTemplate(THANK_YOU).build();
        assertNotEquals(COMPLETED_MAIL, editedMail);
    }

    @Test
    public void isSameString() {
        Job job = new JobBuilder().build();
        Template template = new TemplateBuilder().build();
        Mail mail = new MailBuilder().build();
        assertEquals(mail.toString(), String.format("Job: %s; Template: %s", job, template));
    }

    @Test
    public void isSameConstructMail() {
        Mail completedMail = new MailBuilder().withJob(COMPLETED_JOB).withTemplate(COMPLETED).build();
        Mail thankMail = new MailBuilder().withJob(COMPLETED_JOB).withTemplate(THANK_YOU).build();

        assertEquals(completedMail.constructMail(), String.format("mailto:%s?subject=%s&body=%s",
                COMPLETED_JOB.getClientEmail(), Mail.urlEncode(COMPLETED.getSubject().toString()),
                Mail.urlEncode(COMPLETED.getBody().toString())));

        assertNotEquals(thankMail.constructMail(), String.format("mailto:%s?subject=%s&body=%s",
                COMPLETED_JOB.getClientEmail(), Mail.urlEncode(COMPLETED.getSubject().toString()),
                Mail.urlEncode(COMPLETED.getBody().toString())));
    }

    @Test
    public void isSameUrlEncode() {
        String subject = "Your Order is Completed";
        String expectedSubject = "Your%20Order%20is%20Completed";

        assertEquals(Mail.urlEncode(subject), expectedSubject);
    }

    @Test
    public void isSameEmail() {
        String email = COMPLETED_MAIL.getMailEmail();
        String expectedEmail = COMPLETED_JOB.getClientEmail();

        assertEquals(email, expectedEmail);
    }

    @Test
    public void isSameSubject() {
        String subject = COMPLETED_MAIL.getMailSubject();
        String expectedSubject = COMPLETED_TEMPLATE.getSubject().toString();

        assertEquals(subject, expectedSubject);
    }

    @Test
    public void isSameBody() {
        String body = COMPLETED_MAIL.getMailBody();
        String expectedBody = COMPLETED_TEMPLATE.getBody().toString();

        assertEquals(body, expectedBody);
    }
}
