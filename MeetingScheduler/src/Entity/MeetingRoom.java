package Entity;

import Calendar.Calendar;

// Physical room with capacity; calendar tracks booked slots.
public class MeetingRoom {
    private final String id;
    private final String name;
    private final int capacity;
    private final Calendar calendar = new Calendar();

    public MeetingRoom(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public Calendar getCalendar() { return calendar; }

    @Override
    public String toString() { return name + "(cap=" + capacity + ")"; }
}
