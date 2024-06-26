package io.github.wkktoria.weatherreport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class WeatherReportApplication {
    private static final Logger logger = LoggerFactory.getLogger(WeatherReportApplication.class);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weather Report");
        frame.setSize(new Dimension(400, 250));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(27, 71, 120));

        String apiKey = System.getenv("WEATHER_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            logger.error("API key was not provided");
            System.exit(1);
        }

        WeatherJson weatherJson = new WeatherJson(apiKey);
        Weather weather = weatherJson.createWeather("Warsaw");

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(400, 150));
        infoPanel.setLayout(new GridLayout(2, 2));
        infoPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        infoPanel.setBackground(new Color(27, 71, 120));

        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(400, 50));
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        inputPanel.setBackground(new Color(27, 71, 120));

        Image weatherImage = weather.getImage();
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(weatherImage));

        JLabel locationLabel = createInfoLabel(weather.getLocation());
        JLabel temperatureLabel = createInfoLabel("Temperature: " + weather.getTemperature() + "°C");
        JLabel humidityLabel = createInfoLabel("Humidity: " + weather.getHumidity() + "%");

        JTextField locationField = new JTextField();
        locationField.setPreferredSize(new Dimension(300, 50));
        locationField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchWeather(weatherJson, locationField, imageLabel, locationLabel, temperatureLabel, humidityLabel);
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {

            }
        });
        JButton searchButton = new JButton("Search");
        searchButton.setOpaque(true);
        searchButton.setContentAreaFilled(true);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setBackground(new Color(28, 141, 162));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> searchWeather(weatherJson, locationField, imageLabel, locationLabel, temperatureLabel, humidityLabel));

        infoPanel.add(imageLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(temperatureLabel);
        infoPanel.add(humidityLabel);

        inputPanel.add(locationField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        panel.add(infoPanel);
        panel.add(inputPanel);

        frame.add(panel);

        frame.setVisible(true);
    }

    private static JLabel createInfoLabel(final String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);

        return label;
    }

    private static void searchWeather(final WeatherJson weatherJson, final JTextField locationField, final JLabel imageLabel, final JLabel locationLabel, final JLabel temperatureLabel, final JLabel humidityLabel) {
        String location = locationField.getText();

        if (location.isEmpty()) {
            return;
        }

        Weather weather;
        try {
            weather = weatherJson.createWeather(location);
        } catch (WeatherJson.WeatherJsonException e) {
            JOptionPane.showMessageDialog(null, String.format("Could not get weather for '%s'.", locationField.getText()), "Invalid location", JOptionPane.ERROR_MESSAGE);
            return;
        }

        locationLabel.setText(weather.getLocation());
        temperatureLabel.setText("Temperature: " + weather.getTemperature() + "°C");
        humidityLabel.setText("Humidity: " + weather.getHumidity() + "%");
        imageLabel.setIcon(new ImageIcon(weather.getImage()));

        locationField.setText("");
    }
}

