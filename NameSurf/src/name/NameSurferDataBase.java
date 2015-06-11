package name;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import acm.util.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	private Map<String, NameSurferEntry> nameBook = new HashMap<String, NameSurferEntry>();

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		getNameData(filename);
	}

	private void getNameData(String filename) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				NameSurferEntry nameEntry = new NameSurferEntry(line);
				nameBook.put(nameEntry.getName(), nameEntry);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		// Transfer input to standard for Aaaa
		char ch = name.charAt(0);
		if (Character.isLowerCase(ch) == true) {
			ch = Character.toUpperCase(ch);
		}
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();
		name = ch + otherLetters;
		// Look in HashMap namesdb
		if (nameBook.containsKey(name)) {
			// return the corresponding value-which is a NameSuferEntry
			return nameBook.get(name);
		} else {
			return null;
		}
	}
}
