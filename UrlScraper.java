// Scrapes all the urls from trending and puts them in an arraylist


import java.io.*;
import java.util.*;

import java.util.ArrayList;

public class UrlScraper {

	private String filename;
	private BufferedReader reader;
	private ArrayList<String> lineArrayList;

	public UrlScraper() {
		this.filename = "trending.htm";
		this.lineArrayList = new ArrayList<>();

		try {
			this.reader = new BufferedReader(new FileReader(new File(filename)));
		}
		catch (Exception e) {
			System.out.println("Could not initialise reader, no file found");
		}
	}

	// Reads the next line of the file
	public String getLine() {
		try {
			return this.reader.readLine();
		}
		catch (Exception e) {
			System.out.println("Could not read next line, no file found");
			return null;
		}

	}

	// Returns a boolean which is true if the file is ready
	public Boolean fileIsReady() {
		try {
			return this.reader.ready();
		}
		catch (Exception e) {
			System.out.println("Could not determine if file is ready, no file found");
			return false;
		}
	}

	// Returns an array list of the file
	public ArrayList<String> createArrayList() {
		while (fileIsReady()) {
			String line = getLine();
			lineArrayList.add(line);
		}
		return lineArrayList;
	}

	public ArrayList<String> getUrls() {
		ArrayList<String> urlArrayList = new ArrayList<>();
		lineArrayList = createArrayList();
		for(String line : lineArrayList) {
			if (line.contains("videoId")) {
				String[] wordArray = line.split("VideoId\":\"");
				for (String word : wordArray) {
					String url = word.split("\"}]}}")[0];
					if (!url.contains("action")) {
						url = "https://www.youtube.com/watch?v=" + url;
						urlArrayList.add(url);
					}
				}
			}
		}
		System.out.println(urlArrayList.size() + " videos detected");
		return urlArrayList;
	}

}
