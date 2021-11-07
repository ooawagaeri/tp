package seedu.mycrm.testutil;

import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalContacts.BENSON;
import static seedu.mycrm.testutil.TypicalContacts.CARL;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;

public class TypicalJobs {

    public static final Job COMPLETED = new JobBuilder().withCompletionDate("13/12/2021").withCompletionStatus(true)
            .build();

    public static final Job INCOMPLETED = new JobBuilder().build();

    public static final Contact ALICE_TEMP = new ContactBuilder(ALICE).build();

    public static final Contact CARL_TEMP = new ContactBuilder(CARL).build();

    public static final Job BENSON_JOB = new JobBuilder().withClient(BENSON).build();

    public static final Job CARL_JOB = new JobBuilder().withClient(CARL).withCompletionStatus(false).build();

    private TypicalJobs() {}

    /**
     * Returns an {@code MyCrm} with all the typical jobs.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm ab = new MyCrm();
        ab.addContact(ALICE_TEMP);
        ab.addContact(CARL_TEMP);

        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        ab.addProduct(INTEL_CPU);
        ab.addProduct(ASUS_GPU);
        return ab;
    }

    /**
     * Returns list of typical jobs.
     */
    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(COMPLETED, CARL_JOB));
    }
}
