package Strategy;

import Entity.MeetingRoom;
import java.util.List;

// Picks the first room that fits (simple default strategy).
public class FirstAvailableStrategy implements RoomSelectionStrategy {
    @Override
    public MeetingRoom select(List<MeetingRoom> availableRooms) {
        return availableRooms.isEmpty() ? null : availableRooms.get(0);
    }
}
