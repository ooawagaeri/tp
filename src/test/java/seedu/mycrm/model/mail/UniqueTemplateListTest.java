package seedu.mycrm.model.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;
import static seedu.mycrm.testutil.TypicalTemplates.THANK_YOU;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.mail.exceptions.DuplicateTemplateException;
import seedu.mycrm.model.mail.exceptions.TemplateNotFoundException;
import seedu.mycrm.testutil.TemplateBuilder;

public class UniqueTemplateListTest {
    private final UniqueTemplateList uniqueTemplateList = new UniqueTemplateList();

    @Test
    public void contains_nullTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.contains(null));
    }

    @Test
    public void contains_templateNotInList_returnsFalse() {
        assertFalse(uniqueTemplateList.contains(THANK_YOU));
    }

    @Test
    public void contains_templateInList_returnsTrue() {
        uniqueTemplateList.add(THANK_YOU);
        assertTrue(uniqueTemplateList.contains(THANK_YOU));
    }

    @Test
    public void contains_templateWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTemplateList.add(THANK_YOU);
        Template editedAlice = new TemplateBuilder(THANK_YOU).withBody(VALID_BODY_DONE).build();
        assertTrue(uniqueTemplateList.contains(editedAlice));
    }

    @Test
    public void add_nullTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.add(null));
    }

    @Test
    public void add_duplicateTemplate_throwsDuplicateTemplateException() {
        uniqueTemplateList.add(THANK_YOU);
        assertThrows(DuplicateTemplateException.class, () -> uniqueTemplateList.add(THANK_YOU));
    }

    @Test
    public void setTemplate_nullTargetTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.setTemplate(null, THANK_YOU));
    }

    @Test
    public void setTemplate_nullEditedTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.setTemplate(THANK_YOU, null));
    }

    @Test
    public void setTemplate_targetTemplateNotInList_throwsTemplateNotFoundException() {
        assertThrows(TemplateNotFoundException.class, () -> uniqueTemplateList.setTemplate(THANK_YOU, THANK_YOU));
    }

    @Test
    public void setTemplate_editedTemplateIsSameTemplate_success() {
        uniqueTemplateList.add(THANK_YOU);
        uniqueTemplateList.setTemplate(THANK_YOU, THANK_YOU);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        expectedUniqueTemplateList.add(THANK_YOU);
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplate_editedTemplateHasSameIdentity_success() {
        uniqueTemplateList.add(THANK_YOU);
        Template editedAlice = new TemplateBuilder(THANK_YOU).withBody(VALID_BODY_DONE).build();
        uniqueTemplateList.setTemplate(THANK_YOU, editedAlice);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        expectedUniqueTemplateList.add(editedAlice);
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplate_editedTemplateHasDifferentIdentity_success() {
        uniqueTemplateList.add(THANK_YOU);
        uniqueTemplateList.setTemplate(THANK_YOU, COMPLETED);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        expectedUniqueTemplateList.add(COMPLETED);
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplate_editedTemplateHasNonUniqueIdentity_throwsDuplicateTemplateException() {
        uniqueTemplateList.add(THANK_YOU);
        uniqueTemplateList.add(COMPLETED);
        assertThrows(DuplicateTemplateException.class, () -> uniqueTemplateList.setTemplate(THANK_YOU, COMPLETED));
    }

    @Test
    public void remove_nullTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.remove(null));
    }

    @Test
    public void remove_templateDoesNotExist_throwsTemplateNotFoundException() {
        assertThrows(TemplateNotFoundException.class, () -> uniqueTemplateList.remove(THANK_YOU));
    }

    @Test
    public void remove_existingTemplate_removesTemplate() {
        uniqueTemplateList.add(THANK_YOU);
        uniqueTemplateList.remove(THANK_YOU);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplates_nullUniqueTemplateList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.setTemplates((UniqueTemplateList) null));
    }

    @Test
    public void setTemplates_uniqueTemplateList_replacesOwnListWithProvidedUniqueTemplateList() {
        uniqueTemplateList.add(THANK_YOU);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        expectedUniqueTemplateList.add(COMPLETED);
        uniqueTemplateList.setTemplates(expectedUniqueTemplateList);
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplates_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTemplateList.setTemplates((List<Template>) null));
    }

    @Test
    public void setTemplates_list_replacesOwnListWithProvidedList() {
        uniqueTemplateList.add(THANK_YOU);
        List<Template> templateList = Collections.singletonList(COMPLETED);
        uniqueTemplateList.setTemplates(templateList);
        UniqueTemplateList expectedUniqueTemplateList = new UniqueTemplateList();
        expectedUniqueTemplateList.add(COMPLETED);
        assertEquals(expectedUniqueTemplateList, uniqueTemplateList);
    }

    @Test
    public void setTemplates_listWithDuplicateTemplates_throwsDuplicateTemplateException() {
        List<Template> listWithDuplicateTemplates = Arrays.asList(THANK_YOU, THANK_YOU);
        assertThrows(DuplicateTemplateException.class, ()
            -> uniqueTemplateList.setTemplates(listWithDuplicateTemplates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTemplateList.asUnmodifiableObservableList().remove(0));
    }
}
