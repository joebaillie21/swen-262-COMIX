package model;
public class AddAction implements Action {
    private PersonalCollection pc ;

    public AddAction(PersonalCollection pc){
        this.pc = pc; 
    }
    @Override
    public void execute(ComicBook comic) {
        pc.add((ComicBookComponent)comic); 
    }

    @Override
    public boolean isReversible() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isReversible'");
    }

    @Override
    public void unexecute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unexecute'");
    }
    
}
