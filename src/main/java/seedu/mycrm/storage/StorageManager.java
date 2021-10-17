package seedu.mycrm.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.ReadOnlyUserPrefs;
import seedu.mycrm.model.UserPrefs;

/**
 * Manages storage of MyCrm data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MyCrmStorage myCrmStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MyCrmStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MyCrmStorage myCrmStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.myCrmStorage = myCrmStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ MyCrm methods ==============================

    @Override
    public Path getMyCrmFilePath() {
        return myCrmStorage.getMyCrmFilePath();
    }

    @Override
    public Optional<ReadOnlyMyCrm> readMyCrm() throws DataConversionException, IOException {
        return readMyCrm(myCrmStorage.getMyCrmFilePath());
    }

    @Override
    public Optional<ReadOnlyMyCrm> readMyCrm(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return myCrmStorage.readMyCrm(filePath);
    }

    @Override
    public void saveMyCrm(ReadOnlyMyCrm myCrm) throws IOException {
        saveMyCrm(myCrm, myCrmStorage.getMyCrmFilePath());
    }

    @Override
    public void saveMyCrm(ReadOnlyMyCrm myCrm, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        myCrmStorage.saveMyCrm(myCrm, filePath);
    }

}
