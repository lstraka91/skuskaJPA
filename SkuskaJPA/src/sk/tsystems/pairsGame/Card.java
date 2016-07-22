package sk.tsystems.pairsGame;

public class Card {
	 private int id;
	    private boolean matched = false;


	    public void setId(int id){
	        this.id = id;
	    }

	    public int getId(){
	        return this.id;
	    }

	    public void setMatched(boolean matched){
	        this.matched = matched;
	    }

	    public boolean getMatched(){
	        return this.matched;
	    }
	
}
