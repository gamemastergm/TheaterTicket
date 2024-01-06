package ucsal.edu.web.service;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChartApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BarChart<String, Number> barChart = createBarChart();
        Scene scene = new Scene(barChart, 800, 600);

        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //Map<String, Integer> data = getDataFromDatabase();
        Map<String, Integer> data = getTypeFromDatabase();

        data.forEach((category, count) -> {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(category, count);
            series.getData().add(dataPoint);
            dataPoint.nodeProperty().addListener((node, oldNode, newNode) -> {
                if (newNode != null) {
                    displayLabelForDataPoint(newNode, count);
                }
            });
        });

        barChart.getData().add(series);

        return barChart;
    }

    private void displayLabelForDataPoint(Node node, int count) {
        Label label = new Label(String.valueOf(count));
        StackPane stackPane = (StackPane) node;
        stackPane.getChildren().add(label);
        StackPane.setAlignment(label, Pos.TOP_CENTER);
    }

   /* private Map<String, Integer> getDataFromDatabase() {
        Map<String, Integer> data = new HashMap<>();
        double[] limitIntervals = {0, 1000, 1000, 2000, 2000, 3000, 3000, 4000, 4000, 5000, 5000, 6000, 6000, 7000, 8000, 9000, 9000, 10000, 10000, 11000};

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
                "postgres", "postgres")) {
            for (int i = 0; i < limitIntervals.length - 1; i += 2) {
                double minLimit = limitIntervals[i];
                double maxLimit = limitIntervals[i + 1];

                String sql = "SELECT COUNT(*) FROM cartaocredito WHERE limite BETWEEN ? AND ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setDouble(1, minLimit);
                    pstmt.setDouble(2, maxLimit);

                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            int count = resultSet.getInt(1);
                            String category = String.format("%.0f-%.0f", minLimit, maxLimit);
                            data.put(category, count);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }*/

    private Map<String, Integer> getTypeFromDatabase() {
        Map<String, Integer> data = new HashMap<>();
        String[] tipos = {"Base", "LM", "M", "UP"};

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
                "postgres", "postgres")) {

            for (String tipo : tipos) {
                String sql = "SELECT COUNT(*) FROM titular WHERE classificacao = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, tipo);

                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            int count = resultSet.getInt(1);
                            data.put(tipo, count);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public void launchApp(String[] args) {
        launch(args);
    }
}
