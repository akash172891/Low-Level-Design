package Calendar;

import Entity.TimeSlot;
import java.util.ArrayList;
import java.util.List;

// Owns booked slots and answers "is this slot free?" (SRP: conflict detection).
public class Calendar {
    private final List<TimeSlot> bookings = new ArrayList<>();

    public boolean isAvailable(TimeSlot slot) {
        for (TimeSlot booked : bookings) {
            if (booked.overlaps(slot)) return false;
        }
        return true;
    }

    public boolean book(TimeSlot slot) {
        if (!isAvailable(slot)) return false;
        bookings.add(slot);
        return true;
    }

    public void release(TimeSlot slot) {
        bookings.remove(slot);
    }
}
