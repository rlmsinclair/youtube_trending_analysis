// Models an object that contains the likes and dislikes of a video

public class VideoData {

	double likes;
	double dislikes;

	public VideoData(double likes, double dislikes) {
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public double getLikes() {
		return this.likes;
	}

	public double getDislikes() {
		return this.dislikes;
	}

}
