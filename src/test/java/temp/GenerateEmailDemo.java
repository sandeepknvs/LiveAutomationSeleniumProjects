package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		Date date = new Date();
		String stringdate = date.toString();
		String noColon = stringdate.replaceAll("\\s", "");
		String noSpacenoColon = noColon.replaceAll(":", "");
		String emailWithTimeStamp = noSpacenoColon+"@gmail.com";
	}

}
