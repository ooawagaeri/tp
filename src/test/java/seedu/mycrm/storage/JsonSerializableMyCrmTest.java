package seedu.mycrm.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.commons.util.JsonUtil;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.testutil.TypicalContacts;

public class JsonSerializableMyCrmTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMyCrmTest");
    private static final Path TYPICAL_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("typicalContactsMyCrm.json");
    private static final Path INVALID_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("invalidContactMyCrm.json");
    private static final Path INVALID_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("invalidProductMyCrm.json");
    private static final Path INVALID_TEMPLATES_FILE = TEST_DATA_FOLDER.resolve("invalidTemplateMyCrm.json");
    private static final Path INVALID_JOBS_FILE = TEST_DATA_FOLDER.resolve("invalidJobMyCrm.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve("duplicateContactMyCrm.json");
    private static final Path DUPLICATE_TEMPLATE_FILE = TEST_DATA_FOLDER.resolve("duplicateTemplateMyCrm.json");
    private static final Path DUPLICATE_PRODUCT_FILE = TEST_DATA_FOLDER.resolve("duplicateProductMyCrm.json");
    private static final Path DUPLICATE_JOB_FILE = TEST_DATA_FOLDER.resolve("duplicateJobMyCrm.json");

    @Test
    public void toModelType_typicalContactsFile_success() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTACTS_FILE,
                JsonSerializableMyCrm.class).get();
        MyCrm myCrmFromFile = dataFromFile.toModelType();
        MyCrm typicalContactsMyCrm = TypicalContacts.getTypicalMyCrm();
        assertEquals(myCrmFromFile, typicalContactsMyCrm);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACTS_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidProductFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(INVALID_PRODUCTS_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidTemplateFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(INVALID_TEMPLATES_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidJobFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(INVALID_JOBS_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyCrm.MESSAGE_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTemplates_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TEMPLATE_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyCrm.MESSAGE_DUPLICATE_TEMPLATE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProducts_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRODUCT_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyCrm.MESSAGE_DUPLICATE_PRODUCT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateJobs_throwsIllegalValueException() throws Exception {
        JsonSerializableMyCrm dataFromFile = JsonUtil.readJsonFile(DUPLICATE_JOB_FILE,
                JsonSerializableMyCrm.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMyCrm.MESSAGE_DUPLICATE_JOB,
                dataFromFile::toModelType);
    }
}
