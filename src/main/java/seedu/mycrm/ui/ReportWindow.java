package seedu.mycrm.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.report.GraphDisplay;
import seedu.mycrm.ui.report.JobDisplay;
import seedu.mycrm.ui.report.NodePrinter;

public class ReportWindow extends UiPart<Stage> {

    private static final String FXML = "ReportWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(ReportWindow.class);
    private final ThemeManager themeManager;

    private Stage primaryStage;
    private Logic logic;
    private PrinterJob job;

    private JobDisplay jobDisplay;
    private GraphDisplay graphDisplay;
    private NodePrinter nodePrinter;

    private String latestFlag;

    @FXML
    private StackPane jobDisplayPlaceholder;

    @FXML
    private StackPane graphDisplayPlaceholder;

    /**
     * Creates a {@code ReportWindow} with the given {@code Stage} and {@code Logic}.
     */
    public ReportWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.themeManager = new ThemeManager(primaryStage.getScene().getStylesheets());

        themeManager.initTheme(logic.getGuiSettings());
    }

    /**
     * Creates a new ReportWindow.
     */
    public ReportWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        jobDisplay =
                new JobDisplay();
        jobDisplay.init(logic);
        if (jobDisplayPlaceholder.getChildren().size() > 0) {
            jobDisplayPlaceholder.getChildren().remove(0);
        }
        jobDisplayPlaceholder.getChildren().add(jobDisplay.getRoot());

        graphDisplay = new GraphDisplay();
        graphDisplay.init(logic);
        if (graphDisplayPlaceholder.getChildren().size() > 0) {
            graphDisplayPlaceholder.getChildren().remove(0);
        }
        graphDisplayPlaceholder.getChildren().add(graphDisplay.getRoot());

        this.nodePrinter = new NodePrinter(logic);
    }

    void updateInnerParts() {
        fillInnerParts();
        if (latestFlag != null) {
            switchTab(latestFlag);
        }
    }

    /**
     * Shows the report window.
     */
    public void show() {
        logger.fine("Showing report page about the monthly job records.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the report window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the report window.
     */
    @FXML
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the report window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Align the Ui theme of report window with main window.
     */
    public void changeTheme(String themeName) {
        themeManager.changeTheme(themeName);
    }

    /**
     * Focuses on the corresponding tab of command flag.
     */
    public void switchTab(String flag) {
        jobDisplay.switchTab(flag);
        latestFlag = flag;
    }

    /**
     * Handle the printing request from user.
     */
    @FXML
    public void handlePrint() {
        job = PrinterJob.createPrinterJob();

        if (job.showPageSetupDialog(getRoot().getOwner()) && job.showPrintDialog(getRoot().getOwner())) {
            boolean success = nodePrinter.print(job);
            if (success) {
                job.endJob();
            }
        }
    }

}
