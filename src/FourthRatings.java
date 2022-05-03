
/**
 * @author Amogh M C
 * @version 4.0
 */

import java.util.*;

public class FourthRatings {

	private double getAverageByID(String id, int minimalRaters) {
		double avg = 0;
		double sum = 0;
		int count = 0;

		for (Rater r : RaterDatabase.getRaters()) {
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

	private double dotProduct(Rater me, Rater r) {
		ArrayList<String> meItems = me.getItemsRated();
		ArrayList<String> rItems = r.getItemsRated();
		ArrayList<String> commonItems = new ArrayList<String>();

		for (String s : meItems) {
			if (rItems.contains(s)) {
				commonItems.add(s);
			}
		}

		double result = 0;
		for (String s : commonItems) {
			double meRating = me.getRating(s) - 5;
			double rRating = r.getRating(s) - 5;
			result += meRating * rRating;
		}
		return result;
	}

	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		Rater me = RaterDatabase.getRater(id);

		for (Rater r : RaterDatabase.getRaters()) {
			String raterID = r.getID();
			if (!raterID.equals(id)) {
				double dotProductResult = dotProduct(me, r);
				if (dotProductResult > 0) {
					Rating rRating = new Rating(raterID, dotProductResult);
					list.add(rRating);
				}
			}
		}

		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
			Filter filterCriteria) {
		ArrayList<Rating> raterSimilarities = getSimilarities(id);
		ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> similarRatings = new ArrayList<Rating>();

		int size = raterSimilarities.size();
		if (size < numSimilarRaters) {
			numSimilarRaters = size;
		}

		for (String m : myMovies) {
			int count = 0;
			double sum = 0;
			double weightedAvg = 0;

			for (int i = 0; i < numSimilarRaters; i++) {
				Rating similarRater = raterSimilarities.get(i);
				Rater rater = RaterDatabase.getRater(similarRater.getItem());
				double raterSimilarityValue = similarRater.getValue();

				if (rater.hasRating(m)) {
					sum += rater.getRating(m) * raterSimilarityValue;
					count++;
				}
			}

			if (count >= minimalRaters) {
				weightedAvg = sum / count;
				Rating rating = new Rating(m, weightedAvg);
				similarRatings.add(rating);
			}
		}
		Collections.sort(similarRatings, Collections.reverseOrder());
		return similarRatings;
	}

}
