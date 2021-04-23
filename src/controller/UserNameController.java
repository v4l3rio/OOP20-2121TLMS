package controller;

import java.io.IOException;

/**
 * The UserController interface provides methods to read/write user name.
 */
public interface UserNameController {
	
	/**
	  * @return
	  * 	 the last User saved on file
	  * @throws IOException
	  *             if an I/O error occurs
	  */
	String readUser() throws IOException;
	
	/**
	 * @param user
	 *           the user to write on file
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	void writeUser(String user) throws IOException;

}
