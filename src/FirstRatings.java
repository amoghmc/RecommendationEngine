
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
	public ArrayList<Movie> loadMovies(String filename) {
		FileResource fr = new FileResource(filename);
		CSVParser cp = fr.getCSVParser();
		ArrayList<Movie> arm = new ArrayList<Movie>();
		for (CSVRecord rec : cp) {
			String id = rec.get("id");
			String title = rec.get("title");
			String year = rec.get("year");
			String genre = rec.get("genre");
			String director = rec.get("director");
			String country = rec.get("country");
			int minutes = Integer.parseInt(rec.get("minutes"));
			String poster = rec.get("poster");
			Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
			arm.add(m);
		}
		return arm;
	}

	public void testLoadMovies() {
		String file = "data/ratedmoviesfull.csv";
		ArrayList<Movie> arm = loadMovies(file);
		System.out.println("Total size: " + arm.size());

		for (Movie m : arm) {
			System.out.println(m);
		}

		int comedy = 0;
		for (Movie m : arm) {
			String gen = m.getGenres();
			if (gen.indexOf("Comedy") != -1) {
				comedy++;
			}
		}
		System.out.println("Comedy Movies: " + comedy);

		int gtr = 0;
		for (Movie m : arm) {
			int min = m.getMinutes();
			if (min > 150) {
				gtr++;
			}
		}
		System.out.println("Greater than 150: " + gtr);

		HashMap<String, Integer> dirlist = new HashMap<String, Integer>();
		for (Movie m : arm) {
			String dir = m.getDirector();
			String[] dir_arr = dir.split(", ");

			for (String s : dir_arr) {
				if (!dirlist.containsKey(s)) {
					dirlist.put(s, 1);
				} else {
					int val = dirlist.get(s) + 1;
					dirlist.put(s, val);
				}
			}
		}

		int max = 0;
		for (String s : dirlist.keySet()) {
			// process each key in turn
			int valr = dirlist.get(s);
			// System.out.println(s + dirlist.get(s));
			if (valr > max) {
				max = valr;
			}
		}
		System.out.println("Max movies by a director: " + max);
		System.out.println("Directors with max movies: ");
		for (String s : dirlist.keySet()) {
			// process each key in turn
			int valr = dirlist.get(s);
			if (valr == max) {
				System.out.println(s);
			}
		}

		return;
	}

	public ArrayList<Rater> loadRaters(String filename) {
		FileResource fr = new FileResource("data/" + filename);
		CSVParser cp = fr.getCSVParser();
		ArrayList<Rater> raters = new ArrayList<Rater>();
		ArrayList<String> ids = new ArrayList<String>();

		for (CSVRecord rec : cp) {
			String rater_id = rec.get("rater_id");
			String movie_id = rec.get("movie_id");
			String rating = rec.get("rating");
			// String time = rec.get("time");

			// if rater does not exist
			if (!ids.contains(rater_id)) {
				ids.add(rater_id);
				EfficientRater rtr = new EfficientRater(rater_id);  
				rtr.addRating(movie_id, Double.parseDouble(rating));
				raters.add(rtr);
			}
			// if rater exists
			else {
				for (Rater r : raters) {
					if (r.getID().equals(rater_id)) {
						r.addRating(movie_id, Double.parseDouble(rating));
					}
				}
			}
		}
		return raters;
	}

	public void testLoadRaters() {
		ArrayList<Rater> raters = loadRaters("data/ratings.csv");
		System.out.println("Total size: " + raters.size());
		
		String rtr_id = "193";
		for (Rater r : raters) {
			if (rtr_id.equals(r.getID())) {
				System.out.println("Total num of ratings by " + rtr_id + ": " + r.numRatings());
			}
		}
		
		int max = 0;
		ArrayList<String> max_rtrs = new ArrayList<String>();
		for (Rater r : raters) {
			if (r.numRatings() > max) {
				max = r.numRatings();
			}
		}
		for (Rater r : raters) {
			if (r.numRatings() == max) {
				max_rtrs.add(r.getID());
			}
		}
		System.out.println("Max ratings: " + max);
		System.out.println("No of Raters with max ratings: " + max_rtrs.size());
		System.out.println("Rater IDs with max ratings: ");
		for (String s : max_rtrs) {
			System.out.println(s);
		}
		
		int numRatingsMovie = 0;
		String movie = "1798709";
		for (Rater r : raters) {
			if (r.hasRating(movie)) {
				numRatingsMovie++;
			}
		}
		System.out.println("Num of ratings for " + movie + ": " + numRatingsMovie);
		
		ArrayList<String> movies = new ArrayList<String>();
		for (Rater r : raters) {
			ArrayList<String> temp = r.getItemsRated();
			for (String m : temp) {
				if (!movies.contains(m)) {
					movies.add(m);
				}
			}
		}
		System.out.println("Total movies rated: " + movies.size());
	}

}