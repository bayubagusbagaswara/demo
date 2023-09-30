package com.bayu.demo.service.impl;

import com.bayu.demo.dto.RecruitmentDTO;
import com.bayu.demo.service.RecruitmentService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private static final String API_URL_ALL_RECRUITMENTS = "https://dev6.dansmultipro.com/api/recruitment/positions.json";

    @Override
    public List<RecruitmentDTO> getAllRecruitments() {
        try {
            List<RecruitmentDTO> jobs = new ArrayList<>();

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
                JsonArray jsonArray = gson.fromJson(response.toString(), JsonArray.class);
                java.lang.reflect.Type entityType = new TypeToken<List<RecruitmentDTO>>() {}.getType();

                // Parse the JSON array into a list of MyEntity objects
                jobs = gson.fromJson(jsonArray, entityType);

                for (RecruitmentDTO recruitmentDTO : jobs) {
                    log.info("Id : {}", recruitmentDTO.getId());
                    log.info("Created At : {}", recruitmentDTO.getCreatedAt());
                    log.info("Company URL : {}", recruitmentDTO.getCompanyUrl());
                }
            }
            // Close the connection
            connection.disconnect();
            return jobs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
