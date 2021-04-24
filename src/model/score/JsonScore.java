package model.score;

/**
 * This class implements {@link Score}.
 *
 * 
 */
public final class JsonScore implements Score<String, Integer> {

	private String name;
	private Integer score;
	
	/**
	 * @param name
	 *            The user name
	 * @param score
	 *            The score of the game
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public JsonScore(final String name, final Integer score) {
	    this.name = name;
	    this.score = score;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Integer getScore() {
		return this.score;
	}

	@Override
	public void setScore(final Integer score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final JsonScore other = (JsonScore) obj;
		return name.equals(other.name) 
		    && score.equals(other.score);	
	}
}
