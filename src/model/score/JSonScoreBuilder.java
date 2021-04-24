package model.score;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Implementation of {@link ScoreBuilder} using JSon format.
 * This class builds a {@link JsonScore} with the build pattern.
 * <p>
 * Here is an example of how Builder can be used: 
 * <pre>{@code
 *     JsonScore score = new JsonScoreBuilder()
 *                       	.nameFromString("MIKE")
 *                       	.score(150)
 *                       	.build();
 * }</pre>
 */
public class JSonScoreBuilder implements ScoreBuilder<String, Integer> {

	private String name;
	private Integer score;

	/**
	 * @param name A string with the user name
	 * @return The {@link JSonScoreBuilder} builder with user name
	 */
	public JSonScoreBuilder nameFromString(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param path A string with the path of the user name file
	 * @return The {@link JSonScoreBuilder} builder with user name
	 * @throws FileNotFoundException if the named file does not exist
	 * @throws IOException           if an I/O error occurs
	 */
	public JSonScoreBuilder nameFromPath(final String path) throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			this.name = reader.readLine();
		}
		return this;
	}

	/**
	 * @param score The integer score of the game
	 * @return The {@link JSonScoreBuilder} builder with score
	 */
	public JSonScoreBuilder score(final Integer score) {
		this.score = score;
		return this;
	}

	/**
	 * 
	 * @return A {@link JsonScore} only if there are not exceptions
	 * @throws IllegalStateException if name or score are unset
	 * @throws IOException           if an I/O error occurs
	 */
	public Score<String, Integer> build() {
		if (this.name == null || this.score == null) {
			throw new IllegalStateException();
		}
		return new JsonScore(name, score);
	}
}
