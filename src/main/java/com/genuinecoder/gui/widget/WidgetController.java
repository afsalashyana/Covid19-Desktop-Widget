package com.genuinecoder.gui.widget;

import com.genuinecoder.datafetch.DataProviderService;
import com.genuinecoder.datafetch.model.CountryData;
import com.genuinecoder.datafetch.model.CovidDataModel;
import com.genuinecoder.datafetch.model.GlobalData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;
import org.kordamp.ikonli.fontawesome.FontAwesome;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WidgetController implements Initializable {

    private ScheduledExecutorService executorService;

    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text textGlobalReport, textCountryCode, textCountryReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeScheduler();
    }

    private void initializeScheduler() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::loadData, 0, 20, TimeUnit.SECONDS);
    }

    private void loadData() {
        DataProviderService dataProviderService = new DataProviderService();
        CovidDataModel covidDataModel = dataProviderService.getData("India");

        Platform.runLater(() -> {
            inflateData(covidDataModel);
        });
    }

    private void inflateData(CovidDataModel covidDataModel) {
        GlobalData globalData = covidDataModel.getGlobalData();
        textGlobalReport.setText(getFormattedData(globalData.getCases(), globalData.getRecovered(), globalData.getDeaths()));

        CountryData countryData = covidDataModel.getCountryData();
        textCountryReport.setText(getFormattedData(countryData.getCases(), countryData.getRecovered(), countryData.getDeaths()));

        readjustStage(textCountryCode.getScene().getWindow());
    }

    private String getFormattedData(long totalCases, long recoveredCases, long totalDeaths) {
        return String.format("Cases: %-8d | Recovered: %-6d | Deaths: %-6d", totalCases, recoveredCases, totalDeaths);
    }

    private void readjustStage(Window stage) {
        stage.sizeToScene();

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(visualBounds.getMaxX() - 25 - textCountryCode.getScene().getWidth());
        stage.setY(visualBounds.getMinY() + 25);
    }
}
