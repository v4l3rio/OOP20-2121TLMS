package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * Implementation of {@link UserNameController}.
 */
public final class UserNameControllerImpl implements UserNameController {

	private static final String SEPARATOR = File.separator;
	private static final String FOLDER_NAME = System.getProperty("user.home") + SEPARATOR + "TLMS";
	/**
     * path of the user name file.
     */
    public static final String FILE_NAME_USER = FOLDER_NAME + SEPARATOR + "userName.json";

	@Override
	public void firstGame() throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(UserNameControllerImpl.FILE_NAME_USER));
		    @SuppressWarnings("unused")
		    final String userName = reader.readLine(); //unused but useful to know if it's the firt game ever
		    reader.close();
		} catch (IOException e) {
			createUserNameFile(UserNameControllerImpl.FILE_NAME_USER);
		}	
	}
	
	/**
	 * Method to create user name file.
	 * @param fileNameUser
	 *            path of the file
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	private void createUserNameFile(final String fileNameUser) throws IOException {
		final FileWriter fw = new FileWriter(fileNameUser, true);
		final BufferedWriter bw = new BufferedWriter(fw);
	    bw.write("STEVEN");
	    bw.close();
	    fw.close();
	}

	@Override
	public String readUser() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_USER))) {
			final String user = reader.readLine();
			reader.close();
			return user;
		}
	}

	@Override
	public void writeUser(final String user) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_USER))) {
			final String upperCaseUser = user.toUpperCase();
			writer.write(upperCaseUser);
		}
	}

}
