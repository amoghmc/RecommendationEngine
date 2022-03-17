

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	private String movieFile;
	private String ratingFile;
	private ThirdRatings tr;
	private int minRatings;
	
	public MovieRunnerWithFilters() {
		this("ratedmoviesfull.csv", "ratings.csv", 1);
	}
	
	public MovieRunnerWithFilters(String movieFile, String ratingFile, int minRatings) {
		this.movieFile = movieFile;
		this.ratingFile = ratingFile;
		this.minRatings = minRatings;
		MovieDatabase.initialize(movieFile);
		tr = new ThirdRatings(this.ratingFile);
	}
	
	public void printAverageRatings() {
		System.out.println("Rater Size: \t" + tr.getRaterSize());
		
		System.out.println("Movie Size: \t" + MovieDatabase.size());

		ArrayList<Rating> ratings = tr.getAverageRatings(minRatings);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getTitle(r.getItem()));
		}

	}
	
	public void printAverageRatingsByYear(int year) {
		System.out.println("Rater Size: \t" + tr.getRaterSize());
		
		System.out.println("Movie Size: \t" + MovieDatabase.size());

		YearAfterFilter yf = new YearAfterFilter(year);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, yf);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}
	}
		
		public void printAverageRatingsByGenre(String genre) {
			System.out.println("Rater Size: \t" + tr.getRaterSize());
			
			MovieDatabase.initialize(movieFile);
			System.out.println("Movie Size: \t" + MovieDatabase.size());
			
			
			GenreFilter gf = new GenreFilter(genre);
			ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, gf);
			System.out.println("Movies Found: \t" + ratings.size());
			Collections.sort(ratings);
			for (Rating r : ratings) {
				System.out.println(r.getValue() + "\t "+ MovieDatabase.getTitle(r.getItem()));
				System.out.println("\t" + MovieDatabase.getGenres(r.getItem())); 
			}

	}
		
		public void printAverageRatingsByMinutes(int min, int max) {
			System.out.println("Rater Size: \t" + tr.getRaterSize());
			
			MovieDatabase.initialize(movieFile);
			System.out.println("Movie Size: \t" + MovieDatabase.size());
			
			
			MinutesFilter mf = new MinutesFilter(min, max);
			ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, mf);
			System.out.println("Movies Found: \t" + ratings.size());
			Collections.sort(ratings);
			for (Rating r : ratings) {
				System.out.println(r.getValue() + "\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			}

	}

	public void printAverageRatingsByDirectors(String director) {
			System.out.println("Rater Size: \t" + tr.getRaterSize());
			
			MovieDatabase.initialize(movieFile);
			System.out.println("Movie Size: \t" + MovieDatabase.size());
			
			
			DirectorsFilter df = new DirectorsFilter(director);
			ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, df);
			System.out.println("Movies Found: \t" + ratings.size());
			Collections.sort(ratings);
			for (Rating r : ratings) {
				System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
				System.out.println("\t" + MovieDatabase.getDirector(r.getItem())); 
			}

	}
	
	public void printAverageRatingsByYearAfterAndGenre(int year, String genre) {
		System.out.println("Rater Size: \t" + tr.getRaterSize());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		AllFilters af = new AllFilters();
		GenreFilter gf = new GenreFilter(genre);
		YearAfterFilter yf = new YearAfterFilter(year);
		
		af.addFilter(gf);
		af.addFilter(yf);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem())); 
		}
	}
	
	public void printAverageRatingsByDirectorsAndMinutes(String directors, int min, int max) {
		System.out.println("Rater Size: \t" + tr.getRaterSize());
		
		MovieDatabase.initialize(movieFile);
		System.out.println("Movie Size: \t" + MovieDatabase.size());
		
		
		AllFilters af = new AllFilters();
		DirectorsFilter df = new DirectorsFilter(directors);
		MinutesFilter mf = new MinutesFilter(min, max);
		
		af.addFilter(df);
		af.addFilter(mf);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minRatings, af);
		System.out.println("Movies Found: \t" + ratings.size());
		Collections.sort(ratings, Collections.reverseOrder());
		for (Rating r : ratings) {
			System.out.println(r.getValue() + "\t " + "Time: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
	}

		
}
