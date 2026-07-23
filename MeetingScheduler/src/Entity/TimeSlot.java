package Entity;

// Represents [start, end) interval and detects overlap with another slot.
public class TimeSlot {
    private final int start; // hour of day for demo simplicity (0-23)
    private final int end;

    public TimeSlot(int start, int end) {
        if (start >= end) throw new IllegalArgumentException("Invalid time slot");
        this.start = start;
        this.end = end;
    }

    public int getStart() { return start; }
    public int getEnd() { return end; }

    public boolean overlaps(TimeSlot other) {
        return this.start < other.end && other.start < this.end;
    }

    @Override
    public String toString() {
        return start + ":00-" + end + ":00";
    }
}
