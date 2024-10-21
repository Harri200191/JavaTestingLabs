package twitter;

import static org.junit.Assert.*;
import java.time.Instant; // Ensure this import is included
import java.util.*;
import org.junit.Test;

public class SocialNetworkTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraphNoMentions() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "Hello there!", Instant.now()),
            new Tweet(2, "user2", "Goodbye!", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraphSingleMention() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "@user2 is great!", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(2, followsGraph.size());
        assertTrue(followsGraph.containsKey("user1"));
        assertTrue(followsGraph.get("user1").contains("user2"));
    }
    
    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "@user2 is great! @user3 too!", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(3, followsGraph.size());
        assertTrue(followsGraph.containsKey("user1"));
        assertTrue(followsGraph.get("user1").contains("user2"));
        assertTrue(followsGraph.get("user1").contains("user3"));
    }

    @Test
    public void testGuessFollowsGraphMultipleTweetsSameAuthor() {
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "user1", "@user2 is great!", Instant.now()),
            new Tweet(2, "user1", "@user3 is awesome!", Instant.now())
        );
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals(3, followsGraph.size());
        assertTrue(followsGraph.containsKey("user1"));
        assertTrue(followsGraph.get("user1").contains("user2"));
        assertTrue(followsGraph.get("user1").contains("user3"));
    }

    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testInfluencersSingleUserNoFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>()); // user1 follows nobody
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testInfluencersSingleInfluencer() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2"))); // user1 follows user2
        followsGraph.put("user2", new HashSet<>()); // user2 follows nobody
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals(1, influencers.size());
        assertEquals("user2", influencers.get(0)); // user2 should be the only influencer
    }

    @Test
    public void testInfluencersMultipleUsers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2", "user3"))); // user1 follows user2 and user3
        followsGraph.put("user2", new HashSet<>(Arrays.asList("user1"))); // user2 follows user1
        followsGraph.put("user3", new HashSet<>(Arrays.asList("user1", "user4"))); // user3 follows user1 and user4
        followsGraph.put("user4", new HashSet<>()); // user4 follows nobody
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals(4, influencers.size());
        assertEquals("user1", influencers.get(0)); // user1 has the most followers
        assertEquals("user2", influencers.get(1)); // user2 has 1 follower
        assertEquals("user3", influencers.get(2)); // user3 has 1 follower
        assertEquals("user4", influencers.get(3)); // user4 has no followers
    }

    @Test
    public void testInfluencersEqualFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("user1", new HashSet<>(Arrays.asList("user2"))); // user1 follows user2
        followsGraph.put("user2", new HashSet<>(Arrays.asList("user1"))); // user2 follows user1
        followsGraph.put("user3", new HashSet<>(Arrays.asList("user4"))); // user3 follows user4
        followsGraph.put("user4", new HashSet<>(Arrays.asList("user3"))); // user4 follows user3
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue(influencers.contains("user1"));
        assertTrue(influencers.contains("user2"));
        assertTrue(influencers.contains("user3"));
        assertTrue(influencers.contains("user4"));
    }
}
