import Entity.*;
import Strategy.RoomSelectionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Facade/orchestrator: validates attendees + room, then books all calendars atomically (demo).
public class MeetingScheduler {
    private final List<MeetingRoom> rooms = new ArrayList<>();
    private final Map<String, Meeting> meetings = new HashMap<>();
    private RoomSelectionStrategy strategy;
    private int meetingCounter = 1;

    public MeetingScheduler(RoomSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public void addRoom(MeetingRoom room) { rooms.add(room); }

    public void setStrategy(RoomSelectionStrategy strategy) { this.strategy = strategy; }

    public Meeting schedule(String title, User organizer, List<User> participants, TimeSlot slot) {
        // All attendees (including organizer) must be free
        List<User> all = new ArrayList<>(participants);
        if (!all.contains(organizer)) all.add(organizer);

        for (User u : all) {
            if (!u.getCalendar().isAvailable(slot)) {
                System.out.println("Conflict: " + u + " busy at " + slot);
                return null;
            }
        }

        List<MeetingRoom> candidates = new ArrayList<>();
        for (MeetingRoom room : rooms) {
            if (room.getCapacity() >= all.size() && room.getCalendar().isAvailable(slot)) {
                candidates.add(room);
            }
        }

        MeetingRoom selected = strategy.select(candidates);
        if (selected == null) {
            System.out.println("No room available for " + title + " at " + slot);
            return null;
        }

        // Book room + all user calendars
        selected.getCalendar().book(slot);
        for (User u : all) u.getCalendar().book(slot);

        Meeting meeting = new Meeting("M" + meetingCounter++, title, organizer, all, selected, slot);
        meetings.put(meeting.getId(), meeting);
        System.out.println("Scheduled: " + meeting);
        return meeting;
    }

    public void cancel(String meetingId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || meeting.getStatus() == MeetingStatus.CANCELLED) return;

        meeting.cancel();
        meeting.getRoom().getCalendar().release(meeting.getTimeSlot());
        for (User u : meeting.getParticipants()) {
            u.getCalendar().release(meeting.getTimeSlot());
        }
        System.out.println("Cancelled: " + meeting);
    }
}
