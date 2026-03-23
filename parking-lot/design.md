# Parking Lot Design

## ASCII Class Diagram

```
                        +---------------------+
                        |     ParkingLot      |  (Aggregate Root)
                        |---------------------|
                        | -floors: List       |
                        | -entryGates: Map    |
                        | -exitGates: Map     |
                        | -activeTickets: Map |
                        | -slotStrategy       |
                        | -pricingStrategy    |
                        |---------------------|
                        | +park(vehicle,...)  |
                        | +status()           |
                        | +exit(ticket,...)   |
                        +--+------+-------+---+
                           |      |       |
               +-----------+      |       +------------+
               |                  |                    |
               | composition      |                    | composition
               v                  v                    v
       +-------+-----+    +-------+-------+    +-------+-------+
       |    Floor    |    |  EntryGate    |    |  ExitGate     |
       |-------------|    |---------------|    |---------------|
       | -floorNo    |    | -gateId       |    | -gateId       |
       | -slots:List |    |               |    |               |
       +------+------+    | +genTicket()  |    | +genBill()    |
              |           +---------------+    +---------------+
              | composition
              v
       +------+------+
       |    Slot     |
       |-------------|
       | -slotId     |
       | -floorNo    |
       | -slotType   |
       | -available  |
       | -distances  |  <-- Map<gateId, distance>
       +-------------+

       +-------------+         +-------------+
       |   Vehicle   |         |   Ticket    |
       |-------------|         |-------------|
       | -vehicleId  |         | -ticketId   |
       | -type       |         | -entryTime  |
       |             |         | -vehicle    |
       | +minSlotType|         | -slot       |
       +-------------+         | -gateId     |
                               +------+------+
                                      |
                               +------v------+
                               |    Bill     |
                               |-------------|
                               | -ticketId   |
                               | -amount     |
                               | -exitTime   |
                               +-------------+

  Strategy Interfaces:
  +-------------------------------+    +-----------------------------+
  |  <<interface>>                |    |  <<interface>>              |
  |  SlotAllocationStrategy       |    |  PricingStrategy            |
  |-------------------------------|    |-----------------------------|
  | +findSlot(gateId, vehicle,    |    | +computeAmount(ticket,      |
  |           slotType, floors)   |    |               exitTime)     |
  | +onSlotOccupied(slot)         |    +-------------+---------------+
  | +onSlotFreed(slot)            |                  |
  +---------------+---------------+                  |
                  |                       +-----------v-----------+
                  |                       | HourlyPricingStrategy |
       +----------v------------+          |-----------------------|
       | NearestSlotStrategy   |          | rates: Map<SlotType,  |
       |-----------------------|          |         hourlyRate>   |
       | treesets:             |          +-----------------------+
       |  Map<gateId,          |
       |   TreeSet<Slot>>      |
       | (sorted by distance)  |
       +-----------------------+

  Enums:
  VehicleType: BIKE, CAR, BUS
  SlotType:    SMALL, MEDIUM, LARGE

  Compatibility:
  BIKE  -> SMALL, MEDIUM, LARGE
  CAR   -> MEDIUM, LARGE
  BUS   -> LARGE only
```

## Design Patterns Used

### Strategy Pattern (SlotAllocationStrategy + PricingStrategy)
The two most volatile behaviors — *how to pick a slot* and *how to compute the bill* — are hidden behind interfaces. `ParkingLot` only calls `findSlot()` and `computeAmount()`; it has no idea about TreeSets or hourly rates. Swapping to a VIP-first allocation or a weekend-surge pricing strategy requires writing one new class, not touching the orchestrator.

### Factory Pattern (ParkingLotFactory)
Building a `ParkingLot` requires wiring floors, slots, gates, distance maps, and strategies together. `ParkingLotFactory` owns that complexity so client code stays clean.

### Composite / Hierarchical Ownership (Composition)
`ParkingLot` → `Floor` → `Slot` forms a strict composition chain. A slot cannot exist without a floor, and a floor cannot exist without the lot. This models physical reality directly and keeps the aggregate boundary clear for operations like `status()`.
