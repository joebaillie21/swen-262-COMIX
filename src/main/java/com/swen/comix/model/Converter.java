package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

import com.swen.comix.model.Importer;
import com.swen.comix.model.ImportFromXML;
import com.swen.comix.model.ImportFromCSV;
import com.swen.comix.model.ImportFromJson;
import com.swen.comix.model.Exporter;
import com.swen.comix.model.ExportAsCSV;
import com.swen.comix.model.ExportAsJson;
import com.swen.comix.model.ExportAsSQL;
import com.swen.comix.model.ExportAsXML;

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
     * @throws Exception
     */
    public String convertFileToFile(String fileName) throws Exception {

        ArrayList<ComicBookComponent> asJava = convertFileToJava(fileName);
        String filePath = convertJavaToFile(asJava);

        return filePath;

    }

    public ArrayList<ComicBookComponent> convertFileToJava(String fileName) throws Exception {

        ArrayList<ComicBookComponent> comics = new ArrayList<>();

        switch (fromType) {

            case XML: {
                ImportFromXML importer = new ImportFromXML(fileName);
                comics = importer.importToJava();
            }

            case JSON: {
                ImportFromJson importer = new ImportFromJson(fileName);
                comics = importer.importToJava();
            }

            case CSV: {
                ImportFromCSV importer = new ImportFromCSV(fileName);
                comics = importer.importToJava();
            }

            case JAVA: {
                System.out.println("Cannot convert java to java.");
                return null;
            }
            default:
                break;
        }

        return comics;
    }

    public String convertJavaToFile(ArrayList<ComicBookComponent> comics) throws Exception {

        String filePath = "";

        switch (toType) {

            case XML: {
                ExportAsXML exporter = new ExportAsXML(comics);
                filePath = exporter.toFile();
            }

            case JSON: {
                ExportAsJson exporter = new ExportAsJson(comics);
                filePath = exporter.toFile();
            }

            case CSV: {
                ExportAsCSV exporter = new ExportAsCSV(comics);
                filePath = exporter.toFile();
            }

            case SQL: {
                ExportAsSQL exporter = new ExportAsSQL(comics);
                filePath = exporter.toFile();
            }
            default:
                break;
        }

        return filePath;

    }

}
