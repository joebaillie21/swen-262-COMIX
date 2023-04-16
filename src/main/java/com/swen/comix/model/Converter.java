package com.swen.comix.model;

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

    public Converter(FileType toType, FileType fromType){
        this.toType = toType;
        this.fromType = fromType;
    }

    /**
     * this calls the appropriate importer based on switch statement
     * and it also calls the appropriate exporter based on switch statement
     * convert then after returns the filepath of the new exported file
     * 
     * @param fileName
     * @return
     */
    public String convertFileToFile(String fileName) {

        ArrayList<ComicBook> asJava = convertFileToJava(fileName);
        String filePath = convertJavaToFile(asJava);

        return filePath;

    }

    public ArrayList<ComicBook> convertFileToJava(String fileName) {

        ArrayList<ComicBook> comics = new ArrayList<>();

        // Importer import = new ImportFromCSV(fileName);

        switch (fromType) {

            case XML: {
                // Importer import = new ImportFromXML(fileName);
                // comics = import.toArrayList();
            }

            case JSON: {

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
        // comics = import.toArrayList();
        return comics;
    }

    public String convertJavaToFile(ArrayList<ComicBook> comics) {

        // Exporter export;
        String filePath = "";

        switch (toType) {

            case XML: {
                // export = new ExportAsXML(comics)
            }

            case JSON: {
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
