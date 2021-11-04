package seedu.mycrm.model.mail;

import java.util.List;
import java.util.function.Predicate;

import seedu.mycrm.commons.util.StringUtil;

/**
 * Tests that a {@code Template}'s {@code Subject} matches any of the keywords given.
 */
public class SubjectContainsKeywordsPredicate implements Predicate<Template> {
    private final List<String> keywords;

    public SubjectContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Template template) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(template.getSubject().subject, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SubjectContainsKeywordsPredicate
                && keywords.equals(((SubjectContainsKeywordsPredicate) other).keywords));
    }

}
