import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MainController {
    String foundResultsToJson = null;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField mainTextFieldCityName;

    @FXML
    private Button mainButtonFind;

    @FXML
    private TextField mainTextFieldName;

    @FXML
    private Button mainButtonSave;

    @FXML
    private Button mainButtonLoad;

    @FXML
    private Label mainLabelDate;

    @FXML
    private Label mainLabelMax;

    @FXML
    private Label mainLabelMin;

    @FXML
    private Label mainLabelSTD;

    @FXML
    private TableView<AVGParameters> mainTableView;

    @FXML
    private Pane mainPaneView;

    @FXML
    private TextArea mainTextArea;

    @FXML
    void find(ActionEvent event) {
        String cityName = mainTextFieldCityName.getText();
        String cityNamePrepared = null;
        if(!cityName.isEmpty()){
            cityNamePrepared = cityName.substring(0,1).toUpperCase() + cityName.substring(1,cityName.length()).toLowerCase();
        }


        System.out.println(cityNamePrepared);

        StringBuffer response = new StringBuffer();
        String url = "https://api.openaq.org/v1/measurements?city="+cityNamePrepared+"&parameter[]=pm25&parameter[]=pm10" +
                "&parameter[]=so2&parameter[]=no2&parameter[]=o3&parameter[]=co";
        try{
            URL object = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) object.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response code from the server: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

        }catch (MalformedURLException e) {
            e.printStackTrace();
            mainLabelDate.setText("URL error. Try again.");
            mainLabelMin.setText(" ");
            mainLabelMax.setText(" ");
            mainLabelSTD.setText(" ");

        } catch (IOException e) {
            e.printStackTrace();
            mainLabelDate.setText("No internet connection.");
            mainLabelMin.setText("Connect and try again.");
            mainLabelMax.setText(" ");
            mainLabelSTD.setText(" ");
        }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String responseFromJson = new String(response);

            // System.out.println(responseFromJson);

            ResultsOutput results = gson.fromJson(responseFromJson,ResultsOutput.class);

            //System.out.println(results);


            Map m = gson.fromJson(responseFromJson,Map.class);
            System.out.println(m.get("results"));

            System.out.println("Object has: " + m.size() + " attributes");
            for (Object key : m.keySet()){
                System.out.println("key: " + key);

            }
            //DATE
            String date;
            if(results.getResults().isEmpty()){
                mainLabelDate.setText("No data for such city. Try again.");
                mainLabelMin.setText(" ");
                mainLabelMax.setText(" ");
                mainLabelSTD.setText(" ");
                mainTableView.getColumns().clear();
                mainPaneView.getChildren().clear();
                mainTextArea.clear();
            }
            else {
                date = results.getResults().get(0).getDate().getUtc();
                String datePrepared = date.substring(0,10) + " " + date.substring(11,19);
                mainLabelDate.setText(datePrepared);
                mainLabelMin.setText(" ");
                mainLabelMax.setText(" ");
                mainLabelSTD.setText(" ");
                ArrayList<String> valuesToBeShown = parametersToBeShown(results);
                String parametersMinimum = "Minimum: " + valuesToBeShown.get(24) + " " + valuesToBeShown.get(12) + " " + valuesToBeShown.get(30)+ " "
                        + valuesToBeShown.get(25) + " " + valuesToBeShown.get(13) + " " + valuesToBeShown.get(31)+ " "
                        + valuesToBeShown.get(26) + " " + valuesToBeShown.get(14) + " " + valuesToBeShown.get(32)+ " "
                        + valuesToBeShown.get(27) + " " + valuesToBeShown.get(15) + " " + valuesToBeShown.get(33)+ " "
                        + valuesToBeShown.get(28) + " " + valuesToBeShown.get(16) + " " + valuesToBeShown.get(34)+ " "
                        + valuesToBeShown.get(29) + " " + valuesToBeShown.get(17) + " " + valuesToBeShown.get(35)+ " ";
              //  mainLabelMin.setText(parametersMinimum);
                String parametersMaximum ="Maximum: " + valuesToBeShown.get(24) + " " + valuesToBeShown.get(18) + " " + valuesToBeShown.get(30)+ " "
                        + valuesToBeShown.get(25) + " " + valuesToBeShown.get(19) + " " + valuesToBeShown.get(31)+ " "
                        + valuesToBeShown.get(26) + " " + valuesToBeShown.get(20) + " " + valuesToBeShown.get(32)+ " "
                        + valuesToBeShown.get(27) + " " + valuesToBeShown.get(21) + " " + valuesToBeShown.get(33)+ " "
                        + valuesToBeShown.get(28) + " " + valuesToBeShown.get(22) + " " + valuesToBeShown.get(34)+ " "
                        + valuesToBeShown.get(29) + " " + valuesToBeShown.get(23) + " " + valuesToBeShown.get(35)+ " ";
              //  mainLabelMax.setText(parametersMaximum);
                String parametersSTD = "Standard deviation: " + valuesToBeShown.get(24) + " " + valuesToBeShown.get(6) + " " + valuesToBeShown.get(30)+ " "
                        + valuesToBeShown.get(25) + " " + valuesToBeShown.get(7) + " " + valuesToBeShown.get(31) + " "
                        + valuesToBeShown.get(26) + " " + valuesToBeShown.get(8) + " " + valuesToBeShown.get(32)+ " "
                        + valuesToBeShown.get(27) + " " + valuesToBeShown.get(9) + " " + valuesToBeShown.get(33)+ " "
                        + valuesToBeShown.get(28) + " " + valuesToBeShown.get(10) + " " + valuesToBeShown.get(34)+ " "
                        + valuesToBeShown.get(29) + " " + valuesToBeShown.get(11) + " " + valuesToBeShown.get(35)+ " ";
               // mainLabelSTD.setText(parametersSTD);

                String parametersAVG = "Average: " + valuesToBeShown.get(24) + " " + valuesToBeShown.get(0) + " " + valuesToBeShown.get(30)+ " "
                        + valuesToBeShown.get(25) + " " + valuesToBeShown.get(1) + " " + valuesToBeShown.get(31) + " "
                        + valuesToBeShown.get(26) + " " + valuesToBeShown.get(2) + " " + valuesToBeShown.get(32)+ " "
                        + valuesToBeShown.get(27) + " " + valuesToBeShown.get(3) + " " + valuesToBeShown.get(33)+ " "
                        + valuesToBeShown.get(28) + " " + valuesToBeShown.get(4) + " " + valuesToBeShown.get(34)+ " "
                        + valuesToBeShown.get(29) + " " + valuesToBeShown.get(5) + " " + valuesToBeShown.get(35)+ " ";


                System.out.println(parametersMinimum);
                System.out.println(parametersMaximum);
                System.out.println(parametersSTD);
                System.out.println(parametersAVG);

                String textFieldArea = parametersMinimum + parametersMaximum + parametersAVG + parametersSTD;

                System.out.println(textFieldArea);


                //mainTextArea.setText("test");
                mainTextArea.setText(textFieldArea);


                //table view
             //   ObservableList<String> data = mainTableView.getItems();
                //pm 25
                AVGParameters avgParameters = new AVGParameters(Double.parseDouble(valuesToBeShown.get(0)),
                        Double.parseDouble(valuesToBeShown.get(1)),
                        Double.parseDouble(valuesToBeShown.get(2)),Double.parseDouble(valuesToBeShown.get(3)),
                        Double.parseDouble(valuesToBeShown.get(4)),Double.parseDouble(valuesToBeShown.get(5)));
                System.out.println(avgParameters);

                mainTableView.getColumns().clear();

                TableColumn columnPM25 = new TableColumn<>("pm25");
                TableColumn columnPM10 = new TableColumn<>("pm10");
                TableColumn columnSO2 = new TableColumn<>("so2");
                TableColumn columnNO2 = new TableColumn<>("no2");
                TableColumn columnO3 = new TableColumn<>("o3");
                TableColumn columnCO = new TableColumn<>("co");


                mainTableView.getColumns().addAll(columnPM25,columnPM10,columnSO2,columnNO2,columnO3,columnCO);

                final ObservableList<AVGParameters> data = FXCollections.observableArrayList(avgParameters);

                columnPM25.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("pm25"));
                columnPM10.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("pm10"));
                columnSO2.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("so2"));
                columnNO2.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("no2"));
                columnO3.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("o3"));
                columnCO.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("co"));

                mainTableView.setItems(data);


                //barchart


                mainPaneView.getChildren().clear();
                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setLabel("Parameters");
                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Value");

