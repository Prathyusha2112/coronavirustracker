package io.prathyusha.coronavirustracker;

import java.io.*;
import java.net.URL;

public class SaveRecoveryFile 
{
    public static void main(String[] args)throws IOException
    {
        try(BufferedInputStream inputStream = new BufferedInputStream(new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv").openStream());
        FileOutputStream fileOS = new FileOutputStream("C:\\coronavirustracker\\recoveries.csv"))
        {
            byte data[] = new byte[1024];
            int bytecontent;
            while((bytecontent = inputStream.read(data, 0, 1024))!=-1)
            {
                fileOS.write(data, 0, bytecontent);

            }
        }catch(Exception e) {
            System.out.println("the file could not be saved to the computer");
        }
       
        
    }

    
}