package io.prathyusha.coronavirustracker;

import java.io.*;
import java.util.*;

//import com.fasterxml.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class RecoveredCsv 
{
    public static void main(String[] args)throws IOException
    {
        File input = new File("C:\\coronavirustracker\\recoveries.csv");
        File output = new File("C:\\coronavirustracker\\recoveries.json");

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
    }
    
}