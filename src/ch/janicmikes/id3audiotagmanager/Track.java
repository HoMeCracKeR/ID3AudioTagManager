package ch.janicmikes.id3audiotagmanager;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;

// TODO: Use Hibernate to store and manage tracks in DB (http://hibernate.org)
@Getter
public class Track {
	SimpleDateFormat informat = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
	SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private Integer trackId, size, time, discNumber, discCount, trackNumber,
			trackCount, year, bpm, bitRate, sampleRate, playDate, rating,
			albumRating, fileFolderCount, libraryfolderCount, playCount,
			artworkCount, audiokind;
	private String name, artist, albumArtist, album, grouping, genre, kind,
			persistentId, trackType, location, comments;

	Timestamp playDateUTC, dateAdded, dateModified, skipDate;
	private Boolean audiofile = false, compilation, explict, purchased,
			albumRatingComputed;

	private static final String TRACKID = "Track ID", NAME = "Name",
			ARTIST = "Artist", ALBUM_ARTIST = "Album Artist", ALBUM = "Album",
			GENRE = "Genre", GROUPING = "Grouping", KIND = "Kind",
			SIZE = "Size", TOTAL_TIME = "Total Time",
			DISC_NUMBER = "Disc Number", DISC_COUNT = "Disc Count",
			TRACK_NUMBER = "Track Number", TRACK_COUNT = "Track Count",
			YEAR = "Year", BPM = "BPM", DATE_MODIFIED = "Date Modified",
			DATE_ADDED = "Date Added", BIT_RATE = "Bit Rate",
			SAMPLE_RATE = "Sample Rate", COMMENTS = "Comments",
			PLAY_COUNT = "Play Count", PLAY_DATE = "Play Date",
			PLAY_DATE_UTC = "Play Date UTC", SKIP_DATE = "Skip Date",
			RATING = "Rating", ALBUM_RATING = "Album Rating",
			ALBUM_RATING_COMPUTED = "Album Rating Computed",
			COMPILATION = "Compilation", ARTWORK_COUNT = "Artwork Count",
			PERSISTENT_ID = "Persistent ID", EXPLICT = "Explicit",
			TRACK_TYPE = "Track Type", PURCHASED = "Purchased",
			LOCATION = "Location", FILE_FOLDER_COUNT = "File Folder Count",
			LIBRARY_FOLDER_COUNT = "Library Folder Count";

	public Track(NSDictionary track) {
		for (String tk : track.allKeys()) {
			int tmpint = 0;
			boolean tmpbool = false;
			// Timestamp tmptimestamp = null;

			// System.out.println(track.get(tk).getClass());
			if (track.get(tk).getClass().equals(NSNumber.class)) {
				NSNumber num = (NSNumber) track.get(tk);
				switch (num.type()) {
				case NSNumber.BOOLEAN: {
					tmpbool = num.boolValue();
					break;
				}
				case NSNumber.INTEGER: {
					tmpint = num.intValue();
					break;
				}
				}
			}

			// Switch Properties
			switch (tk) {
			case Track.TRACKID: {
				this.trackId = tmpint;
				break;
			}
			case Track.NAME: {
				this.name = track.get(tk).toString();
				break;
			}
			case Track.ARTIST: {
				this.artist = track.get(tk).toString();
				break;
			}
			case Track.ALBUM_ARTIST: {
				this.albumArtist = track.get(tk).toString();
				break;
			}
			case Track.ALBUM: {
				this.album = track.get(tk).toString();
				break;
			}
			case Track.GENRE: {
				this.genre = track.get(tk).toString();
				break;
			}
			case Track.GROUPING: {
				this.grouping = track.get(tk).toString();
				break;
			}
			case Track.KIND: {
				this.kind = track.get(tk).toString();
				// Determine exact kind
				if (this.kind.toUpperCase().contains("AUDIO")) {
					this.audiofile = true;
					if (this.kind.contains("AAC")) {
						this.audiokind = AudioKind.AAC_FILE;
					} else {
						this.audiokind = AudioKind.MPEG_FILE;
					}
				} else {
					this.audiofile = false;
				}

				break;
			}
			case Track.SIZE: {
				this.size = tmpint;
				break;
			}
			case Track.TOTAL_TIME: {
				this.time = tmpint;
				break;
			}
			case Track.DISC_NUMBER: {
				this.discNumber = tmpint;
				break;
			}
			case Track.DISC_COUNT: {
				this.discCount = tmpint;
				break;
			}
			case Track.TRACK_NUMBER: {
				this.trackNumber = tmpint;
				break;
			}
			case Track.TRACK_COUNT: {
				this.trackCount = tmpint;
				break;
			}
			case Track.YEAR: {
				this.year = tmpint;
				break;
			}
			case Track.BPM: {
				this.bpm = tmpint;
				break;
			}
			case Track.DATE_MODIFIED: {
				this.dateModified = getSQLTimestamp(track.get(tk).toString());
				break;
			}
			case Track.DATE_ADDED: {
				this.dateAdded = getSQLTimestamp(track.get(tk).toString());
				break;
			}
			case Track.BIT_RATE: {
				this.bitRate = tmpint;
				break;
			}
			case Track.SAMPLE_RATE: {
				this.sampleRate = tmpint;
				break;
			}
			case Track.COMMENTS: {
				this.comments = track.get(tk).toString();
				break;
			}
			case Track.PLAY_COUNT: {
				this.playCount = tmpint;
				break;
			}
			case Track.PLAY_DATE: {
				this.playDate = tmpint;
				break;
			}
			case Track.PLAY_DATE_UTC: {
				this.playDateUTC = getSQLTimestamp(track.get(tk).toString());
				break;
			}
			case Track.SKIP_DATE: {
				this.skipDate = getSQLTimestamp(track.get(tk).toString());
				break;
			}
			case Track.RATING: {
				this.rating = tmpint;
				break;
			}
			case Track.ALBUM_RATING: {
				this.albumRating = tmpint;
				break;
			}
			case Track.ALBUM_RATING_COMPUTED: {
				this.albumRatingComputed = tmpbool;
				break;
			}
			case Track.COMPILATION: {
				this.compilation = tmpbool;
				break;
			}
			case Track.ARTWORK_COUNT: {
				this.artworkCount = tmpint;
				break;
			}
			case Track.PERSISTENT_ID: {
				this.persistentId = track.get(tk).toString();
				break;
			}
			case Track.EXPLICT: {
				this.explict = tmpbool;
				break;
			}
			case Track.TRACK_TYPE: {
				this.trackType = track.get(tk).toString();
				break;
			}
			case Track.PURCHASED: {
				this.purchased = tmpbool;
				break;
			}
			case Track.LOCATION: {
				this.location = track.get(tk).toString();
				break;
			}
			case Track.FILE_FOLDER_COUNT: {
				this.fileFolderCount = tmpint;
				break;
			}
			case Track.LIBRARY_FOLDER_COUNT: {
				this.libraryfolderCount = tmpint;
				break;
			}
			}
		}

	}

