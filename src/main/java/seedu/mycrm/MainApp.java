package seedu.mycrm;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.mycrm.commons.core.Config;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.commons.core.Version;
import seedu.mycrm.commons.exceptions.DataConversionException;
import seedu.mycrm.commons.util.ConfigUtil;
import seedu.mycrm.commons.util.StringUtil;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.logic.LogicManager;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.ReadOnlyUserPrefs;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.util.SampleDataUtil;
import seedu.mycrm.storage.JsonMyCrmStorage;
import seedu.mycrm.storage.JsonUserPrefsStorage;
import seedu.mycrm.storage.MyCrmStorage;
import seedu.mycrm.storage.Storage;
import seedu.mycrm.storage.StorageManager;
import seedu.mycrm.storage.UserPrefsStorage;
import seedu.mycrm.ui.Ui;
import seedu.mycrm.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing MyCrm ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        MyCrmStorage myCrmStorage = new JsonMyCrmStorage(userPrefs.getMyCrmFilePath());
        storage = new StorageManager(myCrmStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, getHostServices());
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s myCrm and {@code userPrefs}. <br>
     * The data from the sample myCrm will be used instead if {@code storage}'s myCrm is not found,
     * or an empty myCrm will be used instead if errors occur when reading {@code storage}'s myCrm.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyMyCrm> myCrmOptional;
        ReadOnlyMyCrm initialData;
        try {
            myCrmOptional = storage.readMyCrm();
            if (myCrmOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample MyCrm");
            }
            initialData = myCrmOptional.orElseGet(SampleDataUtil::getSampleMyCrm);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty MyCrm");
            initialData = new MyCrm();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MyCrm");
            initialData = new MyCrm();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MyCrm");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting MyCrm " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping myCrm ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
