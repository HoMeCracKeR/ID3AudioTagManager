package ch.janicmikes.id3audiotagmanager;

import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;

import com.dd.plist.*;

/**
 * 
 * @author janicmikes
 *
 */
public class ID3AudioTagManager {

	String library;
	final String user = "jdbctut";
	final String password = "jdbctut";
	final String database = "jdbc:postgresql://localhost/jdbctut";
	Properties config = new Properties();

	List<Track> audiotracks = new ArrayList<Track>();

	private ID3AudioTagManager() {
		// TODO: Read settings from settings/settings.txt File or static Class
		// or similar

		// Init Settings
		config.setProperty("database",
				"jdbc:postgresql://localhost/audiotagmanager");
		config.setProperty("username", "audiotagmanager");
		config.setProperty("password", "audiotagmanager");

		// buffer approximately times 550 (1 batch -> 550 buffer)
		config.setProperty("batchsize", "200");
		config.setProperty("buffersize", "150000");
	}

	/**
	 * Saves the AudioTracks to specified database (see settings/database)
	 */
	private void saveLibrary() {
		int preprocess = -1, process = 0, processed = 0, totaltracks = this.audiotracks.size();
		try (Connection connection = DriverManager.getConnection(
				config.getProperty("database"), config.getProperty("username"),
				config.getProperty("password"))) {
			StringBuilder sb = new StringBuilder();
			CopyManager cpManager = ((PGConnection) connection).getCopyAPI();
			PushbackReader reader = new PushbackReader(new StringReader(""),
					Integer.parseInt(config.getProperty("buffersize")));
			int batchSize = Integer.parseInt(config.getProperty("batchsize"));
			int i = 0;

			for (Track track : audiotracks) {
				sb.append(track.toCSV()).append("\n");
				if (++i % batchSize == 0) {
					reader.unread(sb.toString().toCharArray());
					cpManager.copyIn("COPY input_track FROM STDIN WITH CSV",
							reader);
					sb.delete(0, sb.length());

				}
				process = Math
						.round(((float) ++processed / (float) totaltracks * (float) 100));
				if (process % 5 == 0 && preprocess != process) {
					preprocess = process;
					System.out.println("COPY to DB: " + process + "%");
				}
			}
			reader.unread(sb.toString().toCharArray());
			cpManager.copyIn("COPY input_track FROM STDIN WITH CSV", reader);

		} catch (SQLException | IOException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	/**
	 * Loads an iTunes generated XML library file
	 * 
	 * @param library
	 *            path to library XML file
	 * @param limit
	 *            set 0 for no limit
	 */
	private void loadLibrary(String library, int limit) {
		this.library = library;
		int preprocess = -1, process = 0, processed = 0, totaltracks = 0;
		try {
			System.out.println("Initialize...");
			File file = new File(library);
			NSDictionary tracks = (NSDictionary) ((NSDictionary) PropertyListParser
					.parse(file)).get("Tracks");

			// All Tracks to Parse (100%) - For progress
			totaltracks = tracks.allKeys().length;
			System.out.println(totaltracks + " Tracks");

			//
			for (String k : tracks.allKeys()) {
				NSDictionary track = (NSDictionary) tracks.get(k);

				Track tmptrack = new Track(track);
				if (tmptrack.getAudiofile().booleanValue()) {
					this.audiotracks.add(tmptrack);
				}

				// Process
				process = Math
						.round(((float) ++processed / (float) totaltracks * (float) 100));
				if (process % 5 == 0 && preprocess != process) {
					preprocess = process;
					System.out.println("Loading XML: " + process + "%");
				}

				// processed starts with 1 (set limit to 0 to fetch all Tracks)
				if (processed == limit) {
					break;
				}
			}
			//
			System.out.println(this.audiotracks.size() + " Audiofiles found");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ID3AudioTagManager manager = new ID3AudioTagManager();
		manager.loadLibrary("Resources/iTunes Library.xml", 0);
		manager.saveLibrary();
	}
}
