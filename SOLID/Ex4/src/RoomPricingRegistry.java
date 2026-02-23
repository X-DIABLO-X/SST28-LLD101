import java.util.LinkedHashMap;
import java.util.Map;

public class RoomPricingRegistry {

    // Preserves insertion order; fallback strategy must be registered last
    private final Map<Integer, RoomPricingStrategy> registry = new LinkedHashMap<>();
    private RoomPricingStrategy                     fallback;

    public void register(int roomTypeCode, RoomPricingStrategy strategy) {
        registry.put(roomTypeCode, strategy);
    }

    /** Fallback used when no specific strategy matches the room type code. */
    public void setFallback(RoomPricingStrategy strategy) {
        this.fallback = strategy;
    }

    public RoomPricingStrategy getFor(int roomTypeCode) {
        RoomPricingStrategy s = registry.get(roomTypeCode);
        if (s != null) return s;
        if (fallback != null) return fallback;
        throw new IllegalArgumentException("No pricing strategy for room type: " + roomTypeCode);
    }
}
