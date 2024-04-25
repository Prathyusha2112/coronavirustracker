package io.prathyusha.coronavirustracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
//import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;
import org.springframework.stereotype.Service;
//import io.prathyusha.coronavirustracker.LocationStats;
import java.io.*;
import java.net.URI;
import java.net.http.*;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


@Service
public class CoronaVirusDataService {

    private static String virus_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String deaths_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static String recover_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
    public List<LocationStats> allStats = new ArrayList<>();
    public List<LocationStats> allStatsd = new ArrayList<>();
    public List<LocationStats> allStatsr = new ArrayList<>();
    

    public List<LocationStats> getAllStats() {
        return allStats;
    }
    public List<LocationStats> getAllStatsd(){
        return allStatsd;
    }
    public List<LocationStats> getAllStatsr(){
        return allStatsr;
    }


    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException
    {

        

        List<LocationStats> newStats = new ArrayList<>();
        List<LocationStats> newStatsd = new ArrayList<>();
        List<LocationStats> newStatsr = new ArrayList<>();
        HttpClient client  = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(virus_data_url))
                .build();
        HttpRequest requestd = HttpRequest.newBuilder()
                .uri(URI.create(deaths_data_url))
                .build();  
        HttpRequest requestr = HttpRequest.newBuilder()
                .uri(URI.create(recover_data_url))   
                .build();          
        HttpResponse<String> httpResponse  = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> httpResponsed  = client.send(requestd, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> httpResponser  = client.send(requestr, HttpResponse.BodyHandlers.ofString());
        
        //System.out.println(httpResponse.body()); 
        StringReader csvBodyReader = new StringReader(httpResponse.body()); 
        StringReader csvBodyReaderd = new StringReader(httpResponsed.body());
        StringReader csvBodyReaderr = new StringReader(httpResponser.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        Iterable<CSVRecord> recordsd = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReaderd);
        Iterable<CSVRecord> recordsr = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReaderr);
        
        
        //int latestCases;
        //int prevDayCases;
        //LocationStats locationStat = new LocationStats();
        for (CSVRecord record : records)
        {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiff(latestCases - prevDayCases);
            //System.out.println(locationStat);
            newStats.add(locationStat);

            

            //String name = record.get("Name");
        }
        this.allStats = newStats;

        /*for (CSVRecord record : recordsd)
        {
            LocationStats locationStatd = new LocationStats();
            locationStatd.setLatestTotalDeaths(latestDeaths);
            locationStatd.setDiff(latestCases - prevDayCases);
            //System.out.println(locationStat);
            newStats.add(locationStatd);
        }*/
        //int latestDeaths;
        for (CSVRecord record : recordsd)
        {
            LocationStats locationStatd = new LocationStats();
            locationStatd.setState(record.get("Province/State"));
            locationStatd.setCountry(record.get("Country/Region"));
            int latestDeaths = Integer.parseInt(record.get(record.size() - 1));
            //int prevDayDeaths = Integer.parseInt(record.get(record.size() - 2));
            locationStatd.setLatestTotalDeaths(latestDeaths);
            //locationStat.setDiff(latestDeaths - prevDayDeaths);
            //System.out.println(locationStatd);
            newStatsd.add(locationStatd);

            

            //String name = record.get("Name");
        }
        this.allStatsd = newStatsd;

        //int latestRecoveries;
        for (CSVRecord record : recordsr)
        {
            LocationStats locationStatr = new LocationStats();
            locationStatr.setState(record.get("Province/State"));
            locationStatr.setCountry(record.get("Country/Region"));
            int latestRecoveries = Integer.parseInt(record.get(record.size() - 1));
            //int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStatr.setLatestTotalRecoveries(latestRecoveries);
            //locationStat.setDiff(latestCases - prevDayCases);
            //System.out.println(locationStat);
            newStatsr.add(locationStatr);

            

            //String name = record.get("Name");
        }
        


        this.allStatsr = newStatsr;     
        //this.allStatsd = newStatsd; 

    }
    

    /**
     * @return List<LocationStats> return the allStats
     */
    

 
}