package io.prathyusha.coronavirustracker;

public class LocationStats 
{

	private String state;
    private String country;
    private int latestTotalCases;
    private int diff;
    private int latestTotalDeaths;
    private int latestTotalRecoveries;
    
    
    public String getState() {
        return state;
    }

    
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return String return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return int return the latestTotalCases
     */
    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    /**
     * @param latestTotalCases the latestTotalCases to set
     */
    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    /**
     * @return int return the latestTotalDeaths
     */
    public int getLatestTotalDeaths()
    {
        return latestTotalDeaths;
    }

    /**
     * @param latestTotalDeaths the latestTotalDeaths to set
     */

    public void setLatestTotalDeaths(int latestTotalDeaths) {
        this.latestTotalDeaths = latestTotalDeaths;
    }

    @Override
    public String toString()
    {
        return "LocationStats{"+"state = "+state + "country = "+country+"\"latest total deaths = " + latestTotalDeaths + "}";
    }


    /**
     * @return int return the diff
     */
    public int getDiff() {
        return diff;
    }

    /**
     * @param diff the diff to set
     */
    public void setDiff(int diff) {
        this.diff = diff;
    }


    /**
     * @return int return the latestTotalRecoveries
     */
    public int getLatestTotalRecoveries() {
        return latestTotalRecoveries;
    }

    /**
     * @param latestTotalRecoveries the latestTotalRecoveries to set
     */
    public void setLatestTotalRecoveries(int latestTotalRecoveries) {
        this.latestTotalRecoveries = latestTotalRecoveries;
    }

}