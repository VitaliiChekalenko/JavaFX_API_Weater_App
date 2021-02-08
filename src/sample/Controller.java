package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text plessure;

    @FXML
    void initialize() {
       getData.setOnAction(event ->{
           String getUserCity = city.getText().trim();
           if(!getUserCity.equals("")) {
               String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=30acf5eea54dd2fa90a1017360189e8d");
               System.out.println(output);

               if (!output.isEmpty()) {
                   JSONObject obj = new JSONObject(output);

                   temp_info.setText("Температура: " + String.format("%.2f",((obj.getJSONObject("main").getDouble("temp"))-273.15d))+ " C");
                   temp_feels.setText("Ощущается: " + String.format("%.2f",((obj.getJSONObject("main").getDouble("feels_like"))-273.15d))+ " C");
                   temp_max.setText("Максимум: " + String.format("%.2f",((obj.getJSONObject("main").getDouble("temp_max"))-273.15d))+ " C");
                   temp_min.setText("Минимум: " + String.format("%.2f",((obj.getJSONObject("main").getDouble("temp_min"))-273.15d))+ " C");
                   plessure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure") + " Па");
//                   temp_info.setText("Температура: " + String.format("%.2f", ((obj.getJSONObject("main").getDouble("temp"))-271)));
//                   temp_info.setText("Температура: " + String.format("%.2f", ((obj.getJSONObject("main").getDouble("temp"))-271)));
//                   temp_info.setText("Температура: " + Math.round((((obj.getJSONObject("main").getDouble("temp"))-271)) * 100) * 0.01d);
//                   temp_feels.setText("Ощущается: " + Math.round((((obj.getJSONObject("main").getDouble("feels_like"))-271)) * 100) * 0.01d);
//                   temp_max.setText("Максимум: " + Math.round((((obj.getJSONObject("main").getDouble("temp_max"))-271)) * 100) * 0.01d);
//                   temp_min.setText("Минимум: " + Math.round((((obj.getJSONObject("main").getDouble("temp_min"))-272)) * 100) * 0.01d);
//                   plessure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));

               }
           }
       });
    }

    private static String getUrlContent(String urlAddress){
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL (urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("Город не найдет");


        }
        return content.toString();
    }
}
