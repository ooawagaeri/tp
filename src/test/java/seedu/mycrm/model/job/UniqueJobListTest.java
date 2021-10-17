package seedu.mycrm.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalJobs.COMPLETED;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.job.exceptions.DuplicateJobException;
import seedu.mycrm.model.job.exceptions.JobNotFoundException;

public class UniqueJobListTest {
    private final UniqueJobList uniqueJobList = new UniqueJobList();

    @Test
    public void contains_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.contains(null));
    }

    @Test
    public void contains_jobNotInList_returnsFalse() {
        assertFalse(uniqueJobList.contains(COMPLETED));
    }

    @Test
    public void contains_jobInList_returnsTrue() {
        uniqueJobList.add(COMPLETED);
        assertTrue(uniqueJobList.contains(COMPLETED));
    }

    @Test
    public void add_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.add(null));
    }

    @Test
    public void add_duplicateJob_throwsDuplicateJobException() {
        uniqueJobList.add(COMPLETED);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.add(COMPLETED));
    }

    @Test
    public void setJob_nullTargetJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(null, COMPLETED));
    }

    @Test
    public void setJob_nullEditedJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(COMPLETED, null));
    }

    @Test
    public void setJob_targetJobNotInList_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.setJob(COMPLETED, COMPLETED));
    }

    @Test
    public void remove_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.remove(null));
    }

    @Test
    public void remove_jobDoesNotExist_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.remove(COMPLETED));
    }

    @Test
    public void remove_existingJob_removesJob() {
        uniqueJobList.add(COMPLETED);
        uniqueJobList.remove(COMPLETED);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullUniqueJobList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((UniqueJobList) null));
    }

    @Test
    public void setJobs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((List<Job>) null));
    }

    @Test
    public void setJobs_listWithDuplicateJobs_throwsDuplicateJobException() {
        List<Job> listWithDuplicateJobs = Arrays.asList(COMPLETED, COMPLETED);
        assertThrows(DuplicateJobException.class, ()
            -> uniqueJobList.setJobs(listWithDuplicateJobs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueJobList.asUnmodifiableObservableList().remove(0));
    }
}
