package seedu.mycrm.ui.report;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.ui.UiPart;


public class Printable extends UiPart<VBox> {

    private static final String FXML = "Printable.fxml";

    @FXML
    private TextArea completedJobList;

    @FXML
    private TextArea inProgressJobList;

    @FXML
    private TextArea topThreeProductList;

    @FXML
    private BarChart<String, Number> barChart;

    public Printable() {
        super(FXML);
    }

    /**
     * Initialize inner parts.
     */
    public void init(Logic logic) {
        int id;

        id = 1;
        completedJobList.setText("");
        for (Job j: logic.getFilteredMonthlyCompletedJobList()) {
            completedJobList.setText(completedJobList.getText() + id + ". "
                    + j.toString() + "\n" + getJobInfo(j) + "\n\n");
            id++;
        }
        completedJobList.setWrapText(true);

        id = 1;
        inProgressJobList.setText("");
        for (Job j: logic.getFilteredJobList()) {
            inProgressJobList.setText(inProgressJobList.getText() + id + ". "
                    + j.toString() + "\n" + getJobInfo(j) + "\n\n");
            id++;
        }
        inProgressJobList.setWrapText(true);

        id = 1;
        topThreeProductList.setText("");
        for (Product p: logic.getFilteredTopThreeProductList()) {
            topThreeProductList.setText(topThreeProductList.getText() + id + ". "
                    + p.toString() + "\n" + getProductInfo(p) + "\n\n");
            id++;
        }
        topThreeProductList.setWrapText(true);

    }

    private String getJobInfo(Job j) {
        String repairFee = "    Repair Fee:" + j.getFee();
        String jobReceivedDate = "    Repair Job Received On: " + j.getReceivedDate().toString();
        String expectedDelivery = "    Expected Delivery" + j.getDeliveryDate().toString();


        String jobInfo = repairFee + "\n"
                + jobReceivedDate + "\n"
                + expectedDelivery;

        return jobInfo;

    }

    private String getProductInfo(Product product) {
        String type = "    Type: " + product.getType().toString();
        String manufacturer = "    Manufacturer: " + product.getManufacturer().toString();
        String description = "    Description: " + product.getDescription().toString();

        String productInfo = type + "\n"
                + manufacturer + "\n"
                + description;

        return productInfo;
    }
}
