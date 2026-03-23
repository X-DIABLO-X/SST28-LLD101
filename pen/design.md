# Pen Design

## ASCII Class Diagram

```
                    +-----------------+
                    |   PenFactory    |  (Static Factory)
                    |-----------------|
                    | +getPen(type,   |
                    |   color, mech)  |
                    +-------+---------+
                            |
                            | creates
                            v
                    +-----------------+
                    |    <<abstract>> |
                    |      Pen        |  (Template Method)
                    |-----------------|
                    | -type: PenType  |
                    | -inkColor: str  |
                    | -inkLevel: dbl  |
                    | -started: bool  |
                    |-----------------|
                    | +start()        |
                    | +write(text)    |
                    | +close()        |
                    | +refill(color)  |
                    | #getWritingPrefix() <<abstract>>
                    | #getInkConsumptionRate() <<abstract>>
                    +--+----------+---+
                       |          |
             +---------+          +---------+
             |                              |
     delegates to                     delegates to
             |                              |
  +----------v----------+     +------------v--------+
  |  <<interface>>      |     |  <<interface>>      |
  |   StartStrategy     |     |   RefillStrategy    |
  |---------------------|     |---------------------|
  | +start(pen)         |     | +refill(pen, color) |
  | +close(pen)         |     +----------+----------+
  | +mechanismName()    |                |
  +----------+----------+      +---------+---------+
             |                 |                   |
    +--------+--------+  +-----v------+    +-------v------+
    |                 |  |InkFillRefill|   |CartridgeRefill|
    | CapStartStrategy|  |(Ballpoint,  |   |(Fountain only)|
    | ClickStartStrategy  Gel)         |   +--------------+
    +--------+--------+  +------------+
             |
             |
  +----------v----------+
  |   Concrete Pens     |
  |---------------------|
  | BallpointPen        |  ink rate: 0.5/char
  | GelPen              |  ink rate: 0.3/char
  | FountainPen         |  ink rate: 0.8/char
  +---------------------+

  Exception Hierarchy:
  PenException (base)
    ├── InvalidPenConfigurationException
    ├── InvalidRefillOperationException
    ├── OutOfInkException
    ├── PenAlreadyStartedException
    └── PenNotStartedException
```

## Design Patterns Used

### Strategy Pattern (StartStrategy + RefillStrategy)
Each pen delegates its start/close behavior to a `StartStrategy` and its refill behavior to a `RefillStrategy`. This means the same pen type can use either a cap or a click mechanism without changing the `Pen` class. New mechanisms (e.g., twist) can be added without touching existing code.

### Template Method Pattern (Pen abstract class)
`Pen` defines the fixed skeleton of `write()` — validate state, check ink, consume ink, display output — but delegates `getInkConsumptionRate()` and `getWritingPrefix()` to subclasses. This enforces consistent write logic across all pen types while letting each type vary only what it needs to.

### Factory Pattern (PenFactory)
`PenFactory` centralizes the wiring of pen type → concrete class, mechanism → start strategy, and pen type → refill strategy. The caller just provides strings (`"GEL"`, `"CLICK"`); the factory handles all the `new` calls. This isolates object construction from usage.
