package io.prathyusha.coronavirustracker;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class SaveDeathsFile {
    public static void main(String[] args)throws IOException
    {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv").openStream());
        FileOutputStream fileOS = new FileOutputStream("C:\\coronavirustracker\\deaths.csv")) 
        {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1)
            {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            System.out.println("file couldnot be saved to computer");
            }
    
    }
}