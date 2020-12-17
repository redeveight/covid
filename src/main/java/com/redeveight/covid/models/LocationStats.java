package com.redeveight.covid.models;

public class LocationStats {
    private String continent;
    private String location;
    private String latestTotalCases;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(String latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "continent='" + continent + '\'' +
                ", location='" + location + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
