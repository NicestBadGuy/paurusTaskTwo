package si.paurus.assignment.tasktwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import si.paurus.assignment.tasktwo.model.TestEventBO;
import si.paurus.assignment.tasktwo.service.MatchEventResultKeeperService;

import java.time.OffsetDateTime;

public class MatchEventResultKeeperServiceTest {
    @Test
    void happyFlow()  {
        var resultKeeperService = new MatchEventResultKeeperService();

        var matchId = "matchId";
        // Check initialization is ok
        Assertions.assertTrue(resultKeeperService.getEventsByMatchId(matchId).isEmpty());
        // Check first insertions
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 2, OffsetDateTime.now()));
        Assertions.assertTrue(resultKeeperService.getEventsByMatchId(matchId).isEmpty());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 1, OffsetDateTime.now()));
        Assertions.assertFalse(resultKeeperService.getEventsByMatchId(matchId).isEmpty());
        Assertions.assertEquals(2, resultKeeperService.getEventsByMatchId(matchId).size());

        // Checking that sequence counter works correctly
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 5, OffsetDateTime.now()));
        Assertions.assertEquals(2, resultKeeperService.getEventsByMatchId(matchId).size());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 4, OffsetDateTime.now()));
        Assertions.assertEquals(2, resultKeeperService.getEventsByMatchId(matchId).size());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 3, OffsetDateTime.now()));
        Assertions.assertEquals(5, resultKeeperService.getEventsByMatchId(matchId).size());

        // Checking that another event type works the same
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 2, 8, OffsetDateTime.now()));
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 2, 7, OffsetDateTime.now()));
        Assertions.assertEquals(5, resultKeeperService.getEventsByMatchId(matchId).size());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 2, 6, OffsetDateTime.now()));
        Assertions.assertEquals(8, resultKeeperService.getEventsByMatchId(matchId).size());

        // Checking that limit is respected
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 9, OffsetDateTime.now()));
        Assertions.assertEquals(9, resultKeeperService.getEventsByMatchId(matchId).size());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 10, OffsetDateTime.now()));
        Assertions.assertEquals(10, resultKeeperService.getEventsByMatchId(matchId).size());
        resultKeeperService.insertIntoResultKeeper(new TestEventBO(matchId, 1, 11, OffsetDateTime.now()));
        Assertions.assertEquals(10, resultKeeperService.getEventsByMatchId(matchId).size());
    }
}
