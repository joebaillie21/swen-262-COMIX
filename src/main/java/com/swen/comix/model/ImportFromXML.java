package com.swen.comix.model;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ImportFromXML implements Importer{

    private String fileName;
    private ArrayList<ComicBookComponent> comicBooks;

    public ImportFromXML(String fileName){
        this.fileName = fileName;
    }
    
    public ArrayList<ComicBookComponent> importToJava(){
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document;
            try {
                document = builder.parse(new File(this.fileName));
                NodeList cbNodes = ((org.w3c.dom.Document) document).getElementsByTagName("book");

        for (int i = 0; i < cbNodes.getLength(); i++) {

            Node cbNode = cbNodes.item(i);

            if (cbNode.getNodeType() == Node.ELEMENT_NODE) {
                Element cbElement = (Element) cbNode;
                String publisher = cbElement.getElementsByTagName("publisher").item(0).getTextContent();
                String seriesTitle = cbElement.getElementsByTagName("seriesTitle").item(0).getTextContent();
                String volNumber = cbElement.getElementsByTagName("volNumber").item(0).getTextContent();
                String issueNumber = cbElement.getElementsByTagName("issueNumber").item(0).getTextContent();
                String publicationDate = cbElement.getElementsByTagName("publicationDate").item(0).getTextContent();
                String authors = cbElement.getElementsByTagName("authors").item(0).getTextContent();
                String principleCharacters = cbElement.getElementsByTagName("principleCharacters").item(0).getTextContent();
                String description = cbElement.getElementsByTagName("description").item(0).getTextContent();

                Publisher pub = new Publisher(publisher);

                String[] splited = principleCharacters.split(" ");
                ArrayList<String> pc = new ArrayList<>();
                
                for (int j = 0; j < splited.length - 1; i++){
                    pc.add(splited[i]);
                }

                String[] splitedAuthors = authors.split(" ");
                ArrayList<Author> auth = new ArrayList<>();

                for (int j = 0; j < splitedAuthors.length - 1; i++){
                    Author a = new Author(splitedAuthors[i]);
                    auth.add(a);
                }

                ComicBookComponent book = new ComicBookComponent(pub, seriesTitle, volNumber, issueNumber, publicationDate, auth, pc, description);
                comicBooks.add(book);
            }
        }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        return comicBooks;
    }
}



