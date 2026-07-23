package Strategy;

import Entity.MeetingRoom;
import java.util.Comparator;
import java.util.List;

// Picks smallest room that fits capacity — reduces waste (best-fit).
public class MinCapacityStrategy implements RoomSelectionStrategy {
    @Override
    public MeetingRoom select(List<MeetingRoom> availableRooms) {
        return availableRooms.stream()
                .min(Comparator.comparingInt(MeetingRoom::getCapacity))
                .orElse(null);
    }
}
