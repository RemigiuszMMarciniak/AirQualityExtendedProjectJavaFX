//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.sun.tools.javadoc.Main;
//import javafx.concurrent.Task;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.ByteBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Map;
//
//public class ClockTask extends Task<Integer> {
//    private int seconds;
//    private int time;
//
//    public int getTime() {
//        return time;
//    }
//
//    public ClockTask(int seconds) {
//        this.seconds = seconds;
//    }
//
//    MainController mainController = new MainController();
//    @Override
//    protected Integer call() throws Exception {
//        for (int i = 0; i < seconds; i++) {
//            time = i;
//            System.out.println(i);
//            updateProgress(i,seconds);
//            Thread.sleep(1000);
//            if(isCancelled()){
//                return i;
//            }
//            if((time)%60 == 0)
//            {
//
//                //SERVER & BARCHAR
//                System.out.println("update time");
//
//
//                String cityName = mainController.getMainTextFieldObservedCity().getText();
//                System.out.println(cityName);
//                String cityNamePrepared = null;
//                ByteBuffer byteBuffer = null;
//                if(!cityName.isEmpty()){
//                    cityNamePrepared = cityName.substring(0,1).toUpperCase() + cityName.substring(1,cityName.length()).toLowerCase();
//                    System.out.println(cityNamePrepared);
//                }
//
//                StringBuffer response = new StringBuffer();
//                String url = "https://api.openaq.org/v1/measurements?city="+cityNamePrepared+"&parameter[]=pm25&parameter[]=pm10" +
//                        "&parameter[]=so2&parameter[]=no2&parameter[]=o3&parameter[]=co";
//                System.out.println(url);
//                String urlFirstPart = "https://api.openaq.org/v1/measurements?city=";
//                String urlSecondPart = "&parameter[]=pm25&parameter[]=pm10" +
//                        "&parameter[]=so2&parameter[]=no2&parameter[]=o3&parameter[]=co";
//                String completeURL = null;
//                try {
//                    cityNamePrepared = URLEncoder.encode(cityNamePrepared, StandardCharsets.UTF_8.toString());
//                    completeURL = urlFirstPart+cityNamePrepared+urlSecondPart;
//                    System.out.println(cityNamePrepared);
//                    System.out.println(completeURL);
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                try{
//                    URL object = new URL(completeURL);
//                    HttpURLConnection connection = (HttpURLConnection) object.openConnection();
//                    connection.setRequestMethod("GET");
//                    int responseCode = connection.getResponseCode();
//                    System.out.println("Response code from the server: " + responseCode);
//                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String inputLine;
//
//                    while((inputLine = in.readLine()) != null){
//                        response.append(inputLine);
//                    }
//                    in.close();
//
//                }catch (MalformedURLException e) {
//                    e.printStackTrace();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                String responseFromJson = new String(response);
//
//                // System.out.println(responseFromJson);
//
//                ResultsOutput results = gson.fromJson(responseFromJson,ResultsOutput.class);
//
//                //System.out.println(results);
//
//
//                Map m = gson.fromJson(responseFromJson,Map.class);
//                System.out.println(m.get("results"));
//
//
//
//
//
//                //DATE
//                String date;
//                if(results.getResults().isEmpty()){
//
//                    mainController.getMainPaneView1().getChildren().clear();
//
//                }
//                else {
//                    date = results.getResults().get(0).getDate().getUtc();
//                    String datePrepared = date.substring(0, 10) + " " + date.substring(11, 19);
//
//                    ArrayList<String> valuesToBeShown = mainController.parametersToBeShown(results);
//
//                    //barchart
//
//
//                    mainController.getMainPaneView1().getChildren().clear();
//                    CategoryAxis xAxis = new CategoryAxis();
//                    xAxis.setLabel("Parameters");
//                    NumberAxis yAxis = new NumberAxis();
//                    yAxis.setLabel("Value");
//
////                yAxis.setStyle("-fx-text-fill: white;");
////                xAxis.setStyle("-fx-text-fill: white;");
//                    BarChart barChart = new BarChart(xAxis, yAxis);
//                    barChart.setTitle("Found parameters compared with very good values");
//
//                    //very good values
//
//                    XYChart.Series series = new XYChart.Series();
//
//                    series.getData().add(new XYChart.Data<>("pm25", 13.));
//                    series.getData().add(new XYChart.Data<>("pm10", 21.));
//                    series.getData().add(new XYChart.Data<>("so2", 51.));
//                    series.getData().add(new XYChart.Data<>("no2", 41.));
//                    series.getData().add(new XYChart.Data<>("o3", 71.));
//                    series.getData().add(new XYChart.Data<>("co", 3.));
//
//                    //found values
//
//
//                    XYChart.Series seriesFound = new XYChart.Series();
//
//                    if (Double.parseDouble(valuesToBeShown.get(0)) < 0 || valuesToBeShown.get(0) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("pm25", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("pm25", Double.parseDouble(valuesToBeShown.get(0))));
//
//                    }
//
//                    if (Double.parseDouble(valuesToBeShown.get(1)) < 0 || valuesToBeShown.get(1) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("pm10", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("pm10", Double.parseDouble(valuesToBeShown.get(1))));
//
//                    }
//
//                    if (Double.parseDouble(valuesToBeShown.get(2)) < 0 || valuesToBeShown.get(2) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("so2", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("so2", Double.parseDouble(valuesToBeShown.get(2))));
//
//                    }
//
//                    if (Double.parseDouble(valuesToBeShown.get(3)) < 0 || valuesToBeShown.get(3) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("no2", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("no2", Double.parseDouble(valuesToBeShown.get(3))));
//                    }
//
//                    if (Double.parseDouble(valuesToBeShown.get(4)) < 0 || valuesToBeShown.get(4) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("o3", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("o3", Double.parseDouble(valuesToBeShown.get(4))));
//
//                    }
//
//                    if (Double.parseDouble(valuesToBeShown.get(5)) < 0 || valuesToBeShown.get(5) == "NaN") {
//                        seriesFound.getData().add(new XYChart.Data<>("co", -1));
//                    } else {
//                        seriesFound.getData().add(new XYChart.Data<>("co", Double.parseDouble(valuesToBeShown.get(5))));
//
//                    }
//
//
//                    series.setName("Very good values");
//                    seriesFound.setName("Found values");
//
//
//                    System.out.println(valuesToBeShown.get(0));
//                    System.out.println(valuesToBeShown.get(5));
//
//                    System.out.println(seriesFound.toString());
//                    System.out.println(seriesFound.getData().toString());
//                    barChart.getData().addAll(series, seriesFound);
//
//                    mainController.getMainPaneView1().getChildren().add(barChart);
//                }
//
//
//            }
//        }
//
//
//        return seconds;
//    }
//
//    @Override
//    public boolean cancel(boolean mayInterruptIfRunning) {
//        updateMessage("cancelled");
//        return super.cancel(mayInterruptIfRunning);
//    }
//
//    @Override
//    protected void updateProgress(double workDone, double max) {
//        updateMessage("progress!" + workDone);
//        super.updateProgress(workDone, max);
//    }
//
//
//}
