package com.bayu.demo.service.impl;

import com.bayu.demo.dto.RecruitmentDTO;
import com.bayu.demo.service.RecruitmentService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private static final String API_URL_ALL_RECRUITMENTS = "https://dev6.dansmultipro.com/api/recruitment/positions.json";

    @Override
    public List<RecruitmentDTO> getAllRecruitments() {
        try {
            List<RecruitmentDTO> recruitmentDTOList = new ArrayList<>();

            // Create a URL object
            URL url = new URL(API_URL_ALL_RECRUITMENTS);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP method (GET by default)
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (HTTP 200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON array response using Gson
                Gson gson = new Gson();
                RecruitmentDTO[] jsonArray = gson.fromJson(response.toString(), RecruitmentDTO[].class);

                // Work with the parsed JSON array
                recruitmentDTOList.addAll(Arrays.asList(jsonArray));
            } else {
                System.out.println("HTTP Request Failed with Response Code: " + responseCode);
            }
            // Close the connection
            connection.disconnect();

            return recruitmentDTOList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
