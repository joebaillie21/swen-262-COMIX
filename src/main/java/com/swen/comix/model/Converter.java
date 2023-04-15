package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Holds a "from" type and a "to" type as strings
 * These are used to determine which importers and exporters are instatiated and
 * used
 * A converter is only made to transfer from one type to another
 * Different converters will need to be made at runtime to convert the different
 * file types
 * 
 * @author Joe
 * 
 */
public class Converter {
    private FileType toType, fromType;

    /**
     * this calls the appropriate importer based on switch statement
     * and it also calls the appropriate exporter based on switch statement
     * convert then after returns the filepath of the new exported file
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public String convertFileToFile(String fileName) throws IOException {

        ArrayList<ComicBookComponent> asJava = convertFileToJava(fileName);
        String filePath = convertJavaToFile(asJava);

        return "";

    }

    public ArrayList<ComicBookComponent> convertFileToJava(String fileName) throws IOException {

        ArrayList<ComicBookComponent> comics = new ArrayList<>();
        // Importer import;

        switch (fromType) {

            case XML: {
                // import = new ImportFromXML(fileName)
            }

            case JSON: {
                Importer importer = new ImportFromJson(fileName);
                comics = importer.importToJava();
                // import = new ImportFromJSON(fileName)
            }

            case CSV: {
                // import = new ImportFromCSV(fileName)
            }

            case JAVA: {
                System.out.println("Cannot convert java to java.");
                return null;
            }
            default:
                break;
        }
        // filePath = import.toArrayList();
        return comics;
    }

    public String convertJavaToFile(ArrayList<ComicBookComponent> comics) throws IOException {

        // Exporter export;
        String filePath = "";

        switch (toType) {

            case XML: {
                // export = new ExportAsXML(comics)
            }

            case JSON: {
                Exporter exporter = new ExportAsJson(comics);
                //return exporter.export(filePath);
                // export = new ExportAsJSON(comics)
            }

            case CSV: {
                // export = new ExportAsXML(comics)
            }

            case JAVA: {
                return comics.toString();
            }
            default:
                break;
        }
        // filePath = export.toFile();
        return filePath;

    }

}
