package twitter;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class ExtractTest {

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes @user1 #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "john", "@user2 mentioned in this tweet.", d3);
    private static final Tweet tweet4 = new Tweet(4, "jane", "No mentions here.", d3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanThreeTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanEmptyList() {
        Timespan timespan = Extract.getTimespan(Arrays.asList());
        assertEquals("expected equal start and end for empty list", 
                     timespan.getStart(), timespan.getEnd());
    }

    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetMentionedUsersSingleMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
        Set<String> expected = new HashSet<>(Arrays.asList("user1"));
        assertEquals("expected single mention", expected, mentionedUsers);
    }

    @Test
    public void testGetMentionedUsersMultipleMentions() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2, tweet3));
        Set<String> expected = new HashSet<>(Arrays.asList("user1", "user2"));
        assertEquals("expected multiple mentions", expected, mentionedUsers);
    }
    
    @Test
    public void testGetMentionedUsersCaseInsensitive() {
        Tweet tweetWithDifferentCase = new Tweet(5, "alice", "@User1 and @USER1 are the same.", d3);
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetWithDifferentCase));
        Set<String> expected = new HashSet<>(Arrays.asList("user1"));
        assertEquals("expected case insensitive mention", expected, mentionedUsers);
    }
}
