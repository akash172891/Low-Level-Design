import Entity.*;
import Strategy.FirstAvailableStrategy;
import Strategy.MinCapacityStrategy;

import java.util.Arrays;
import java.util.List;

// Demo: schedule with conflict + room strategy switch.
public class Main {
    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler(new MinCapacityStrategy());

        scheduler.addRoom(new MeetingRoom("R1", "Small", 2));
        scheduler.addRoom(new MeetingRoom("R2", "Large", 5));

        User alice = new User("U1", "Alice");
        User bob = new User("U2", "Bob");
        User carol = new User("U3", "Carol");

        TimeSlot morning = new TimeSlot(10, 11);
        TimeSlot noon = new TimeSlot(11, 12);

        // Best-fit: 2 people → Small room
        scheduler.schedule("Standup", alice, List.of(bob), morning);

        // Conflict: Alice already booked 10-11
        scheduler.schedule("Overlap", bob, List.of(alice), morning);

        // Needs capacity 3 → Large room
        scheduler.schedule("Planning", alice, Arrays.asList(bob, carol), noon);

        // Switch strategy and book another slot
        scheduler.setStrategy(new FirstAvailableStrategy());
        scheduler.schedule("Sync", bob, List.of(carol), new TimeSlot(14, 15));
    }
}
