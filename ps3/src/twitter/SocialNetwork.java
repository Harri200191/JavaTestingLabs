package twitter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet.
     */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
	    Map<String, Set<String>> followsGraph = new HashMap<>();

	    // Regex pattern to match mentions (e.g., @username)
	    Pattern mentionPattern = Pattern.compile("@([A-Za-z0-9_-]+)");

	    for (Tweet tweet : tweets) {
	        String author = tweet.getAuthor().toLowerCase(); // Normalize to lower case

	        // Find mentions in the tweet text
	        Matcher matcher = mentionPattern.matcher(tweet.getText());
	        Set<String> mentionedUsers = new HashSet<>(); // Collect mentioned users for the current tweet

	        while (matcher.find()) {
	            String mentionedUser = matcher.group(1).toLowerCase(); // Get the username, normalize to lower case
	            mentionedUsers.add(mentionedUser);
	        }

	        // Only add to the graph if there are mentioned users
	        if (!mentionedUsers.isEmpty()) {
	            followsGraph.putIfAbsent(author, new HashSet<>()); // Ensure the author is in the graph
	            followsGraph.get(author).addAll(mentionedUsers); // Add mentioned users
	            // Ensure mentioned users are in the graph
	            for (String mentionedUser : mentionedUsers) {
	                followsGraph.putIfAbsent(mentionedUser, new HashSet<>());
	            }
	        }
	    }

	    return followsGraph;
	}


    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followerCounts = new HashMap<>();

        // Count followers for each user
        for (Map.Entry<String, Set<String>> entry : followsGraph.entrySet()) {
            String user = entry.getKey();
            Set<String> follows = entry.getValue();

            for (String followedUser : follows) {
                followerCounts.put(followedUser, followerCounts.getOrDefault(followedUser, 0) + 1);
            }
        }

        // Create a list of users sorted by follower count
        List<Map.Entry<String, Integer>> sortedInfluencers = new ArrayList<>(followerCounts.entrySet());
        sortedInfluencers.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort descending by follower count

        List<String> influencers = new ArrayList<>();
        for (Map.Entry<String, Integer> influencer : sortedInfluencers) {
            influencers.add(influencer.getKey());
        }

        return influencers;
    }
}
