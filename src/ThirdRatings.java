

/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
	private ArrayList<Rater> myRaters;

	public ThirdRatings() {
		// default constructor
		this("ratings.csv");
	}

	public ThirdRatings(String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsFile);
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
	
	private double getAverageByID(String id, int minimalRaters) {
		double avg = 0;
		double sum = 0;
		int count = 0;
		for (Rater r : myRaters) {
			if (r.hasRating(id)) {
				sum += r.getRating(id);
				count++;
			}
		}
		
		if (count >= minimalRaters) {
			avg = sum / count;
		}
		return avg;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
		
		for (String id : myMovies) {
			double avg = getAverageByID(id, minimalRaters);
			if (avg > 0) {
				Rating rating = new Rating(id, avg);
				ratings.add(rating);
			}
		}
		return ratings;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
		
		for (String id : myMovies) {
			double avg = getAverageByID(id, minimalRaters);
			if (avg > 0) {
				Rating rating = new Rating(id, avg);
				ratings.add(rating);
			}
		}
		return ratings;
	}

}

