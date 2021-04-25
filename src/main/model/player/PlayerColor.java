package main.model.player;

/**
 * 
 * this enum is usefull to understand player's color
 */
public enum PlayerColor{
		BLUE("Blue"), 
		RED("Red");
	
	private final String actualName;

	/**
	 * 
	 * @param color's name
	 */
		PlayerColor(final String name) {
			this.actualName = name;
		}

		/**
		 * 
		 * @return the name assigned to this enum's value
		 */
		public String getActualName() {
			return this.actualName;
		}
				
}
