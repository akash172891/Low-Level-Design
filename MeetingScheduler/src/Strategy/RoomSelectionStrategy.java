package Strategy;

import Entity.MeetingRoom;
import java.util.List;

// Strategy: pluggable rule for picking a room from available candidates (OCP).
public interface RoomSelectionStrategy {
    MeetingRoom select(List<MeetingRoom> availableRooms);
}
