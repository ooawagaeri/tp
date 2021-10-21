package seedu.mycrm.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.commons.util.FileUtil;
import seedu.mycrm.commons.util.JsonUtil;
import seedu.mycrm.model.ReadOnlyMyCrm;

/**
 * A class to access MyCrm data stored as a json file on the hard disk.
 */
public class JsonMyCrmStorage implements MyCrmStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMyCrmStorage.class);

    private Path filePath;

    public JsonMyCrmStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMyCrmFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMyCrm> readMyCrm() throws DataConversionException {
        return readMyCrm(filePath);
    }

    /**
     * Similar to {@link #readMyCrm()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMyCrm> readMyCrm(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMyCrm> jsonMyCrm = JsonUtil.readJsonFile(
                filePath, JsonSerializableMyCrm.class);

        if (jsonMyCrm.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMyCrm.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMyCrm(ReadOnlyMyCrm myCrm) throws IOException {
        saveMyCrm(myCrm, filePath);
    }

    /**
     * Similar to {@link #saveMyCrm(ReadOnlyMyCrm)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMyCrm(ReadOnlyMyCrm myCrm, Path filePath) throws IOException {
        requireNonNull(myCrm);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMyCrm(myCrm), filePath);
    }

}
