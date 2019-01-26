import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// This class contains two methods that will return the likes and dislikes of a youtube video

public class Scraper {

	// Takes a string as a parameter, returns an int
	public VideoData getVideoData(String videoUrl) {
		VideoData videoData = null;
		try {
			Document doc = Jsoup.connect(videoUrl).header("User-Agent", "Chrome").get();
			Element body = doc.body();
			String noOfLikes = body.getElementsByAttributeValue("title", "I like this").get(0).text();
			String noOfDislikes = body.getElementsByAttributeValue("title", "I dislike this").get(0).text();
			// Split the strings if they have commas
			if (noOfLikes.contains(",")) {
				try {
					noOfLikes = noOfLikes.split(",")[0] + noOfLikes.split(",")[1] + noOfLikes.split(",")[2];
				}
				catch (Exception e) {
					noOfLikes = noOfLikes.split(",")[0] + noOfLikes.split(",")[1];
				}
			}
			// Should remove duplicate code
			if (noOfDislikes.contains(",")) {
				try {
					noOfDislikes = noOfDislikes.split(",")[0] + noOfDislikes.split(",")[1] + noOfDislikes.split(",")[2];
				}
				catch (Exception e) {
					noOfDislikes = noOfDislikes.split(",")[0] + noOfDislikes.split(",")[1];
				}
			}
			videoData = new VideoData(Double.parseDouble(noOfLikes), Double.parseDouble(noOfDislikes));
		}

		catch(Exception e) {
			System.out.println("Couldn't scrape data");
		}

		return videoData;
	}



}
