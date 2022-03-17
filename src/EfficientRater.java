

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
	private String myID;
	private HashMap<String, Rating> myRatings;

	public EfficientRater(String id) {
		myID = id;
		myRatings = new HashMap<String, Rating>();
	}

	@Override
	public void addRating(String item, double rating) {
		myRatings.put(item, new Rating(item, rating));
	}

	@Override
	public boolean hasRating(String item) {
		return myRatings.containsKey(item);
	}

	@Override
	public String getID() {
		return myID;
	}

	@Override
	public double getRating(String item) {
		if (myRatings.get(item) == null) {
			return -1;
		}
		else {
			return myRatings.get(item).getValue();
		}
	}

	@Override
	public int numRatings() {
		return myRatings.size();
	}

	@Override
	public ArrayList<String> getItemsRated() {	
		ArrayList<String> list = new ArrayList<String>();		
		for (String s : myRatings.keySet()) {
			list.add(s);
		}
		
		return list;
	}
}
