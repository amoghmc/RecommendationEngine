
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings() {
		// default constructor
		this("ratedmoviesfull.csv", "ratings.csv");
	}

	public SecondRatings(String movieFile, String ratingsFile) {
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(movieFile);
		myRaters = fr.loadRaters(ratingsFile);
	}
	
	public int getMovieSize() {
		return myMovies.size();
	}

	public int getRaterSize() {
		return myRaters.size();
	}
	
	private double getAverageByID(String id, int minimalRaters) {
		double avg = 0, sum = 0;
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
		for (Movie m : myMovies) {
			String id = m.getID();
			double avg = getAverageByID(id, minimalRaters);
			if (avg > 0) {
				Rating rating = new Rating(m.getID(), avg);
				ratings.add(rating);
			}
		}
		return ratings;
	}
	
	public String getTitle(String id) {
		String title = "N/A";
		for (Movie m : myMovies) {
			if (m.getID().equals(id)) {
				title = m.getTitle();
			}
		}
		return title;
	}
	
	public String getID(String title) {
		String id = "NO SUCH TITLE";
		for (Movie m : myMovies) {
			if (m.getTitle().equals(title)) {
				id = m.getID();
			}
		}
		return id;
	}
}

