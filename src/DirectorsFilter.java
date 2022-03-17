
public class DirectorsFilter implements Filter {
	private String[] myDirs;

	public DirectorsFilter(String directors) {
		myDirs = directors.split(",");
	}

	@Override
	public boolean satisfies(String id) {
		String dir = MovieDatabase.getDirector(id);
		for (String s : myDirs) {
			if (dir.indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}

}
