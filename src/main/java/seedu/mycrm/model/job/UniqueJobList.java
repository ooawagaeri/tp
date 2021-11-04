package seedu.mycrm.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.mycrm.model.job.exceptions.DuplicateJobException;
import seedu.mycrm.model.job.exceptions.JobNotFoundException;
import seedu.mycrm.model.product.Product;

/**
 * A list of jobs that enforces uniqueness between its elements and does not allow nulls.
 * A job is considered unique by comparing using {@code Template#isSameJob(Template)}. As such, adding and
 * updating of jobs uses Job#isSameJob(Template) for equality to ensure that the job being added
 * or updated is unique in terms of identity in the UniqueJobList. However, the removal of a job uses
 * Job#equals (Object) to ensure that the job with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Job#isSameJob(Job)
 */
public class UniqueJobList implements Iterable<Job> {

    private final ObservableList<Job> internalList = FXCollections.observableArrayList();
    private final ObservableList<Job> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent job as the given argument.
     */
    public boolean contains(Job toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameJob);
    }

    /**
     * Adds a job to the list.
     * The job must not already exist in the list.
     */
    public void add(Job toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateJobException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the list.
     * The job identity of {@code editedJob} must not be the same as another existing job in the list.
     */
    public void setJob(Job target, Job editedJob) {
        requireAllNonNull(target, editedJob);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new JobNotFoundException();
        }

        if (!target.isSameJob(editedJob) && contains(editedJob)) {
            throw new DuplicateJobException();
        }

        internalList.set(index, editedJob);
    }

    /**
     * Removes the equivalent job from the list.
     * The job must exist in the list.
     */
    public void remove(Job toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new JobNotFoundException();
        }
    }

    public void setJobs(UniqueJobList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code jobs}.
     * {@code jobs} must not contain duplicate jobs.
     */
    public void setJobs(List<Job> jobs) {
        requireAllNonNull(jobs);
        if (!jobsAreUnique(jobs)) {
            throw new DuplicateJobException();
        }

        internalList.setAll(jobs);
    }

    public double getMonthlyRevenue(LocalDate date) {
        requireNonNull(date);
        ObservableList<Job> monthlyJob = getMonthlyJob(date);
        double revenue = 0.0;


        if (monthlyJob.size() > 0) {
            for (Job j : monthlyJob) {
                if (j.getFee() != null) {
                    revenue += j.getFee().getDollar();
                }
            }
        }

        return revenue;
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Job> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the most three common {@code Product} received this month.
     */
    public ObservableList<Product> getUnmodifiableTopThreeProductList() {
        return FXCollections.unmodifiableObservableList(getTopThreeProduct());
    }

    @Override
    public Iterator<Job> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.mycrm.model.job.UniqueJobList // instanceof handles nulls
            && internalList.equals(((seedu.mycrm.model.job.UniqueJobList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code jobs} contains only unique jobs.
     */
    private boolean jobsAreUnique(List<Job> jobs) {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (int j = i + 1; j < jobs.size(); j++) {
                if (jobs.get(i).isSameJob(jobs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private ObservableList<Job> getMonthlyJob(LocalDate date) {
        ObservableList<Job> jobs = FXCollections.observableArrayList();

        for (Job j: internalList) {
            if (j.isCompletedThisMonth(date)) {
                jobs.add(j);
            }
        }
        return jobs;
    }

    private Map<Product, Integer> getMonthlyProduct() {
        Map<Product, Integer> monthlyProducts = new HashMap<>();

        for (Job j: internalList) {

            if (j.isReceivedThisMonth(LocalDate.now()) && j.getProduct() != null) {
                Product product = j.getProduct();
                Integer val = monthlyProducts.get(product);

                monthlyProducts.put(product, val == null ? 1 : val + 1);
            }
        }

        return monthlyProducts;
    }

    private ObservableList<Product> getTopThreeProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Map<Product, Integer> productIntegerMap = this.getMonthlyProduct();

        for (int i = 0; i < 3; i++) {
            Map.Entry<Product, Integer> max = null;

            for (Map.Entry<Product, Integer> e : productIntegerMap.entrySet()) {
                if (max == null || e.getValue() > max.getValue()) {
                    max = e;
                }
            }

            if (max != null && max.getKey() != null) {
                products.add(max.getKey());
                productIntegerMap.remove(max.getKey());
            }

        }
        return products;
    }


}
