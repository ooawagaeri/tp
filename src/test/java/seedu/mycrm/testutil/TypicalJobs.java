package seedu.mycrm.testutil;

import static seedu.mycrm.testutil.TypicalContacts.BENSON;
import static seedu.mycrm.testutil.TypicalContacts.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.job.Job;

public class TypicalJobs {

    public static final Job COMPLETED = new JobBuilder().withCompletionStatus(true).build();

    public static final Job INCOMPLETE = new JobBuilder().withCompletionStatus(false).build();

    public static final Job BENSON_JOB = new JobBuilder().withClient(BENSON).build();

    public static final Job CARL_JOB = new JobBuilder().withClient(CARL).withCompletionStatus(false).build();

    private TypicalJobs() {}

    /**
     * Returns an {@code MyCrm} with all the typical jobs.
     */
    public static MyCrm getTypicalMyCrm() {
        MyCrm ab = new MyCrm();
        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        return ab;
    }

    /**
     * Returns list of typical jobs.
     */
    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(COMPLETED, CARL_JOB));
    }
}
