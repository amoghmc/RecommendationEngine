
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	private String movieFile;
	private String ratingFile;
	private FourthRatings fratings;
	private int minRatings;
	
	public MovieRunnerSimilarRatings() {
		this("ratedmoviesfull.csv", "ratings.csv", 1);
	}

	public MovieRunnerSimilarRatings(String movieFile, String ratingFile, int minRatings) {
		this.movieFile = movieFile;
		this.ratingFile = ratingFile;
		this.minRatings = minRatings;
		MovieDatabase.initialize(movieFile);
		RaterDatabase.initialize(ratingFile);
		fratings = new FourthRatings();
	} 
	
	public void printAverageRatings() {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		System.out.println("Movie Size: \t" + MovieDatabase.size());

		ArrayList<Rating> ratings = fratings.getAverageRatings(minRatings);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getTitle(r.getItem()));
		}

	}
	
	public void printAverageRatingsByYear(int year) {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		System.out.println("Movie Size: \t" + MovieDatabase.size());

		YearAfterFilter yf = new YearAfterFilter(year);
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, yf);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}
	}
		
	public void printAverageRatingsByGenre(String genre) {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		GenreFilter gf = new GenreFilter(genre);
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, gf);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t "+ MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem())); 
		}

	}
		
	public void printAverageRatingsByMinutes(int min, int max) {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		MinutesFilter mf = new MinutesFilter(min, max);
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, mf);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}

	}

	public void printAverageRatingsByDirectors(String director) {
			System.out.println("Rater Size: \t" + RaterDatabase.size());
			
			MovieDatabase.initialize(movieFile);
			System.out.println("Movie Size: \t" + MovieDatabase.size());
			
			
			DirectorsFilter df = new DirectorsFilter(director);
			ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, df);
			System.out.println("Movies Found: \t" + ratings.size());
			Collections.sort(ratings);
			for (Rating r : ratings) {
				System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
				System.out.println("\t" + MovieDatabase.getDirector(r.getItem())); 
			}

	}
	
	public void printAverageRatingsByYearAfterAndGenre(int year, String genre) {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		AllFilters af = new AllFilters();
		GenreFilter gf = new GenreFilter(genre);
		YearAfterFilter yf = new YearAfterFilter(year);
		
		af.addFilter(gf);
		af.addFilter(yf);
		
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()) + "\t" + r.getItem()); 
		}
	}
	
	public void printAverageRatingsByDirectorsAndMinutes(String directors, int min, int max) {
		System.out.println("Rater Size: \t" + RaterDatabase.size());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		AllFilters af = new AllFilters();
		DirectorsFilter df = new DirectorsFilter(directors);
		MinutesFilter mf = new MinutesFilter(min, max);
		
		af.addFilter(df);
		af.addFilter(mf);
		
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings, Collections.reverseOrder());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
	}
	
	public void printSimilarRatings(String id, int numSimilarRaters) {
		ArrayList<Rating> ratings = fratings.getSimilarRatings(id, numSimilarRaters, minRatings);
		System.out.println("Movies Found: \t" + ratings.size());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	public void printSimilarRatingsByGenre(String id, int numSimilarRaters, String genre) {
		Filter gf = new GenreFilter(genre);
		ArrayList<Rating> ratings = fratings.getSimilarRatingsByFilter(id, numSimilarRaters, minRatings, gf);
		System.out.println("Movies Found: \t" + ratings.size());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t "+ MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem())); 
		}
	}

	public void printSimilarRatingsByDirector(String id, int numSimilarRaters, String director) {
		Filter df = new DirectorsFilter(director);
		ArrayList<Rating> ratings = fratings.getSimilarRatingsByFilter(id, numSimilarRaters, minRatings, df);
		System.out.println("Movies Found: \t" + ratings.size());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem())); 
		}
	}
	
	public void printSimilarRatingsByGenreAndMinutes(String id, int numSimilarRaters, String genre, int min, int max) {
		AllFilters af = new AllFilters();
		Filter gf = new GenreFilter(genre);
		MinutesFilter mf = new MinutesFilter(min, max);
		af.addFilter(gf);
		af.addFilter(mf);
		
		ArrayList<Rating> ratings = fratings.getSimilarRatingsByFilter(id, numSimilarRaters, minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem())); 
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes(String id, int numSimilarRaters, int year, int min, int max) {
		AllFilters af = new AllFilters();
		Filter yf = new YearAfterFilter(year);
		MinutesFilter mf = new MinutesFilter(min, max);
		af.addFilter(yf);
		af.addFilter(mf);
		
		ArrayList<Rating> ratings = fratings.getSimilarRatingsByFilter(id, numSimilarRaters, minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()));
		}
	}
	
}
