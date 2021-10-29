package seedu.mycrm.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.report.GraphDisplay;
import seedu.mycrm.ui.report.JobDisplay;
import seedu.mycrm.ui.report.Printable;

public class ReportWindow extends UiPart<Stage> {

    private static final String FXML = "ReportWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(ReportWindow.class);

    private Stage primaryStage;
    private Logic logic;

    private JobDisplay jobDisplay;
    private GraphDisplay graphDisplay;
    private Printable printable;

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
        jobDisplay = new JobDisplay();
        jobDisplay.init(logic);
        jobDisplayPlaceholder.getChildren().add(jobDisplay.getRoot());

        graphDisplay = new GraphDisplay();
        graphDisplay.init(logic);
        graphDisplayPlaceholder.getChildren().add(graphDisplay.getRoot());

        printable = new Printable();
        printable.init(logic);
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
     * Handle the printing request from user.
     */
    @FXML
    public void handlePrint() {
        print(getPrintable());
    }

    private VBox getPrintable() {
        VBox toPrint = new VBox();
        BarChart<String, Number> barChart = graphDisplay.clone(logic);

        barChart.setMinSize(372, 350);

        toPrint.getChildren().addAll(barChart, printable.getRoot());

        return toPrint;
    }

    private void print(VBox region) {
        requireNonNull(region);

        Printer printer = Printer.getDefaultPrinter();
        requireNonNull(printer);

        PageLayout pagelayout = printer.createPageLayout(Paper.A4,
                PageOrientation.PORTRAIT,
                Printer.MarginType.HARDWARE_MINIMUM);

        region.setPrefSize(pagelayout.getPrintableWidth() - 110, pagelayout.getPrintableHeight());

        PrinterJob job = PrinterJob.createPrinterJob();
        requireNonNull(job);

        if (job != null) {
            job.showPageSetupDialog(getRoot().getOwner());
            job.showPrintDialog(getRoot().getOwner());
            boolean success = job.printPage(region);
            if (success) {
                job.endJob();
            }
        }

    }

}
