package com.redeveight.covid.services;

import com.redeveight.covid.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {
    private static String COVID_DATA_URL = "https://covid.ourworldindata.org/data/owid-covid-data.csv";

    private List<LocationStats> stats = new ArrayList<>();

    public List<LocationStats> getStats() {
        return stats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setContinent(record.get("continent"));
            locationStats.setLocation(record.get("location"));
            locationStats.setLatestTotalCases(record.get("total_cases"));
            //System.out.println(locationStats.toString());
            newStats.add(locationStats);
        }
        this.stats = newStats;
    }
}