	private Timestamp getSQLTimestamp(String string) {
		Date date = null;
		try {
			date = informat.parse(string);
			this.playDateUTC = Timestamp.valueOf(outformat.format(date));

		} catch (ParseException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return Timestamp.valueOf(outformat.format(date));
	}

	private String getCSV(Object o) {
		if (o == null) {
			return "";
		} else if (o instanceof String) {
			String tmpvalue = "";
			tmpvalue = (String) o;
			if (tmpvalue.contains(",") || tmpvalue.contains("\"")
					|| tmpvalue.contains("\n")) {
				return "\"" + tmpvalue.replace("\"", "\"\"") + "\"";
			} else {
				return tmpvalue;
			}
		} else {
			return o.toString();
		}
	}

	public CharSequence toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCSV(this.getTrackId())).append(",")
				.append(getCSV(this.getName())).append(",")
				.append(getCSV(this.getArtist())).append(",")
				.append(getCSV(this.getAlbumArtist())).append(",")
				.append(getCSV(this.getAlbum())).append(",")
				.append(getCSV(this.getGenre())).append(",")
				.append(getCSV(this.getGrouping())).append(",")
				.append(getCSV(this.getKind())).append(",")
				.append(getCSV(this.getSize())).append(",")
				.append(getCSV(this.getTime())).append(",")
				.append(getCSV(this.getDiscNumber())).append(",")
				.append(getCSV(this.getDiscCount())).append(",")
				.append(getCSV(this.getTrackNumber())).append(",")
				.append(getCSV(this.getTrackCount())).append(",")
				.append(getCSV(this.getYear())).append(",")
				.append(getCSV(this.getBpm())).append(",")
				.append(getCSV(this.getDateModified())).append(",")
				.append(getCSV(this.getDateAdded())).append(",")
				.append(getCSV(this.getBitRate())).append(",")
				.append(getCSV(this.getSampleRate())).append(",")
				.append(getCSV(this.getComments())).append(",")
				.append(getCSV(this.getPlayCount())).append(",")
				.append(getCSV(this.getPlayDate())).append(",")
				.append(getCSV(this.getPlayDateUTC())).append(",")
				.append(getCSV(this.getSkipDate())).append(",")
				.append(getCSV(this.getRating())).append(",")
				.append(getCSV(this.getAlbumRating())).append(",")
				.append(getCSV(this.getAlbumRatingComputed())).append(",")
				.append(getCSV(this.getCompilation())).append(",")
				.append(getCSV(this.getArtworkCount())).append(",")
				.append(getCSV(this.getPersistentId())).append(",")
				.append(getCSV(this.getExplict())).append(",")
				.append(getCSV(this.getTrackType())).append(",")
				.append(getCSV(this.getPurchased())).append(",")
				.append(getCSV(this.getLocation())).append(",")
				.append(getCSV(this.getFileFolderCount())).append(",")
				.append(getCSV(this.getLibraryfolderCount()));
		return sb.toString();
	}

	public static String getAttributes() {
		return "TRACKID," + "NAME," + "ARTIST," + "ALBUM_ARTIST," + "ALBUM,"
				+ "GENRE," + "GROUPING" + "KIND," + "SIZE," + "TOTAL_TIME,"
				+ "DISC_NUMBER," + "DISC_COUNT," + "TRACK_NUMBER,"
				+ "TRACK_COUNT," + "YEAR," + "BPM," + "DATE_MODIFIED,"
				+ "DATE_ADDED," + "BIT_RATE," + "SAMPLE_RATE," + "COMMENTS,"
				+ "PLAY_COUNT," + "PLAY_DATE," + "PLAY_DATE_UTC,"
				+ "SKIP_DATE," + "RATING," + "ALBUM_RATING,"
				+ "ALBUM_RATING_COMPUTED," + "COMPILATION," + "ARTWORK_COUNT,"
				+ "PERSISTENT_ID," + "EXPLICT," + "TRACK_TYPE," + "PURCHASED,"
				+ "LOCATION," + "FILE_FOLDER_COUNT," + "LIBRARY_FOLDER_COUNT";
	}

}
