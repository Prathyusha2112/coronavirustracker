package io.prathyusha.coronavirustracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{

    @Autowired
    CoronaVirusDataService coronavirusdataservice;
    @GetMapping("/")
    public String home(Model model)
    {

        List<LocationStats> allStats = coronavirusdataservice.getAllStats();
        List<LocationStats> allStatsd = coronavirusdataservice.getAllStatsd();
        List<LocationStats> allStatsr = coronavirusdataservice.getAllStatsr();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalReportedDeaths = allStatsd.stream().mapToInt(stat -> stat.getLatestTotalDeaths()).sum();
        int totalReportedRecoveries = allStatsr.stream().mapToInt(stat -> stat.getLatestTotalRecoveries()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("locationStatsd", allStatsd);
        model.addAttribute("locationStatsr", allStatsr);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalReportedDeaths", totalReportedDeaths);
        model.addAttribute("totalReportedRecoveries", totalReportedRecoveries);
        return "home";
    }
    
}