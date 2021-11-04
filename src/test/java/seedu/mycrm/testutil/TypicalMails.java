package seedu.mycrm.testutil;

import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;
import static seedu.mycrm.testutil.TypicalContacts.FIONA;
import static seedu.mycrm.testutil.TypicalJobs.CARL_JOB;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;

public class TypicalMails {

    public static final Template COMPLETED_TEMPLATE = new TemplateBuilder().withSubject(VALID_SUBJECT_COMPLETE)
            .withBody(VALID_BODY_COMPLETE).build();

    public static final Template THANK_YOU_TEMPLATE = new TemplateBuilder().withSubject(VALID_SUBJECT_DONE)
            .withBody(VALID_BODY_DONE).build();

    public static final Job COMPLETED_JOB = new JobBuilder().withCompletionStatus(true).build();

    public static final Mail COMPLETED_MAIL = new MailBuilder().withJob(COMPLETED_JOB)
            .withTemplate(COMPLETED_TEMPLATE).build();

    public static final Contact NO_EMAIL_CONTACT = new ContactBuilder(FIONA).withEmail(null).build();

    public static final Job NO_EMAIL_JOB = new JobBuilder().withCompletionStatus(true)
            .withClient(NO_EMAIL_CONTACT).build();

    public static final Mail THANK_YOU_MAIL = new MailBuilder().withTemplate(THANK_YOU_TEMPLATE).build();

    private TypicalMails() {}

    /**
     * Returns an {@code MyCrm} with all the typical templates.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm ab = new MyCrm();
        ab.addTemplate(COMPLETED_TEMPLATE);
        ab.addJob(CARL_JOB);
        return ab;
    }

}
