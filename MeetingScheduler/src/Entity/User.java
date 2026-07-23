package Entity;

import Calendar.Calendar;

// Participant/organizer; each user owns a calendar for conflict checks.
public class User {
    private final String id;
    private final String name;
    private final Calendar calendar = new Calendar();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Calendar getCalendar() { return calendar; }

    @Override
    public String toString() { return name; }
}
