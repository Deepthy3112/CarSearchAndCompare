package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadTextFile {
    public static List<String> extractCarRegistrationNumbers(String filePath) {
        Matcher matcher = null;
		try {
			String data = new String(Files.readAllBytes(Paths.get(filePath)));
			Pattern allPatterns = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}"); 
			matcher = allPatterns.matcher(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return matcher.results().map(MatchResult::group).collect(Collectors.toList());
    }

    public static List<String> readExpectedOutput(String filePath) {
    	List<String> data = null;
    	try {
			 data=Files.readAllLines(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return data;
    }
}
