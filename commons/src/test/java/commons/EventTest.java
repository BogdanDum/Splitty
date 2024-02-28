package commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/*

Important to consider, the hash consistency and the eventId
uniqueness cannot be tested without persisting entities yet
should be implemented in the future.


 */


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;


public class EventTest {

    private Event event;
    private Event event1;
    private Event event2;
    List<Participant> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add(new Participant());
        event = new Event("Test Event", new ArrayList<>());
        event1 = new Event("Title", List.of(new Participant("Person1", "p1"),
                new Participant("Person2", "p2")));
        event2 = new Event("Title", List.of(new Participant("Person1", "p1"),
                new Participant("Person2", "p2")));
    }

    @Test
    void testConstructor() {
        String title = "Test Event";
        List<Participant> participants = Arrays.asList(new Participant("Person1", "p1"),
                new Participant("Person2", "p2"));
        Event event = new Event(title, participants);
        assertEquals(title, event.getTitle());
        assertEquals(participants.size(), event.getParticipants().size());
        assertTrue(event.getParticipants().containsAll(participants));
        assertNotNull(event.getCreationDate());
    }

    @Test
    void testConstructorNullParticipants() {
        String title = "Test Event";
        Event event = new Event(title, null);
        assertNotNull(event);
        assertEquals(title, event.getTitle());
        assertNotNull(event.getParticipants());
        assertTrue(event.getParticipants().isEmpty());
        assertNotNull(event.getCreationDate());
    }


    @Test
    void testCreationDate() {
        Event event = new Event("Test Event", List.of(
                new Participant("John Doe", "jd")));
        assertNotNull(event.getCreationDate());
        assertTrue(event.getCreationDate().getTime() <= System.currentTimeMillis());
    }

    @Test
    public void EventGetterTest(){
        String title = "title";
        Event test = new Event(title, null);
        assertEquals("title", test.getTitle());
    }

    @Test
    public void EventSetterTest(){
        String title = "title";
        Event test = new Event(title, null);
        assertEquals("title", test.getTitle());
        test.setTitle("newTitle");
        assertEquals("newTitle", test.getTitle());
    }

    @Test
    void testSameInstance() {
        Event testEvent = event;
        assertEquals(testEvent, event);
    }

    @Test
    void testDifferentInstance() {
        assertEquals(event1, event2);
    }

    @Test
    void testEqualityWithoutId(){
        assertEquals(event1.getTitle(), event2.getTitle());
        assertEquals(event1.getParticipants().size(), event2.getParticipants().size());
        for(int i = 0; i < event1.getParticipants().size(); i++){
            assertEquals(event1.getParticipants().get(i), event2.getParticipants().get(i));
        }
    }


    @Test
    void testNull() {
        assertNotEquals(null, event);
    }

    @Test
    void testEqualityDifferentClass() {
        Object other = new Object();
        assertNotEquals(event, other);
    }

    @Test
    void testHashConsistency() {
        assertEquals(event1.hashCode(), event2.hashCode());
        // should be equal due to unique ID not taken into consideration
        //can later be tested differently when object persistence is implemented with a database
    }

    @Test
    void addingParticipantTest() {
        Participant participant = new Participant("Person123", "test123");
        event.addParticipant(participant);
        assertEquals(event.getParticipants().getFirst(), participant);
    }

    @Test
    void settingParticipantTest(){
        List<Participant> testList = new ArrayList<>();
        for(int i = 0; i<4; i++){
            testList.add(new Participant("Person"+i, "test"+i));
        }
        Event event1 = new Event("title", null);
        event1.setParticipants(testList);
        Event event2 = new Event("title", testList);
        assertEquals(event1.getParticipants(), event2.getParticipants());
    }

    @Test
    void testDeleteParticipant() {
        Participant participant = new Participant("Person", "test");
        event.addParticipant(participant);
        assertTrue(event.deleteParticipant(participant));
        assertFalse(event.getParticipants().contains(participant));
    }

    @Test
    void testDeleteNothing() {
        Participant participant = new Participant("Person", "test");
        event.deleteParticipant(participant);
        assertFalse(event.getParticipants().contains(participant));
    }

    @Test
    void testParticipantList() {
        Participant participant1 = new Participant("Person123", "test123");
        Participant participant2 = new Participant("Person123", "test123");
        event.addParticipant(participant1);
        event.addParticipant(participant2);
        event.deleteParticipant(participant1);
        assertTrue(event.getParticipants().contains(participant2) && event.getParticipants().size() == 1);
    }
}
