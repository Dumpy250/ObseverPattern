# Observer-Pattern

Repository: `ObseverPattern`.

A Java console demonstration of custom **subject/observer callbacks** using synthetic basketball scores. A scoring subject notifies prediction, score-tracking and news observers. It does not consume live sports data or implement an evaluated forecasting model.

## Structure and behavior

| File in `src/` | Role |
|---|---|
| `BasketballGameSimulation.java` | Menu and main entrypoint; registers the three observers |
| `BasketballSubject.java` | Subject operations, including registration and notification |
| `BasketballObserver.java` | `update(int teamAScore, int teamBScore)` contract |
| `BasketballScoring.java` | Scores, simulated updates and in-memory score history |
| `PredictionObserver.java` | Random score guesses and update-driven counters |
| `ScoreTrackingObserver.java` | Counts observer notifications, despite its “Total Games” label |
| `NewsGenerationObserver.java` | Prints an update message; no actual news generation |

Selecting **Start a new game** archives previous nonzero scores, clears the scores and notifies observers. **Simulate Quarter** actually loops through four quarters: each input line other than `end` adds a random 0–19 points to each team and triggers updates. Enter `end` for each quarter to advance.

Prediction values are random integers from 0–100. A static flag allows generation only once per process; later new-game/quarter calls do not refresh them. Printed prediction statistics mix update-level errors with game counters and are not accuracy measurements for a forecasting system.

## Source-checked usage

A JDK with `javac` and `java` is required. Source imports use only standard JDK classes; no Maven/Gradle build is supplied. IntelliJ metadata mixes language level 21 with SDK name 17, so it does not establish a reliable minimum or verified runtime. Use a maintained JDK and check compatibility separately.

**Unexecuted PowerShell instructions**, from the repository root:

```powershell
New-Item -ItemType Directory -Force -Path .readme-build | Out-Null
$sources = (Get-ChildItem src -Filter *.java).FullName
javac -d .readme-build $sources
```

After compilation succeeds:

```powershell
java -cp .readme-build BasketballGameSimulation
```

Compilation writes temporary classes; launching runs an interactive, randomized console simulation. Do not commit the temporary output folder.

**Menu mismatch:** the displayed menu says **8. Exit**, but the switch implements exit as **7**. Entering `7` prints score-tracking statistics and terminates; `8` follows the invalid-choice branch.

## Limitations

- All scores, history and counters are in memory; no file output, GUI, network service or live-data connection is implemented.
- Score history is recorded when a new game begins, not automatically as a completed-game ledger.
- Multiple scanners share console input, and nonnumeric menu input has no recovery handler. Even a blank line inside the simulation counts as a scoring update unless it equals `end`.
- “News” is a placeholder message. Random predictions and the printed counters must not be presented as measured forecasting performance.
- No automated tests or verified simulation results are supplied.
