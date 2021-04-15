package menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonScore implements Score<String, Integer> {

	private String name;
	private Integer score;
	
	public JsonScore(final String pathUser, final Integer score) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(pathUser));
	    this.name = reader.readLine();
	    this.score = score;
	    reader.close();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getScore() {
		return this.score;
	}

	@Override
	public void setScore(Integer score) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		JsonScore other = (JsonScore) obj;
		return name.equals(other.name) 
		    && score.equals(other.score);	
	}


}
