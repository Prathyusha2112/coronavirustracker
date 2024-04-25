package io.prathyusha.coronavirustracker;
import java.io.File;
//import java.io.IOException;
import java.util.List;
import java.util.Map;
//import java.lang.Object;

//import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
//import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
//import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
//import com.fasterxml.jackson.databind.ObjectReader.readValues;


//import org.spf4j.jaxrs.CsvSchema;

public class DeathsCsv 
{

    public static void main(String[] args) throws Exception 
    {
        File input = new File("C:\\coronavirustracker\\deaths.csv");
        File output = new File("C:\\coronavirustracker\\deaths.json");


        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);


        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
    }
        
    
}