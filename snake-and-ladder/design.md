# Snake and Ladder Design

## ASCII Class Diagram

```
                    +------------------+
                    |      Game        |  (Orchestrator)
                    |------------------|
                    | -board: Board    |
                    | -players: Queue  |
                    | -moveStrategy   |
                    | -winners: List   |
                    |------------------|
                    | +startGame()     |
                    | +makeMove()      |
                    +--+--------+------+
                       |        |
           +-----------+        +------------+
           |                                 |
           v                                 v
  +--------+---------+          +------------+----------+
  |      Board       |          |  <<interface>>        |
  |------------------|          |  MakeMoveStrategy     |
  | -size: int       |          |-----------------------|
  | -snakes: List    |          | +makeMove(player,     |
  | -ladders: List   |          |   diceValue, board)   |
  |                  |          +------------+----------+
  | +getLastCell()   |                       |
  | +getSnakeAt(pos) |            +----------+-----------+
  | +getLadderAt(pos)|            |                      |
  +------------------+            v                      v
           |             +--------+----------+  +--------+---------+
           |             |AllowConsecutive   |  |SkipOnThree       |
  +--------+--------+    |Sixes              |  |ConsecutiveSixes  |
  |                 |    |-------------------|  |------------------|
  |   Snake         |    | extra turn on 6   |  | skip turn on 3   |
  |-----------------|    +-------------------+  |  consecutive 6s  |
  | -start (head)   |                           +------------------+
  | -end   (tail)   |
  | start > end     |    +------------------+
  +-----------------+    |      Player      |
                         |------------------|
  +-----------------+    | -name: String    |
  |   Ladder        |    | -position: int   |
  |-----------------|    |                  |
  | -start (bottom) |    | +getPosition()   |
  | -end   (top)    |    | +setPosition()   |
  | start < end     |    +------------------+
  +-----------------+
                         +------------------+
  +------------------+   |   MoveResult     |
  |      Dice        |   |------------------|
  |------------------|   | -finalPosition   |
  | +roll(): int     |   | -note: String    |
  | (static, 1-6)    |   | -hasExtraTurn    |
  +------------------+   +------------------+

  Turn flow:
  Game.makeMove()
    -> Dice.roll()
    -> MakeMoveStrategy.makeMove(player, dice, board)
         -> compute raw position
         -> check snake at position  -> slide down if hit
         -> check ladder at position -> climb up if hit
         -> check extra turn rule
    -> player.setPosition(result.finalPosition)
    -> if position == lastCell -> player wins
    -> else re-queue player
```

## Design Patterns Used

### Strategy Pattern (MakeMoveStrategy)
The rules for *what happens after a dice roll* change per game variant — some give an extra turn on a 6, others skip a player after three consecutive 6s. `MakeMoveStrategy` encapsulates exactly that decision. `Game` has no if-else for rule variants; it just calls `moveStrategy.makeMove()`. New variants (e.g., double dice, bomb cell) are added as new strategy classes without touching `Game`.

### Value Object (MoveResult)
After a move, the strategy returns a `MoveResult` carrying the final position, a note for display, and whether an extra turn was earned. Bundling these into one immutable return value keeps the `makeMove` signature clean and avoids multiple return values or out-parameters.

### Queue-based Turn Management
Players are stored in a `LinkedList` used as a queue (poll → process → offer). This naturally models a circular turn order without index arithmetic. When a player wins, they are simply not re-offered back to the queue.
