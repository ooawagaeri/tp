package seedu.mycrm.ui.report;

import java.time.LocalDate;
import java.time.Month;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.UiPart;

public class GraphDisplay extends UiPart<Region> {

    private static final String FXML = "GraphDisplay.fxml";

    private static final LocalDate now = LocalDate.now();

    @FXML
    private BarChart<String, Number> bc;

    public GraphDisplay() {
        super(FXML);
    }

    /**
     * Initialize inner parts.
     */
    public void init(Logic logic) {

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        addToChart(series1, logic, 1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        addToChart(series2, logic, 0);

        bc.getData().add(series1);
        bc.getData().add(series2);

    }

    /**
     * Makes a copy of this bar chart.
     */
    public BarChart<String, Number> clone(Logic logic) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> bcClone = new BarChart<>(xAxis, yAxis);

        bcClone.setTitle("Monthly Revenue");
        xAxis.setLabel("Month");
        yAxis.setLabel("Revenue($)");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        addToChart(series1, logic, 1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        addToChart(series2, logic, 0);

        bcClone.getData().add(series1);
        bcClone.getData().add(series2);

        bcClone.setStyle(bc.getStyle());

        return bcClone;
    }

    private void addToChart(XYChart.Series<String, Number> series, Logic logic, int year) {
        double[] revenueOfYear = getLastFourMonthRevenue(logic, year);
        Month month = now.getMonth();

        series.setName(Integer.toString(now.getYear() - year));
        for (int i = 0; i < 4; i++) {
            series.getData().add(new XYChart.Data<>(month.minus(3 - i).toString(), revenueOfYear[i]));
        }
    }

    private double[] getLastFourMonthRevenue(Logic logic, int year) {
        double[] lastFourMonthRevenue = new double[4];

        for (int i = 0; i < 4; i++) {
            lastFourMonthRevenue[i] = logic.getRevenue(now.minusYears(year).minusMonths(3 - i));
        }

        return lastFourMonthRevenue;
    }
}
