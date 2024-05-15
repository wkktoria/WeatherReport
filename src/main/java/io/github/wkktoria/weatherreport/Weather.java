package io.github.wkktoria.weatherreport;

import java.awt.*;
import java.math.BigDecimal;

class Weather {
    private String location;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private Image image;

    Weather(String location, BigDecimal temperature, BigDecimal humidity, Image image) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.image = image;
    }

    String getLocation() {
        return location;
    }

    BigDecimal getTemperature() {
        return temperature;
    }

    BigDecimal getHumidity() {
        return humidity;
    }

    Image getImage() {
        return image;
    }

    //    private final String apiKey;
//    private Optional<JSONObject> json;
//
//    Weather(String location, String apiKey) {
//        this.apiKey = apiKey;
//        json = createJsonObject(location);
//    }

//    Optional<Image> createImage() {
//        try {
//            String iconCode;
//            Optional<JSONArray> weatherJsonArray = getWeatherJsonArray();
//            if (json.isPresent() && weatherJsonArray.isPresent()) {
//                iconCode = weatherJsonArray.get().getJSONObject(0).getString("icon");
//                String imageUrlString = "https://openweathermap.org/img/wn/%s.png";
//                return Optional.of(ImageIO.read(new URI(String.format(imageUrlString, iconCode)).toURL()));
//            }
//        } catch (IOException | URISyntaxException e) {
//            return Optional.empty();
//        }
//        return Optional.empty();
//    }

//    Optional<JSONObject> getJson() {
//        return json;
//    }
//
//    void setJson(final String location) {
//        this.json = createJsonObject(location);
//    }
//
//    Optional<String> getLocation() {
//        return json.map(j -> j.getString("name"));
//    }
//
//    Optional<BigDecimal> getTemperature() {
//        Optional<JSONObject> mainJsonObject = getMainJsonObject();
//        return mainJsonObject.map(jsonObject -> jsonObject.getBigDecimal("temp"));
//    }
//
//    Optional<BigDecimal> getHumidity() {
//        Optional<JSONObject> mainJsonObject = getMainJsonObject();
//        return mainJsonObject.map(jsonObject -> jsonObject.getBigDecimal("humidity"));
//    }
//
//    private Optional<JSONObject> getMainJsonObject() {
//        return json.map(jsonObject -> jsonObject.getJSONObject("main"));
//    }
//
//    private Optional<JSONArray> getWeatherJsonArray() {
//        return json.map(jsonObject -> jsonObject.getJSONArray("weather"));
//    }

//    private Optional<JSONObject> createJsonObject(final String location) {
//        try {
//            URI uri = new URI(String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s", location, apiKey));
//            URLConnection connection = uri.toURL().openConnection();
//            InputStream inputStream = connection.getInputStream();
//            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
//            String response = scanner.hasNext() ? scanner.next() : "";
//            return Optional.of(new JSONObject(response));
//        } catch (URISyntaxException | IOException e) {
//            return Optional.empty();
//        }
//    }
}
