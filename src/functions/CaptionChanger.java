package functions;

public class CaptionChanger {

	private static String chanelid = "@TopTrends";
	private static String chanelurl = "t.me/TopTrends";

	public static String captionchanger(String caption) {

		if (caption.isEmpty()) {
			System.out.println("its null!");
			return chanelid;
		} else {
			System.out.println("\n\nthis is caption: \n" + caption);

			caption = caption.replaceAll("@[^\\s]+", chanelid);
			caption = caption.replaceAll("t.me/joinchat[^\\s]+", chanelurl);
			caption = caption.replaceAll("https?://\\S+\\s?", chanelurl);
			caption = caption.replaceAll("t.co/[^\\s]+", chanelurl);

			return caption;
		}
	}

}
