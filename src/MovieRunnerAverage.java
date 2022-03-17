

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	private String movieFile;
	private String ratingFile;
	private SecondRatings sr;
	
	public MovieRunnerAverage() {
		movieFile = "ratedmoviesfull.csv";
		ratingFile = "ratings.csv";
		sr = new SecondRatings(movieFile, ratingFile);
	}
	public void printAverageRatings() {
		System.out.println("Movie Size: " + sr.getMovieSize());
		System.out.println("Rater Size: " + sr.getRaterSize());

		int minRatings = 12;
		ArrayList<Rating> ratings = sr.getAverageRatings(minRatings);
		Collections.sort(ratings);
		for (Rating r : ratings) {
			System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
		}

	}

	public void getAverageRatingOneMovie() {
		int minRatings = 2;
		String title = "Vacation";
		String id = sr.getID(title);

		if (id.equals("NO SUCH TITLE")) {
			System.out.println("No such title found!");
			return;
		}
		ArrayList<Rating> ratings = sr.getAverageRatings(minRatings);
		for (Rating r : ratings) {
			if (r.getItem().equals(id)) {
				System.out.println("Avg rating for 1 movie: ");
				System.out.println(title + " = " + r.getValue());
			}
		}
		return;
	}

}
