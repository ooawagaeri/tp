package seedu.mycrm.logic.commands.mails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.mycrm.commons.core.Messages.MESSAGE_TEMPLATES_LISTED_OVERVIEW;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;
import static seedu.mycrm.testutil.TypicalTemplates.DONE;
import static seedu.mycrm.testutil.TypicalTemplates.getTypicalMyCrm;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.mail.SubjectContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindTemplateCommandTest {
    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void equals() {
        SubjectContainsKeywordsPredicate firstPredicate =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("first"));
        SubjectContainsKeywordsPredicate secondPredicate =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTemplateCommand findFirstCommand = new FindTemplateCommand(firstPredicate);
        FindTemplateCommand findSecondCommand = new FindTemplateCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindTemplateCommand findFirstCommandCopy = new FindTemplateCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different contact -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noTemplateFound() {
        String expectedMessage = String.format(MESSAGE_TEMPLATES_LISTED_OVERVIEW, 0);
        SubjectContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTemplateCommand command = new FindTemplateCommand(predicate);
        expectedModel.updateFilteredTemplateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTemplateList());
    }

    @Test
    public void execute_multipleKeywords_multipleTemplatesFound() {
        String expectedMessage = String.format(MESSAGE_TEMPLATES_LISTED_OVERVIEW, 2);
        SubjectContainsKeywordsPredicate predicate = preparePredicate("Completed Done");
        FindTemplateCommand command = new FindTemplateCommand(predicate);
        expectedModel.updateFilteredTemplateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(COMPLETED, DONE), model.getFilteredTemplateList());
    }

    /**
     * Parses {@code userInput} into a {@code SubjectContainsKeywordsPredicate}.
     */
    private SubjectContainsKeywordsPredicate preparePredicate(String userInput) {
        return new SubjectContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

