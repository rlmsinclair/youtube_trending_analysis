import java.util.ArrayList;

// Gives the trending video with highest and lowest like/dislike ratio
// Download the youtube trending page (https://www.youtube.com/feed/trending), view source, and save as "trending.htm"
// in source file folder

public class Main {

	public static void main(String[] args) {


		ArrayList<String> urlArrayList;
		ArrayList<Double> ratioArrayList = new ArrayList<>();

		UrlScraper urlScraper = new UrlScraper();
		urlArrayList = urlScraper.getUrls();


		Scraper scraper = new Scraper();
		int highestRatioArrayNum = 0;
		double highestRatio = 0;
		int lowestRatioArrayNum = 0;
		double lowestRatio = 100;
		for (int i = 0; i < urlArrayList.size(); i++) {

			VideoData videoData = scraper.getVideoData(urlArrayList.get(i));
			try {
				int j = i + 1;
				System.out.println("Video " + j + ": " + urlArrayList.get(i));
				System.out.println("Likes: " + videoData.getLikes());
				System.out.println("Dislikes: " + videoData.getDislikes());
				double likeDislikeRatio = videoData.getLikes() / videoData.getDislikes();
				System.out.println("Likes/Dislikes = " + String.format("%.2f", likeDislikeRatio));
				ratioArrayList.add(likeDislikeRatio);
				System.out.println();
				if (likeDislikeRatio > highestRatio) {
					highestRatio = likeDislikeRatio;
					highestRatioArrayNum = i;
				}
				if (likeDislikeRatio < lowestRatio) {
					lowestRatio = likeDislikeRatio;
					lowestRatioArrayNum = i;
				}


			}
			catch (Exception e) {}
		}

		System.out.println("----------\n----------\n----------\n");

		int temp1 = highestRatioArrayNum + 1;
		int temp2 = lowestRatioArrayNum + 1;
		System.out.println("Highest ratio video is video " + temp1 + ": " + urlArrayList.get(highestRatioArrayNum));
		System.out.println("Lowest ratio video is video " + temp2 + ": " + urlArrayList.get(lowestRatioArrayNum));




	}
}
