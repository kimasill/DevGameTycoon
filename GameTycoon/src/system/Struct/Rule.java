package system.Struct;

import java.io.Serializable;
import java.util.Random;

public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	private Subject[] subjects;
	private Genre[] genres;
	private int[][] recipe;

	public Rule() {
		subjects = Subject.values();
		genres = Genre.values();
		recipe = new int[subjects.length][genres.length];
		Random s = new Random();
		for (int i = 0; i < subjects.length; i++) {
			for (int j = 0; j < genres.length; j++) {
				recipe[i][j] = s.nextInt(90) + 10;
			}
		}
	}

	public int getInterest(Subject subject, Genre genre) {
		int x = indexOf(subjects, subject);
		int y = indexOf(genres, genre);
		return recipe[x][y];
	}

	private static int indexOf(Subject[] arr, Subject v) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == v)
				return i;
		}
		return 0;
	}

	private static int indexOf(Genre[] arr, Genre v) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == v)
				return i;
		}
		return 0;
	}
}
