package seedu.mycrm.logic.commands.mails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.DESC_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.DESC_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_ISSUE;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.CommandTestUtil.showTemplateAtIndex;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;
import static seedu.mycrm.testutil.TypicalTemplates.getTypicalMyCrm;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.ClearCommand;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand.EditTemplateDescriptor;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.testutil.EditTemplateDescriptorBuilder;
import seedu.mycrm.testutil.TemplateBuilder;


class EditTemplateCommandTest {
    private final Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Template editedTemplate = new TemplateBuilder().build();
        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder(editedTemplate).build();
        EditTemplateCommand editCommand = new EditTemplateCommand(INDEX_FIRST_TEMPLATE, descriptor);

        String expectedMessage = String.format(EditTemplateCommand.MESSAGE_EDIT_TEMPLATE_SUCCESS, editedTemplate);

        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setTemplate(model.getFilteredTemplateList().get(0), editedTemplate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTemplate = Index.fromOneBased(model.getFilteredTemplateList().size());
        Template lastTemplate = model.getFilteredTemplateList().get(indexLastTemplate.getZeroBased());

        TemplateBuilder templateInList = new TemplateBuilder(lastTemplate);
        Template editedTemplate = templateInList.withSubject(VALID_SUBJECT_DONE).withBody(VALID_BODY_DONE).build();

        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder()
                .withSubject(VALID_SUBJECT_DONE).withBody(VALID_BODY_DONE).build();
        EditTemplateCommand editCommand = new EditTemplateCommand(indexLastTemplate, descriptor);

        String expectedMessage = String.format(EditTemplateCommand.MESSAGE_EDIT_TEMPLATE_SUCCESS, editedTemplate);

        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setTemplate(lastTemplate, editedTemplate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTemplateCommand editCommand = new EditTemplateCommand(INDEX_FIRST_TEMPLATE,
                new EditTemplateDescriptor());
        Template editedTemplate = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());

        String expectedMessage = String.format(EditTemplateCommand.MESSAGE_EDIT_TEMPLATE_SUCCESS, editedTemplate);

        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);

        Template templateInFilteredList = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        Template editedTemplate = new TemplateBuilder(templateInFilteredList).withSubject(VALID_SUBJECT_ISSUE).build();
        EditTemplateCommand editCommand = new EditTemplateCommand(INDEX_FIRST_TEMPLATE,
                new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_ISSUE).build());

        String expectedMessage = String.format(EditTemplateCommand.MESSAGE_EDIT_TEMPLATE_SUCCESS, editedTemplate);

        Model expectedModel = new ModelManager(new MyCrm(model.getMyCrm()), new UserPrefs());
        expectedModel.setTemplate(model.getFilteredTemplateList().get(0), editedTemplate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTemplateUnfilteredList_failure() {
        Template firstTemplate = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder(firstTemplate).build();
        EditTemplateCommand editCommand = new EditTemplateCommand(INDEX_SECOND_TEMPLATE, descriptor);

        assertCommandFailure(editCommand, model, EditTemplateCommand.MESSAGE_DUPLICATE_TEMPLATE);
    }

    @Test
    public void execute_duplicateTemplateFilteredList_failure() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);

        // edit template in filtered list into a duplicate in myCrm
        Template templateInList = model.getMyCrm().getTemplateList().get(INDEX_SECOND_TEMPLATE.getZeroBased());
        EditTemplateCommand editCommand = new EditTemplateCommand(INDEX_FIRST_TEMPLATE,
                new EditTemplateDescriptorBuilder(templateInList).build());

        assertCommandFailure(editCommand, model, EditTemplateCommand.MESSAGE_DUPLICATE_TEMPLATE);
    }

    @Test
    public void execute_invalidTemplateIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTemplateList().size() + 1);
        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder()
                .withSubject(VALID_SUBJECT_COMPLETE).build();
        EditTemplateCommand editCommand = new EditTemplateCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of myCrm
     */
    @Test
    public void execute_invalidTemplateIndexFilteredList_failure() {
        showTemplateAtIndex(model, INDEX_FIRST_TEMPLATE);
        Index outOfBoundIndex = INDEX_SECOND_TEMPLATE;
        // ensures that outOfBoundIndex is still in bounds of myCrm list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMyCrm().getTemplateList().size());

        EditTemplateCommand editCommand = new EditTemplateCommand(outOfBoundIndex,
                new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_DONE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTemplateCommand standardCommand = new EditTemplateCommand(INDEX_FIRST_TEMPLATE, DESC_DONE);

        // same values -> returns true
        EditTemplateDescriptor copyDescriptor = new EditTemplateDescriptor(DESC_DONE);
        EditTemplateCommand commandWithSameValues = new EditTemplateCommand(INDEX_FIRST_TEMPLATE, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditTemplateCommand(INDEX_SECOND_TEMPLATE, DESC_DONE));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditTemplateCommand(INDEX_FIRST_TEMPLATE, DESC_COMPLETE));
    }
}
