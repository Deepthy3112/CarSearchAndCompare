package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//this is a generic method to read input text file into List using Pattern class of java which needs a Regex to identify regestration numbers in the file
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

	//this method reads output files to get the values to compare against into a List
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
