
import java.util.ArrayList;
import java.util.Collections;

public class RecommendationRunner implements Recommender {
	private FourthRatings fratings;
	private int minRatings;

	public RecommendationRunner() {
		this(2);
	}

	public RecommendationRunner(int minRatings) {
		this.minRatings = minRatings;
		fratings = new FourthRatings();
	}

	@Override
	public ArrayList<String> getItemsToRate() {
		ArrayList<String> movies = new ArrayList<String>();
		YearAfterFilter yf = new YearAfterFilter(1990);
		ArrayList<Rating> ratings = fratings.getAverageRatingsByFilter(minRatings, yf);
		Collections.sort(ratings, Collections.reverseOrder());

		int count = 0;
		for (Rating r : ratings) {
			if (count > 10) {
				break;
			} else {
				movies.add(r.getItem());
				count++;
			}
		}
		return movies;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		Rater webRater = RaterDatabase.getRater(webRaterID);
		Filter yf = new YearAfterFilter(1990);
		int numSimilarRaters = 40;
		ArrayList<Rating> ratings = fratings.getSimilarRatingsByFilter(webRaterID, numSimilarRaters, minRatings, yf);
		
		ArrayList<Rating> filteredRatings = new ArrayList<Rating>();
		for (Rating r : ratings) {
			if (!webRater.hasRating(r.getItem())) {
				filteredRatings.add(r);
			}
		}
		
		if (filteredRatings.size() == 0) {
			System.out.println("<style>\r\n"
					+ ".alert {\r\n"
					+ "  padding: 20px;\r\n"
					+ "  background-color: #f44336;\r\n"
					+ "  color: white;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ ".closebtn {\r\n"
					+ "  margin-left: 15px;\r\n"
					+ "  color: white;\r\n"
					+ "  font-weight: bold;\r\n"
					+ "  float: right;\r\n"
					+ "  font-size: 22px;\r\n"
					+ "  line-height: 20px;\r\n"
					+ "  cursor: pointer;\r\n"
					+ "  transition: 0.3s;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ ".closebtn:hover {\r\n"
					+ "  color: black;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "</style>\r\n"
					+ "<div class=\"alert\">\r\n"
					+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
					+ "  <strong>Sorry, no movies were found!</strong> Please rate more movies next time.\r\n"
					+ "</div>\r\n"
					+ "");
			return;
		}
		
		int count = 0;
		System.out.println("<style>\r\n"
				+ "table {\r\n"
				+ "  font-family: arial, sans-serif;\r\n"
				+ "  border-collapse: collapse;\r\n"
				+ "  width: 100%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "td, th {\r\n"
				+ "  border: 1px solid #dddddd;\r\n"
				+ "  text-align: left;\r\n"
				+ "  padding: 8px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "tr:nth-child(even) {\r\n"
				+ "  background-color: #dddddd;\r\n"
				+ "}\r\n"
				+ "</style>");
		System.out.println("<style>\r\n"
				+ ".alert {\r\n"
				+ "  padding: 20px;\r\n"
				+ "  background-color: #f44336;\r\n"
				+ "  color: white;\r\n"
				+ "  opacity: 1;\r\n"
				+ "  transition: opacity 0.6s;\r\n"
				+ "  margin-bottom: 15px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".alert.success {background-color: #04AA6D;}\r\n"
				+ "\r\n"
				+ ".closebtn {\r\n"
				+ "  margin-left: 15px;\r\n"
				+ "  color: white;\r\n"
				+ "  font-weight: bold;\r\n"
				+ "  float: right;\r\n"
				+ "  font-size: 22px;\r\n"
				+ "  line-height: 20px;\r\n"
				+ "  cursor: pointer;\r\n"
				+ "  transition: 0.3s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".closebtn:hover {\r\n"
				+ "  color: black;\r\n"
				+ "}\r\n"
				+ "</style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<div class=\"alert success\">\r\n"
				+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
				+ "  <strong>Success!</strong> Here is the list of movies recommended to you, based on your ratings.\r\n"
				+ "</div>");
		System.out.println("<br>");
		System.out.println("<table> <thead> <tr> <th>Rank</th> <th>Title</th> <th>Year</th> <th>Genre(s)</th> <th>Director(s)</th> <th>Minutes</th> </tr> </thead> <tbody>");
		for (Rating r : ratings) {
			if (count >= 50) {
				break;
			} else {
				System.out.println("<tr>" + "<td>" + (count + 1) + "<td>" + MovieDatabase.getTitle(r.getItem()) +  "</td>" + "<td>" + MovieDatabase.getYear(r.getItem()) +  "</td>" + "<td>" + MovieDatabase.getGenres(r.getItem()) +  "</td>" + "<td>" + MovieDatabase.getDirector(r.getItem()) +  "</td>" + "<td>" + MovieDatabase.getMinutes(r.getItem()) +  "</td>" + "</tr>");
				count++;
			}
		}
		System.out.println("</tbody> </table>");
		
	}

}
