package seedu.mycrm.ui.report;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.commands.PrintReportCommand.SHOW_COMPLETED_FLAG;
import static seedu.mycrm.logic.commands.PrintReportCommand.SHOW_IN_PROGRESS_FLAG;
import static seedu.mycrm.logic.commands.PrintReportCommand.SHOW_PRODUCT_FLAG;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.UiPart;
import seedu.mycrm.ui.job.JobListPanel;
import seedu.mycrm.ui.product.ProductListPanel;

/**
 * After creating an instance of JobDisplay, should instantly call method fillInnerParts(Logic).
 */
public class JobDisplay extends UiPart<Region> {

    private static final String FXML = "JobDisplay.fxml";

    private static final String COMPLETED_JOB_NUMBER_MESSAGE = "The total number of jobs completed in this month: ";
    private static final String IN_PROGRESS_JOB_NUMBER_MESSAGE = "The total number of in-progress jobs: ";

    private JobListPanel completedJobListPanel;
    private JobListPanel inProgressJobListPanel;
    private ProductListPanel topThreeProductListPanel;


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab completedJobTab;

    @FXML
    private Tab inProgressJobTab;

    @FXML
    private Tab topThreeProductTab;

    @FXML
    private StackPane completedJobListPanelPlaceholder;

    @FXML
    private StackPane inProgressJobListPanelPlaceholder;

    @FXML
    private StackPane topThreeProductListPanelPlaceholder;

    @FXML
    private NumberCard numberOfCompletedJobDisplay;

    @FXML
    private NumberCard numberOfInProgressJobDisplay;

    public JobDisplay() {
        super(FXML);
    }

    /**
     * Initialize inner parts.
     */
    public void init(Logic logic) {
        int numberOfCompletedJob = logic.getFilteredMonthlyCompletedJobList().size();
        int numberOfInProgressJob = logic.getFilteredIncompleteJobList().size();

        completedJobListPanel = new JobListPanel(logic.getFilteredMonthlyCompletedJobList());
        numberOfCompletedJobDisplay = new NumberCard(COMPLETED_JOB_NUMBER_MESSAGE, numberOfCompletedJob);
        VBox vBox1 = new VBox(numberOfCompletedJobDisplay.getRoot(), completedJobListPanel.getRoot());
        vBox1.setVgrow(completedJobListPanel.getRoot(), Priority.ALWAYS);
        completedJobListPanelPlaceholder.managedProperty().bind(completedJobListPanelPlaceholder.visibleProperty());
        completedJobListPanelPlaceholder.getChildren().add(vBox1);

        inProgressJobListPanel = new JobListPanel(logic.getFilteredIncompleteJobList());
        numberOfInProgressJobDisplay = new NumberCard(IN_PROGRESS_JOB_NUMBER_MESSAGE, numberOfInProgressJob);
        VBox vBox2 = new VBox(numberOfInProgressJobDisplay.getRoot(), inProgressJobListPanel.getRoot());
        vBox2.setVgrow(inProgressJobListPanel.getRoot(), Priority.ALWAYS);
        inProgressJobListPanelPlaceholder.managedProperty().bind(inProgressJobListPanelPlaceholder.visibleProperty());
        inProgressJobListPanelPlaceholder.getChildren().add(vBox2);

        topThreeProductListPanel = new ProductListPanel(logic.getFilteredTopThreeProductList());
        topThreeProductListPanelPlaceholder
                .managedProperty()
                .bind(topThreeProductListPanelPlaceholder.visibleProperty());
        topThreeProductListPanelPlaceholder.getChildren().add(topThreeProductListPanel.getRoot());

        topThreeProductTab.setText("Top-Three Products Received in "
                + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    /**
     * Switch tab based on command flag.
     */
    public void switchTab(String commandFlag) {
        switch (commandFlag) {
        case SHOW_COMPLETED_FLAG:
            switchTab(completedJobTab);
            break;

        case SHOW_IN_PROGRESS_FLAG:
            switchTab(inProgressJobTab);
            break;

        case SHOW_PRODUCT_FLAG:
            switchTab(topThreeProductTab);
            break;

        default:
            assert false;
        }
    }

    private void switchTab(Tab tab) {
        requireNonNull(tab);

        tabPane.getSelectionModel().select(tab);
    }
}
