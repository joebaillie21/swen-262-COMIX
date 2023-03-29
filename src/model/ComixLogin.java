package model;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @Author Angela Ngo
 */
public class ComixLogin implements ComixMediator {
    private HashMap<String, ArrayList<Object>> userLogins; 
    final int PASSWORD = 1; 
    final int PERSONAL_COLLECTION = 2; 

    public ComixLogin(){
        userLogins = new HashMap<String, ArrayList<Object>>();
    }
    /**
     * String username - the key of the hashmap 
     * String password - the password of the user 
     * return isLoggedIn boolean 
     */
    @Override
    public boolean login(String username, String password) {
        // search through the local hashmap of user logins 
        boolean isLoggedIn = false; 
        for(String user : userLogins.keySet()){
            if(username.equals(user)){
                String givenPW = (String)userLogins.get(username).get(PASSWORD);
                if(givenPW.equals(password)){
                    isLoggedIn = true; 
                }
            }
        }
        return isLoggedIn; 
    }

    /**
     * Hypothetically this will put all the user logins into the hashmap 
     */
    public void updateUserLogins(){
        ObjectMapper objMapper = new ObjectMapper(); 
        ArrayList<Object> userInfo = new ArrayList<>(); 
        try{
            File file = new File("users.json"); 
            JsonNode rootNode = objMapper.readTree(file);
            String username = rootNode.get("username").asText(); 
            String password = rootNode.get("password").asText();

            try{ // raise json mapping exception
                PersonalCollection pc =  objMapper.readValue(file, PersonalCollection.class); 
                userInfo.add(password); 
                userInfo.add(pc);

            }catch(IOException f){
                f.printStackTrace();
            }

            userLogins.put(username, userInfo);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // need to be able to access the specific personal collection

    /**
     * this allows guest to get a specific personal collection from the 
     * hashmap that stores the users information and their personal collection
     * @param username - username of collection want to look through
     * @return personal collection of the specified user 
     */
    public PersonalCollection getSpecificPersonalCollection(String username){
        return (PersonalCollection) userLogins.get(username).get(PERSONAL_COLLECTION);
    }


    /**
     * This will add the user to the users.json and save the data 
     * @param user - A type of signed in user with a password 
     * @throws IOException - due to the use of file writer 
     */
    public void addUser(SignedInUser user) throws IOException{
        JSONObject userJson = new JSONObject(); 
        userJson.put("username", user.getName()); 
        userJson.put("password", user.getPassword());
        JSONObject personalCollection = loadPersonalCollectionToJson(user.getPersonalCollection().getPersonalCollection());
        userJson.put("personal_collection", personalCollection);

        try(FileWriter file = new FileWriter("users.json")){
            file.write(userJson.toString()); // writes it into the json file 
        }catch(IOException e){
            System.out.println("Theres an error with the file: users.json");
        }
        
    }

    /**
     * makes the personal collection (in form of arraylist) into 
     * @param pc
     * @return
     */
    private JSONObject loadPersonalCollectionToJson(ArrayList<ComicBookComponent> pc){
        JSONObject personalCollection = new JSONObject(); 
        for(int i = 0; i < pc.size() ; i++){
            ComicBookComponent comic = pc.get(i); 
            JSONObject comicJsonObj = createComicJsonEntry(comic);
            personalCollection.put(""+ i, comicJsonObj);
        }
        return personalCollection; 
    }

    /**
     * This is a private method that will help with creating a comic json with 
     * all the books information 
     * @param comic
     * @return
     */
    private JSONObject createComicJsonEntry(ComicBookComponent comic){
        JSONObject comicEntry = new JSONObject(); 

        comicEntry.put("series_title", comic.getSeriesTitle());
        comicEntry.put("publisher", comic.getPublisher());
        comicEntry.put("author", comic.getAuthor());
        comicEntry.put("volume_number", comic.getVolNum());
        comicEntry.put("issue_number", comic.getIssueNum());
        comicEntry.put("publication_date", comic.getPubDate());
        comicEntry.put("description", comic.getDescription());
        comicEntry.put("signatures", comic.getSignature());

        // making the principle character json list 
        JSONObject principleCharacter = new JSONObject();
        ArrayList<String> principleCharacterList = comic.getPrincipleCharacter(); 
        for(int k = 0; k < principleCharacterList.size(); k++){
            principleCharacter.put("" + k , principleCharacterList.get(k)); 
        }
        comicEntry.put("principle_characters", principleCharacter); 


        // adding in the "decorated attributes" to the personal collection 
        if(comic.getGrade() != -1 ){comicEntry.put("grade", comic.getGrade());}
        if(comic.getValue() != -1){comicEntry.put("value", comic.getValue());}

        return comicEntry; 
    }
    
}
