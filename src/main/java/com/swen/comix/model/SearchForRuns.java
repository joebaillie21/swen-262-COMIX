package com.swen.comix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SearchForRuns implements SearchStrategy {

    private PersonalCollection personalCollection; 
    final int MIN_CONSEC_ISSUES = 12; 
    final int TITLE = 0, VOL_NUM = 1; 
    ArrayList<ComicBook> runs = new ArrayList<>(); 
    public SearchForRuns(PersonalCollection personalCollection){
        this.personalCollection = personalCollection; 
        //Collections.copy(runs, (ArrayList<ComicBook>)personalCollection.getPersonalCollection()); COMMENT OUT AFTER TESTS

        /* POPULATE TEST DATA IN RUNS
         * should contain 6 books 
         */
        Publisher p1 = new Publisher("meo");
        Publisher p2 = new Publisher("nana"); 
        String title1 = "The Amazing Spider-man"; 
        String title2 = "Madoka Majika"; 
        String pubdate = "1-2-03"; 
        ArrayList<Author> author = new ArrayList<>(); 
        author.add(new Author("lalo"));
        author.add(new Author("papa"));  
        ComicBook cb1 = new ComicBookComponent(p1, title1, 1, 2, pubdate, author, null,  "example"  );
        ComicBook cb2 = new ComicBookComponent(p2, title2, 1, 1, pubdate, author, null,  "example"  );
        ComicBook cb3 = new ComicBookComponent(p1, title1, 4, 2, pubdate, author, null,  "example"  );
        ComicBook cb4 = new ComicBookComponent(p2, title2, 3, 1, pubdate, author, null,  "example"  );
        ComicBook cb5 = new ComicBookComponent(p1, title1, 1, 3, pubdate, author, null,  "example"  );
        ComicBook cb6 = new ComicBookComponent(p2, title2, 3, 3, pubdate, author, null,  "example"  );
        
        runs.add(cb1);
        runs.add(cb2);
        runs.add(cb3);
        runs.add(cb4);
        runs.add(cb5); 
        runs.add(cb6); 
        
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 

        if(isSearchDb == false){
            comics = searchOnPC();
        }
        return comics; 
    }
    
    private ArrayList<ComicBook> searchOnPC(){
        sort(); 
    
        ArrayList<ArrayList<ComicBook>> sortedByTitle = splitBySeries(); 
        ArrayList<ArrayList<ComicBook>> sortedByVolNum = splitByVolNum(sortedByTitle); 
        ArrayList<ArrayList<ComicBook>> sortedByRuns = splitByIssueNum(sortedByVolNum);

        /**
         * Not sure how to count the amount of runs other than seeing what the title was or something and then counting if it was a run that way? 
         */

        
        ArrayList<ComicBook> CC = new ArrayList<>(); 
        

        return runs; 

    }

    public ArrayList<ComicBook> getRuns(){
        return runs; 
    }
    public ArrayList<ComicBook> sort(){
        Comparator<ComicBook> bySeriesTitle = (book1, book2) -> book1.getSeriesTitle().compareTo(book2.getSeriesTitle());
        Comparator<ComicBook> byVolumeNum = (book1, book2) -> Integer.compare(book1.getVolNum(), book2.getVolNum());
        Comparator<ComicBook> byIssueNum = (book1, book2) -> Integer.compare(book1.getIssueNum(), book2.getIssueNum());

        Comparator<ComicBook> chain = bySeriesTitle
            .thenComparing(byVolumeNum)
            .thenComparing(byIssueNum); 
        
        Collections.sort(runs,chain);
        return runs;

        // write tests that test that this change persists in the object 

    }

    public ArrayList<ArrayList<ComicBook>> splitBySeries(){
        ArrayList<ArrayList<ComicBook>> series = new ArrayList<>(); 
        ArrayList<ComicBook> serie = new ArrayList<>(); 

        String title = runs.get(0).getSeriesTitle(); 
        for(int i = 0 ; i < runs.size(); i++){
            
            if(title.equals(runs.get(i).getSeriesTitle())){
                serie.add(runs.get(i));
        
            }else if(!(title.equals(runs.get(i).getSeriesTitle()))){
                System.out.println(runs.get(i).getSeriesTitle());
                series.add(serie); 
                serie = new ArrayList<>(); 
                serie.add(runs.get(i));
                title = runs.get(i).getSeriesTitle();
            }
        }

        series.add(serie);
        return series; 
    }

    public ArrayList<ArrayList<ComicBook>> splitByVolNum(ArrayList<ArrayList<ComicBook>> sortedByTitle){
        ArrayList<ArrayList<ComicBook>> volNums = new ArrayList<>(); 
        ArrayList<ComicBook> vol = new ArrayList<>(); 

        for(int i = 0; i < sortedByTitle.size(); i ++){
            int volNum = sortedByTitle.get(i).get(0).getVolNum();
            for( int k = 0; k < sortedByTitle.get(i).size(); k++){
                ComicBook cb = sortedByTitle.get(i).get(k);
                if(volNum == cb.getVolNum()){
                    vol.add(cb);
                }else if(volNum != cb.getVolNum()){
                    volNums.add(vol);
                    vol = new ArrayList<>(); 
                    vol.add(cb);
                    volNum = cb.getVolNum();
                }
            }
        }
        return volNums; 
    }

    public ArrayList<ArrayList<ComicBook>> splitByIssueNum(ArrayList<ArrayList<ComicBook>> sortedByVolNum){
        ArrayList<ArrayList<ComicBook>> consecutiveNums = new ArrayList<>(); 
        ArrayList<ComicBook> issues = new ArrayList<>(); 

        
        for(int i = 0; i < sortedByVolNum.size(); i++){
            int issueNum = sortedByVolNum.get(i).get(0).getIssueNum();
            int count = 0;  
            for(int k = 0; k < sortedByVolNum.get(i).size(); k++){ //basic
                ComicBook cb = sortedByVolNum.get(i).get(k); 
                if(issueNum + 1 == cb.getIssueNum()){
                    count++; 
                    issues.add(cb);
                    issueNum = sortedByVolNum.get(i).get(k).getIssueNum();
                }else if(issueNum + 1 != cb.getIssueNum()){
                    issues = new ArrayList<>();
                    break; 
                }
            }

            if(issues.size() != 0 && count >= MIN_CONSEC_ISSUES){ // if there are issues in there meaning its consec as well as the count is great 
                consecutiveNums.add(issues); // TO DO: smush them all together into one array list  
            }
            issues = new ArrayList<>(); // reset 
        }

        return consecutiveNums; 
    }


    //These are tests for the private methods 

    public static void main(String [] args) {
        /* POPULATE TEST DATA IN RUNS
         * should contain 6 books 
         */

        PersonalCollection pc = new PersonalCollection("meow"); 

        Publisher p1 = new Publisher("meo");
        Publisher p2 = new Publisher("nana"); 
        String title1 = "The Amazing Spider-man"; 
        String title2 = "Madoka Majika"; 
        String pubdate = "1-2-03"; 
        ArrayList<Author> author = new ArrayList<>(); 
        author.add(new Author("lalo"));
        author.add(new Author("papa"));  
        ComicBook cb1 = new ComicBookComponent(p1, title1, 1, 2, pubdate, author, null,  "example"  );
        ComicBook cb2 = new ComicBookComponent(p2, title2, 1, 1, pubdate, author, null,  "example"  );
        ComicBook cb3 = new ComicBookComponent(p1, title1, 4, 2, pubdate, author, null,  "example"  );
        ComicBook cb4 = new ComicBookComponent(p2, title2, 3, 1, pubdate, author, null,  "example"  );
        ComicBook cb5 = new ComicBookComponent(p1, title1, 1, 3, pubdate, author, null,  "example"  );
        ComicBook cb6 = new ComicBookComponent(p2, title2, 3, 3, pubdate, author, null,  "example"  );
        
        pc.add(cb1);
        pc.add(cb2);
        pc.add(cb3);
        pc.add(cb4);
        pc.add(cb5); 
        pc.add(cb6); 
        
        SearchForRuns sfr = new SearchForRuns(pc);
        
        ArrayList<ComicBook> sorted = sfr.sort();
        ArrayList<ArrayList<ComicBook>> splitSeries = sfr.splitBySeries();

        for(int i = 0; i < splitSeries.size(); i++){ // this is supposed to have the other series show up as well 
            System.out.println(i + " series"); 
            for(int k = 0; k < splitSeries.get(i).size(); k++){
                System.out.println(splitSeries.get(i).get(k).toString());
            }

        }
        
    }
}
