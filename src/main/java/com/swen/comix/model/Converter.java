package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @author Joe
 *         Joe is doing this not really sure how this works and am just writing
 *         out what was on the board yesterday
 */
public class Converter {
    private FileType toType, fromType;
    private boolean db;

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

        return "";

    }

    public ArrayList<ComicBook> convertFileToJava(String fileName) {

        ArrayList<ComicBook> comics = new ArrayList<>();
        // Importer import;

        switch (fromType) {

            case XML: {
                // import = new ImportFromXML(fileName)
            }

            case JSON: {
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

    public ArrayList<ComicBookComponent> convertFileToJava(){
        //switch based on from type (the toType will be java if this method is called)
            //return the new java object which will replace the caller's personal colelction
        return null;
    }
}
