package com.swen.comix.model;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExportAsXML implements Exporter {

    private ArrayList<ComicBookComponent> comics;

    public ExportAsXML(ArrayList<ComicBookComponent> comic){
        this.comics = comic;
    }

    @Override
    public String toFile() throws IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("comics");
            document.appendChild(rootElement);

            for (ComicBookComponent comic : this.comics) {

                Element comicElement = document.createElement("comic");
                rootElement.appendChild(comicElement);

                Element publisherElement = document.createElement("publisher");
                publisherElement.appendChild(document.createTextNode(comic.getPublisher().getName()));
                comicElement.appendChild(publisherElement); 

                Element seriesTitleElement = document.createElement("seriesTitle");
                seriesTitleElement.appendChild(document.createTextNode(comic.getSeriesTitle()));
                comicElement.appendChild(seriesTitleElement);

                Element volNumberElement = document.createElement("volNumber");
                volNumberElement.appendChild(document.createTextNode(comic.getVolNum()));
                comicElement.appendChild(volNumberElement); 

                Element issueNumberElement = document.createElement("issueNumber");
                issueNumberElement.appendChild(document.createTextNode(comic.getIssueNumber()));
                comicElement.appendChild(issueNumberElement);
            
                Element publicationDateElement = document.createElement("publicationDate");
                publicationDateElement.appendChild(document.createTextNode(comic.getPublicationDate()));
                comicElement.appendChild(publicationDateElement); 

                Element authorsElement = document.createElement("authors");

                for (Author author : comic.getAuthors()){
                    authorsElement.appendChild(document.createTextNode(author.getName()));
                }

                comicElement.appendChild(authorsElement); 

                Element principleCharactersElement = document.createElement("principleCharacters");

                for (String character : comic.getPrincipleCharacters()){
                    principleCharactersElement.appendChild(document.createTextNode(character));
                }

                comicElement.appendChild(principleCharactersElement); 

                Element descriptionElement = document.createElement("description");
                descriptionElement.appendChild(document.createTextNode(comic.getDescription()));
                comicElement.appendChild(descriptionElement); 
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
        
            transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("newXMLFile.xml"));

            try {
                transformer.transform(domSource, streamResult);
            } catch (TransformerException e) {
                e.printStackTrace();
            }

        } catch (TransformerConfigurationException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
