package seedu.mycrm.testutil;

import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.testutil.TypicalJobs.CARL_JOB;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;

public class TypicalMails {

    public static final Template COMPLETED_TEMPLATE = new TemplateBuilder().withSubject(VALID_SUBJECT_COMPLETE)
            .withBody("Dear valued customer, your order has been completed and ready for collection").build();

    public static final Job COMPLETED_JOB = new JobBuilder().withCompletionStatus(true).build();

    public static final Mail COMPLETED_MAIL = new MailBuilder().build();

    private TypicalMails() {}

    /**
     * Returns an {@code MyCrm} with all the typical templates.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm ab = new MyCrm();
        ab.addJob(CARL_JOB);
        ab.addTemplate(COMPLETED_TEMPLATE);
        return ab;
    }

}
