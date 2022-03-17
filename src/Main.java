

public class Main {

	public static void main(String[] args) {
		/*
		long t1 = System.currentTimeMillis();
		FirstRatings fr = new FirstRatings();
		fr.testLoadRaters();
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
		System.out.println(System.currentTimeMillis() - t1);
		*/
		
		//MovieRunnerWithFilters mrf = new MovieRunnerWithFilters(movieFile, ratingFile, minRatings);
		
		
		//mrf.printAverageRatings();
		//mrf.printAverageRatingsByYear(year);
		//mrf.printAverageRatingsByGenre(genre);
		//mrf.printAverageRatingsByMinutes(min, max);
		//mrf.printAverageRatingsByDirectors(directors);
		//mrf.printAverageRatingsByYearAfterAndGenre(year, genre);
		//mrf.printAverageRatingsByDirectorsAndMinutes(directors, min, max);
		int minRatings = 5;
		String movieFile = "ratedmoviesfull.csv";
		String ratingFile = "ratings.csv";
		MovieRunnerSimilarRatings mrs = new MovieRunnerSimilarRatings(movieFile, ratingFile, minRatings);
		RecommendationRunner rr = new RecommendationRunner();
		
		String id = "314";
		int numSimilar = 10;	
		int year = 1990;
		String genre = "Action";
		int min = 70;
		int max = 200;
		String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
		
		//mrs.printAverageRatings();
		//mrs.printAverageRatingsByYear(year);
		//mrs.printAverageRatingsByGenre(genre);
		//mrs.printAverageRatingsByMinutes(min, max);
		//mrs.printAverageRatingsByDirectors(directors);
		//mrs.printAverageRatingsByYearAfterAndGenre(year, genre);
		//mrs.printAverageRatingsByDirectorsAndMinutes(directors, min, max);
		//mrs.printSimilarRatings(id, numSimilar);
		//mrs.printSimilarRatingsByGenre(id, numSimilar, genre);
		//mrs.printSimilarRatingsByDirector(id, numSimilar, directors);
		//mrs.printSimilarRatingsByGenreAndMinutes(id, numSimilar, genre, min, max);
		//mrs.printSimilarRatingsByYearAfterAndMinutes(id, numSimilar, year, min, max);
		rr.printRecommendationsFor(id);
	}
	
}
