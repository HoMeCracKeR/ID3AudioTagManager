package ch.janicmikes.id3audiotagmanager;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.tag.*;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.*;

public class ID3AudioTagManagerSingle {
	public static void main(String[] args) {
		 File file = new File("Resources/audiofile.m4a");

		AudioFile f;

		try {
			f = AudioFileIO.read(file);

			// f.toString();
			Tag tag = f.getTag();
//			System.out.println(tag);
			String bpm = tag.getFirst(FieldKey.BPM);
			String key = tag.getFirst(FieldKey.KEY);
			String comment = tag.getFirst(FieldKey.COMMENT);
			String title = tag.getFirst(FieldKey.TITLE);
			//
			
			//
			System.out.println("Name: " + title);
			System.out.println("BPM: " + bpm);
			System.out.println("KEY: " + key);
			System.out.println("COMMENT: " + comment);
			//
			
			if(bpm.equals("") || bpm.equals("0") || key.equals("")){
				System.out.println("File needs to get analyzed");
			}
			//
		} catch (CannotReadException | IOException | TagException
				| ReadOnlyFileException | InvalidAudioFrameException e) {
			e.printStackTrace();
		}

	}

	
}
