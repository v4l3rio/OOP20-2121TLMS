package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class UserNameControllerImpl implements UserNameController {

	private static final String SEPARATOR = File.separator;
	private static final String FOLDER_NAME = System.getProperty("user.home") + SEPARATOR + "TLMS";
	/**
     * path of the user name file.
     */
    public static final String FILE_NAME_USER = FOLDER_NAME + SEPARATOR + "userName.json";

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
