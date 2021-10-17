package seedu.mycrm.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.ReadOnlyUserPrefs;
import seedu.mycrm.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MyCrmStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMyCrmFilePath();

    @Override
    Optional<ReadOnlyMyCrm> readMyCrm() throws DataConversionException, IOException;

    @Override
    void saveMyCrm(ReadOnlyMyCrm myCrm) throws IOException;

}
