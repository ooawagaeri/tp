package seedu.mycrm.model.job;

import java.util.List;
import java.util.function.Predicate;

import seedu.mycrm.commons.util.StringUtil;

public class JobContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        if (keywords.stream().anyMatch(keyword -> StringUtil
                .containsWordIgnoreCase(job.getJobDescription().toString(), keyword))) {
            return true;
        }

        if (job.getClient() != null && keywords.stream().anyMatch(keyword -> StringUtil
                .containsWordIgnoreCase(job.getClient().getName().fullName, keyword))) {
            return true;
        }

        if (job.getProduct() != null && keywords.stream().anyMatch(keywords -> StringUtil
                .containsWordIgnoreCase(job.getProduct().getName().toString(), keywords))) {
            return true;
        }

        if (job.getJobStatus() != null && keywords.stream().anyMatch(keywords -> StringUtil
                .containsWordIgnoreCase(job.getJobStatus().toString(), keywords))) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobContainsKeywordsPredicate) other).keywords)); // state check
    }
}
