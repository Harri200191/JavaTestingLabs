package twitter;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;


public class Filter {

    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().equalsIgnoreCase(username)) {
                result.add(tweet);
            }
        }
        return result;
    }

    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (isWithinTimespan(tweet.getTimestamp(), timespan)) {
                result.add(tweet);
            }
        }
        return result;
    }

    private static boolean isWithinTimespan(Instant timestamp, Timespan timespan) {
        return !timestamp.isBefore(timespan.getStart()) && !timestamp.isAfter(timespan.getEnd());
    }

    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            for (String word : words) {
                if (text.toLowerCase().contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // Found at least one matching word
                }
            }
        }
        return result;
    }
}
