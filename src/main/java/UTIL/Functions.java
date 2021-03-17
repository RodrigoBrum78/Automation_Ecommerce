package UTIL;

public class Functions {
	
	public static Double supprimerSymbol$(String text) {
		
		text = text.replace("$", "");
		return Double.parseDouble(text);
		
	}
	
	public static int supprimerNomItems(String text) {
		text = text.replace(" items", "");
		return Integer.parseInt(text);
	}
	
	public static String effacerText(String text, String textPourEffacer) {
		text = text.replace(textPourEffacer, "");
		return text;
	}

}
