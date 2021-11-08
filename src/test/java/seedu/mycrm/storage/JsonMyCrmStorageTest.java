package seedu.mycrm.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalContacts.HOON;
import static seedu.mycrm.testutil.TypicalContacts.IDA;
import static seedu.mycrm.testutil.TypicalContacts.getTypicalMyCrm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;

public class JsonMyCrmStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMyCrmStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMyCrm_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMyCrm(null));
    }

    private java.util.Optional<ReadOnlyMyCrm> readMyCrm(String filePath) throws Exception {
        return new JsonMyCrmStorage(Paths.get(filePath)).readMyCrm(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMyCrm("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMyCrm("notJsonFormatMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidContactMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidContactMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidProductMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidProductMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidTemplateMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidTemplateMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidJobMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidJobMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidAndValidContactMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidAndValidContactMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidAndValidProductMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidAndValidProductMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidAndValidTemplateMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidAndValidTemplateMyCrm.json"));
    }

    @Test
    public void readMyCrm_invalidAndValidJobMyCrm_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMyCrm("invalidAndValidJobMyCrm.json"));
    }

    @Test
    public void readAndSaveMyCrm_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMyCrm.json");
        MyCrm original = getTypicalMyCrm();
        JsonMyCrmStorage jsonMyCrmStorage = new JsonMyCrmStorage(filePath);

        // Save in new file and read back
        jsonMyCrmStorage.saveMyCrm(original, filePath);
        ReadOnlyMyCrm readBack = jsonMyCrmStorage.readMyCrm(filePath).get();
        assertEquals(original, new MyCrm(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonMyCrmStorage.saveMyCrm(original, filePath);
        readBack = jsonMyCrmStorage.readMyCrm(filePath).get();
        assertEquals(original, new MyCrm(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonMyCrmStorage.saveMyCrm(original); // file path not specified
        readBack = jsonMyCrmStorage.readMyCrm().get(); // file path not specified
        assertEquals(original, new MyCrm(readBack));

    }

    @Test
    public void saveMyCrm_nullMyCrm_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMyCrm(null, "SomeFile.json"));
    }

    /**
     * Saves {@code myCrm} at the specified {@code filePath}.
     */
    private void saveMyCrm(ReadOnlyMyCrm myCrm, String filePath) {
        try {
            new JsonMyCrmStorage(Paths.get(filePath))
                    .saveMyCrm(myCrm, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMyCrm_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMyCrm(new MyCrm(), null));
    }
}
