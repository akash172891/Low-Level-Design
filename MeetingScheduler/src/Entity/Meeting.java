package Entity;

import java.util.List;

// Booked meeting linking organizer, attendees, room, and time.
public class Meeting {
    private final String id;
    private final String title;
    private final User organizer;
    private final List<User> participants;
    private final MeetingRoom room;
    private final TimeSlot timeSlot;
    private MeetingStatus status;

    public Meeting(String id, String title, User organizer, List<User> participants,
                   MeetingRoom room, TimeSlot timeSlot) {
        this.id = id;
        this.title = title;
        this.organizer = organizer;
        this.participants = participants;
        this.room = room;
        this.timeSlot = timeSlot;
        this.status = MeetingStatus.SCHEDULED;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public User getOrganizer() { return organizer; }
    public List<User> getParticipants() { return participants; }
    public MeetingRoom getRoom() { return room; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public MeetingStatus getStatus() { return status; }

    public void cancel() { this.status = MeetingStatus.CANCELLED; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " @ " + room + " " + timeSlot + " (" + status + ")";
    }
}
