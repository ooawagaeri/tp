package seedu.mycrm.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;

/**
 * Represents a storage for {@link MyCrm}.
 */
public interface MyCrmStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMyCrmFilePath();

    /**
     * Returns MyCrm data as a {@link ReadOnlyMyCrm}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMyCrm> readMyCrm() throws DataConversionException, IOException;

    /**
     * @see #getMyCrmFilePath()
     */
    Optional<ReadOnlyMyCrm> readMyCrm(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMyCrm} to the storage.
     * @param myCrm cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMyCrm(ReadOnlyMyCrm myCrm) throws IOException;

    /**
     * @see #saveMyCrm(ReadOnlyMyCrm)
     */
    void saveMyCrm(ReadOnlyMyCrm myCrm, Path filePath) throws IOException;

}
