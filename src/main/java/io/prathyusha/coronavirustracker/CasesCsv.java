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

public class CasesCsv {

    public static void main(String[] args) throws Exception {
        File input = new File("C:\\coronavirustracker\\ccases.csv");
        File output = new File("C:\\coronavirustracker\\cases.json");


        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);


        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));

     /*   List<Map<?, ?>> data = readObjectsFromCsv(input);
        writeAsJson(data, output);
    }*/

    /*public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        /*CsvSchema schema = CsvSchema.builder()
                .addColumn("country", CsvSchema.ColumnType.STRING)
                .addColumn("state", CsvSchema.ColumnType.STRING)
                .addColumn("cases", CsvSchema.ColumnType.NUMBER)
                .build().withHeader();*/

      /*  CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(schema)
                .readValues(file);
        return mappingIterator.readAll();
        
        
        
    }

    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }*/
}
}