//                yAxis.setStyle("-fx-text-fill: white;");
//                xAxis.setStyle("-fx-text-fill: white;");
                BarChart barChart = new BarChart(xAxis,yAxis);
                barChart.setTitle("Found parameters compared with very good values");

                //very good values

                XYChart.Series series = new XYChart.Series();

                series.getData().add(new XYChart.Data<>("pm25", 13.));
                series.getData().add(new XYChart.Data<>("pm10", 21.));
                series.getData().add(new XYChart.Data<>("so2", 51.));
                series.getData().add(new XYChart.Data<>("no2", 41.));
                series.getData().add(new XYChart.Data<>("o3", 71.));
                series.getData().add(new XYChart.Data<>("co", 3.));

                //found values


                XYChart.Series seriesFound = new XYChart.Series();

                if(Double.parseDouble(valuesToBeShown.get(0)) < 0 || valuesToBeShown.get(0) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("pm25", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("pm25", Double.parseDouble(valuesToBeShown.get(0))));

                }

                if(Double.parseDouble(valuesToBeShown.get(1)) < 0 || valuesToBeShown.get(1) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("pm10", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("pm10", Double.parseDouble(valuesToBeShown.get(1))));

                }

                if(Double.parseDouble(valuesToBeShown.get(2)) < 0 || valuesToBeShown.get(2) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("so2", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("so2", Double.parseDouble(valuesToBeShown.get(2))));

                }

                if(Double.parseDouble(valuesToBeShown.get(3)) < 0 || valuesToBeShown.get(3) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("no2", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("no2", Double.parseDouble(valuesToBeShown.get(3))));
                }

                if(Double.parseDouble(valuesToBeShown.get(4)) < 0 || valuesToBeShown.get(4) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("o3", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("o3", Double.parseDouble(valuesToBeShown.get(4))));

                }

                if(Double.parseDouble(valuesToBeShown.get(5)) < 0 || valuesToBeShown.get(5) == "NaN"){
                    seriesFound.getData().add(new XYChart.Data<>("co", -1));
                }
                else {
                    seriesFound.getData().add(new XYChart.Data<>("co", Double.parseDouble(valuesToBeShown.get(5))));

                }



                series.setName("Very good values");
                seriesFound.setName("Found values");


                System.out.println(valuesToBeShown.get(0));
                System.out.println(valuesToBeShown.get(5));

                System.out.println(seriesFound.toString());
                System.out.println(seriesFound.getData().toString());
               barChart.getData().addAll(series,seriesFound);

                mainPaneView.getChildren().add(barChart);

                //to Json

                Parameters parameters = new Parameters(datePrepared,Double.parseDouble(valuesToBeShown.get(12)),
                        Double.parseDouble(valuesToBeShown.get(13)),Double.parseDouble(valuesToBeShown.get(14)),
                        Double.parseDouble(valuesToBeShown.get(15)),Double.parseDouble(valuesToBeShown.get(16)),
                        Double.parseDouble(valuesToBeShown.get(17)),Double.parseDouble(valuesToBeShown.get(18)),
                        Double.parseDouble(valuesToBeShown.get(19)),Double.parseDouble(valuesToBeShown.get(20)),
                        Double.parseDouble(valuesToBeShown.get(21)),Double.parseDouble(valuesToBeShown.get(22)),
                        Double.parseDouble(valuesToBeShown.get(23)),Double.parseDouble(valuesToBeShown.get(0)),
                        Double.parseDouble(valuesToBeShown.get(1)),Double.parseDouble(valuesToBeShown.get(2)),
                        Double.parseDouble(valuesToBeShown.get(3)),Double.parseDouble(valuesToBeShown.get(4)),
                        Double.parseDouble(valuesToBeShown.get(5)),Double.parseDouble(valuesToBeShown.get(6)),
                        Double.parseDouble(valuesToBeShown.get(7)),Double.parseDouble(valuesToBeShown.get(8)),
                        Double.parseDouble(valuesToBeShown.get(9)),Double.parseDouble(valuesToBeShown.get(10)),
                        Double.parseDouble(valuesToBeShown.get(11)));

                System.out.println(parameters);
                foundResultsToJson = gson.toJson(parameters);

                System.out.println(foundResultsToJson);



            }

    }

    public ArrayList<String> parametersToBeShown(ResultsOutput results){
        ArrayList<String> output = new ArrayList<>();
        double pm25 = 0;
        double pm10 = 0;
        double so2 = 0;
        double no2 = 0;
        double o3 = 0;
        double co = 0;
        ArrayList<Double> pm25ArrayList = new ArrayList<>();
        ArrayList<Double> pm10ArrayList = new ArrayList<>();
        ArrayList<Double> so2ArrayList = new ArrayList<>();
        ArrayList<Double> no2ArrayList = new ArrayList<>();
        ArrayList<Double> o3ArrayList = new ArrayList<>();
        ArrayList<Double> coArrayList = new ArrayList<>();
        int pm25Count = 0;
        int pm10Count = 0;
        int so2Count = 0;
        int no2Count = 0;
        int o3Count = 0;
        int coCount = 0;

        String pm25Unit = "no data";
        String pm10Unit = "no data";
        String so2Unit = "no data";
        String no2Unit = "no data";
        String o3Unit = "no data";
        String coUnit = "no data";

        String pm25Parameter = "no data";
        String pm10Parameter = "no data";
        String so2Parameter = "no data";
        String no2Parameter = "no data";
        String o3Parameter = "no data";
        String coParameter = "no data";

        // STD, AVG
        for (int i = 0; i < results.getMeta().getLimit() ; i++) {

            if(results.getResults().get(i).getParameter().contains("pm25")){
                pm25+= results.getResults().get(i).getValue();
                pm25Count++;
                pm25ArrayList.add(results.getResults().get(i).getValue());
                pm25Unit = results.getResults().get(i).getUnit();
                pm25Parameter = results.getResults().get(i).getParameter();
            }
            else if(results.getResults().get(i).getParameter().contains("pm10")){
                pm10+= results.getResults().get(i).getValue();
                pm10Count++;
                pm10ArrayList.add(results.getResults().get(i).getValue());
                pm10Unit = results.getResults().get(i).getUnit();
                pm10Parameter = results.getResults().get(i).getParameter();

            }
            else if(results.getResults().get(i).getParameter().contains("so2")){
                so2+= results.getResults().get(i).getValue();
                so2Count++;
                so2ArrayList.add(results.getResults().get(i).getValue());
                so2Unit = results.getResults().get(i).getUnit();
                so2Parameter = results.getResults().get(i).getParameter();

            }
            else if(results.getResults().get(i).getParameter().contains("no2")){
                no2+= results.getResults().get(i).getValue();
                no2Count++;
                no2ArrayList.add(results.getResults().get(i).getValue());
                no2Unit = results.getResults().get(i).getUnit();
                no2Parameter = results.getResults().get(i).getParameter();

            }
            else if(results.getResults().get(i).getParameter().contains("o3")){
                o3+= results.getResults().get(i).getValue();
                o3Count++;
                o3ArrayList.add(results.getResults().get(i).getValue());
                o3Unit = results.getResults().get(i).getUnit();
                o3Parameter = results.getResults().get(i).getParameter();

            }
            else if(results.getResults().get(i).getParameter().contains("co")){
                co+= results.getResults().get(i).getValue();
                coCount++;
                coArrayList.add(results.getResults().get(i).getValue());
                coUnit = results.getResults().get(i).getUnit();
                coParameter = results.getResults().get(i).getParameter();

            }
        }
        //AVG
        double pm25AVG = pm25/pm25Count;
        double pm10AVG = pm10/pm10Count;
        double so2AVG = so2/so2Count;
        double no2AVG = no2/no2Count;
        double o3AVG = o3/o3Count;
        double coAVG = co/coCount;

        output.add(0,Double.toString(pm25AVG));
        output.add(1,Double.toString(pm10AVG));
        output.add(2,Double.toString(so2AVG));
        output.add(3,Double.toString(no2AVG));
        output.add(4,Double.toString(o3AVG));
        output.add(5,Double.toString(coAVG));

        double pm25STD = 0;
        double pm10STD = 0;
        double so2STD = 0;
        double no2STD = 0;
        double o3STD = 0;
        double coSTD = 0;

        for (Double d: pm25ArrayList) {
            pm25STD += Math.pow((d - pm25AVG),2);
        }
        pm25STD = Math.sqrt(pm25STD/pm25Count);

        for (Double d: pm10ArrayList) {
            pm10STD += Math.pow((d - pm10AVG),2);
        }
        pm10STD = Math.sqrt(pm10STD/pm10Count);

        for (Double d: so2ArrayList) {
            so2STD += Math.pow((d - so2AVG),2);
        }
        so2STD = Math.sqrt(so2STD/so2Count);

        for (Double d: no2ArrayList) {
            no2STD += Math.pow((d - no2AVG),2);
        }
        no2STD = Math.sqrt(no2STD/no2Count);

        for (Double d: o3ArrayList) {
            o3STD += Math.pow((d - o3AVG),2);
        }
        o3STD = Math.sqrt(o3STD/o3Count);

        for (Double d: coArrayList) {
            coSTD += Math.pow((d - coAVG),2);
        }
        coSTD = Math.sqrt(coSTD/coCount);

        output.add(6,Double.toString(pm25STD));
        output.add(7,Double.toString(pm10STD));
        output.add(8,Double.toString(so2STD));
        output.add(9,Double.toString(no2STD));
        output.add(10,Double.toString(o3STD));
        output.add(11,Double.toString(coSTD));

        //min
        Collections.sort(pm25ArrayList);
        Collections.sort(pm10ArrayList);
        Collections.sort(so2ArrayList);
        Collections.sort(no2ArrayList);
        Collections.sort(o3ArrayList);
        Collections.sort(coArrayList);

        if(pm25ArrayList.isEmpty()){
            pm25ArrayList.add(0,-1.);

        }
        if(pm10ArrayList.isEmpty()){
            pm10ArrayList.add(0,-1.);
        }
        if(so2ArrayList.isEmpty()){
            so2ArrayList.add(0,-1.);
        }
        if(no2ArrayList.isEmpty()){
            no2ArrayList.add(0,-1.);
        }
        if(o3ArrayList.isEmpty()){
            o3ArrayList.add(0,-1.);
        }
        if(coArrayList.isEmpty()){
            coArrayList.add(0,-1.);
        }
        output.add(12,Double.toString(pm25ArrayList.get(0)));
        output.add(13,Double.toString(pm10ArrayList.get(0)));
        output.add(14,Double.toString(so2ArrayList.get(0)));
        output.add(15,Double.toString(no2ArrayList.get(0)));
        output.add(16,Double.toString(o3ArrayList.get(0)));
        output.add(17,Double.toString(coArrayList.get(0)));

        //max

        output.add(18,Double.toString(pm25ArrayList.get(pm25ArrayList.size()-1)));
        output.add(19,Double.toString(pm10ArrayList.get(pm10ArrayList.size()-1)));
        output.add(20,Double.toString(so2ArrayList.get(so2ArrayList.size()-1)));
        output.add(21,Double.toString(no2ArrayList.get(no2ArrayList.size()-1)));
        output.add(22,Double.toString(o3ArrayList.get(o3ArrayList.size()-1)));
        output.add(23,Double.toString(coArrayList.get(coArrayList.size()-1)));

        output.add(24,pm25Parameter);
        output.add(25,pm10Parameter);
        output.add(26,so2Parameter);
        output.add(27,no2Parameter);
        output.add(28,o3Parameter);
        output.add(29,coParameter);


        output.add(30,pm25Unit);
        output.add(31,pm10Unit);
        output.add(32,so2Unit);
        output.add(33,no2Unit);
        output.add(34,o3Unit);
        output.add(35,coUnit);



        return output;
    }

    @FXML
    void load(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files(*.json)","*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println("Path: " + file);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        Parameters parameters = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            System.out.println();
            parameters = gson.fromJson(bufferedReader,Parameters.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
         System.out.println("From Json: " + parameters);

        mainTableView.getColumns().clear();
        mainPaneView.getChildren().clear();

        //set labels
        mainLabelDate.setText(parameters.getDate());
        mainLabelMin.setText(file.getName().substring(0,file.getName().length()-5));
        mainLabelMax.setText(" ");
        mainLabelSTD.setText(" ");



        String textFieldArea = parameters.toString();

        System.out.println(textFieldArea);

        mainTextArea.setText(textFieldArea);

        //set table

        AVGParameters avgParameters = new AVGParameters(parameters.getPm25avg(),parameters.getPm10avg(),
                parameters.getSo2avg(),parameters.getNo2avg(),parameters.getO3avg(),parameters.getCoavg());
        System.out.println(avgParameters);

        mainTableView.getColumns().clear();

        TableColumn columnPM25 = new TableColumn<>("pm25");
        TableColumn columnPM10 = new TableColumn<>("pm10");
        TableColumn columnSO2 = new TableColumn<>("so2");
        TableColumn columnNO2 = new TableColumn<>("no2");
        TableColumn columnO3 = new TableColumn<>("o3");
        TableColumn columnCO = new TableColumn<>("co");


        mainTableView.getColumns().addAll(columnPM25,columnPM10,columnSO2,columnNO2,columnO3,columnCO);

        final ObservableList<AVGParameters> data = FXCollections.observableArrayList(avgParameters);

        columnPM25.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("pm25"));
        columnPM10.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("pm10"));
        columnSO2.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("so2"));
        columnNO2.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("no2"));
        columnO3.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("o3"));
        columnCO.setCellValueFactory(new PropertyValueFactory<AVGParameters,String>("co"));

        mainTableView.setItems(data);

        //set chart
        //barchart


        mainPaneView.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Parameters");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");
        BarChart barChart = new BarChart(xAxis,yAxis);
        barChart.setTitle("Found parameters compared with very good values");

        //very good values

        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data<>("pm25", 13.));
        series.getData().add(new XYChart.Data<>("pm10", 21.));
        series.getData().add(new XYChart.Data<>("so2", 51.));
        series.getData().add(new XYChart.Data<>("no2", 41.));
        series.getData().add(new XYChart.Data<>("o3", 71.));
        series.getData().add(new XYChart.Data<>("co", 3.));

        //found values


        XYChart.Series seriesFound = new XYChart.Series();

        series.setName("Very good values");
        seriesFound.setName("Found values");

        seriesFound.getData().add(new XYChart.Data<>("pm25",parameters.getPm25avg()));
        seriesFound.getData().add(new XYChart.Data<>("pm10",parameters.getPm10avg()));
        seriesFound.getData().add(new XYChart.Data<>("so2",parameters.getSo2avg()));
        seriesFound.getData().add(new XYChart.Data<>("no2",parameters.getNo2avg()));
        seriesFound.getData().add(new XYChart.Data<>("o3", parameters.getO3avg()));
        seriesFound.getData().add(new XYChart.Data<>("co", parameters.getCoavg()));


        System.out.println(seriesFound.toString());
        System.out.println(seriesFound.getData().toString());
        barChart.getData().addAll(series,seriesFound);

        mainPaneView.getChildren().add(barChart);

    }

    @FXML
    void save(ActionEvent event) {
        String fileName = mainTextFieldName.getText();
        if(fileName.isEmpty()){
            fileName = "unnamed";
        }

        final DirectoryChooser directoryChooser = new DirectoryChooser();

        File file = directoryChooser.showDialog(null);
        if (file != null){
            System.out.println("Path: " + file.getAbsolutePath());
            String path = file.getAbsolutePath();

            //json

            if(foundResultsToJson != null){
                //write to file

                OutputStream outputStream = null;

                try {
                    System.out.println(path+"/"+fileName+".json");
                    outputStream = new FileOutputStream(new File(path+"/"+fileName+".json"));
                    outputStream.write(foundResultsToJson.getBytes(),0,foundResultsToJson.length());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try{
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("saved successfully");
            }
            else{
                System.out.println("json null");
            }


        }
        else{
            System.out.println("Error");
        }

    }

    @FXML
    void initialize() {
        assert mainTextFieldCityName != null : "fx:id=\"mainTextFieldCityName\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonFind != null : "fx:id=\"mainButtonFind\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextFieldName != null : "fx:id=\"mainTextFieldName\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonSave != null : "fx:id=\"mainButtonSave\" was not injected: check your FXML file 'main.fxml'.";
        assert mainButtonLoad != null : "fx:id=\"mainButtonLoad\" was not injected: check your FXML file 'main.fxml'.";
        assert mainLabelDate != null : "fx:id=\"mainLabelDate\" was not injected: check your FXML file 'main.fxml'.";
        assert mainLabelMax != null : "fx:id=\"mainLabelMax\" was not injected: check your FXML file 'main.fxml'.";
        assert mainLabelMin != null : "fx:id=\"mainLabelMin\" was not injected: check your FXML file 'main.fxml'.";
        assert mainLabelSTD != null : "fx:id=\"mainLabelSTD\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTableView != null : "fx:id=\"mainTableView\" was not injected: check your FXML file 'main.fxml'.";
        assert mainPaneView != null : "fx:id=\"mainPaneView\" was not injected: check your FXML file 'main.fxml'.";
        assert mainTextArea != null : "fx:id=\"mainTextArea\" was not injected: check your FXML file 'main.fxml'.";

    }
}
