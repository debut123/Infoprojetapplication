package fr.isep.francois.projetapplication;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






public class Demo_CSV {




    private static Projet projet;

    public static void setProjet(Projet projet) {
        Demo_CSV.projet = projet;
    }

    public static void main(String[] args) throws IOException {
        var vols = lireCSV();
        //ecrireCSV(vols);
        ecrireCSV_try(vols);
    }

    private static void ecrireCSV_try(List<Map<String, String>> vols) {

        try (FileWriter fch = new FileWriter("nv_vols.csv")) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(new String[]{"jour", "de", "à", "id", "prévu à"})
                    .build();

            CSVPrinter printer = null;
            printer = new CSVPrinter(fch, csvFormat);
            for (var vol : vols) {
                printer.printRecord(
                        vol.get("Date"),
                        vol.get("Dép"),
                        vol.get("Arriv"),
                        vol.get("Code"),
                        vol.get("Heure")
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Problème...",e);
        }
        // "Close" automatique...
    }
    public static List<Map<String, String>> lireCSV() throws IOException {

        Reader in = new FileReader("./vol.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(new String[]{"Code", "Dép", "Arriv", "Date", "Heure"})
                .setSkipHeaderRecord(true)
                .setDelimiter('|')
                .setIgnoreSurroundingSpaces(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        List<Map<String,String>> vols = new ArrayList<>();
        for (CSVRecord record : records) {
            Map<String,String> vol = new HashMap<>();
            vol.put("Code", record.get("Code"));
            vol.put("Dép", record.get("Dép"));
            vol.put("Arriv", record.get("Arriv"));
            vol.put("Date", record.get("Date"));
            vol.put("Heure", record.get("Heure"));
            vols.add(vol);
        }

        in.close();
        //System.out.println(vols);
        return vols;
    }

    private static void ecrireCSV_rapport(List<Map<String, String>> vols) {

        try (FileWriter fch = new FileWriter(projet.getNom()+" CSV")) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(new String[]{"nom", "date", "budget", "id", "prévu à"})
                    .build();

            CSVPrinter printer = null;
            printer = new CSVPrinter(fch, csvFormat);
            for (var vol : vols) {
                printer.printRecord(
                        vol.get("Date"),
                        vol.get("Dép"),
                        vol.get("Arriv"),
                        vol.get("Code"),
                        vol.get("Heure")
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Problème...",e);
        }
        // "Close" automatique...
    }


}
