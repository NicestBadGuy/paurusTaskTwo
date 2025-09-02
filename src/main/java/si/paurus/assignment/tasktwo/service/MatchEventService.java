package si.paurus.assignment.tasktwo.service;

import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.model.EventBO;
import si.paurus.assignment.tasktwo.model.NewTestEventBO;
import si.paurus.assignment.tasktwo.model.TestEventBO;
import si.paurus.assignment.tasktwo.repository.MatchEventEntity;
import si.paurus.assignment.tasktwo.repository.MatchEventJpaRepo;
import si.paurus.assignment.tasktwo.repository.MatchTestEventEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class MatchEventService {
    private final MatchEventJpaRepo matchEventJpaRepo;
    private final MatchEventResultKeeperService matchEventResultKeeperService;
    private final MatchEventRepositoryService matchEventRepositoryService;

    public MatchEventService(
            MatchEventJpaRepo matchEventJpaRepo,
            MatchEventResultKeeperService matchEventResultKeeperService,
            MatchEventRepositoryService matchEventRepositoryService) {
        this.matchEventJpaRepo = matchEventJpaRepo;
        this.matchEventRepositoryService = matchEventRepositoryService;
        this.matchEventResultKeeperService = matchEventResultKeeperService;
    }

    public void addNewEvent(NewTestEventBO newEvent) {
        matchEventResultKeeperService.insertIntoResultKeeper(toTestEventBO(newEvent));
        matchEventRepositoryService.saveAsync(toMatchEventEntity(newEvent));
    }

    public void addNewEvents(List<NewTestEventBO> newEvents) {
        newEvents.forEach(newEvent -> {
            matchEventResultKeeperService.insertIntoResultKeeper(toTestEventBO(newEvent));
            matchEventRepositoryService.saveAsync(toMatchEventEntity(newEvent));
        });
    }

    public List<EventBO> getByMatchId(String matchId) {
        return matchEventJpaRepo.findMatchEventEntitiesByMatchIdOrderByDateInsert(matchId).stream().map(MatchEventService::fromMatchEventEntity).toList();
    }


    public List<TestEventBO> getTestByMatchId(String matchId) {
        return matchEventResultKeeperService.getEventsByMatchId(matchId);
    }

    // Would move bottom two method to some mapping implementation
    private static TestEventBO toTestEventBO(NewTestEventBO from) {
        return new TestEventBO(from.matchId(), from.eventType(), from.eventSeq(), OffsetDateTime.now());
    }

    private static MatchTestEventEntity toMatchEventEntity(NewTestEventBO from) {
        var to = new MatchTestEventEntity();
        to.setMatchId(from.matchId());
        to.setEventType(from.eventType());
        to.setEventSequence(from.eventSeq());
        return to;
    }

    private static EventBO fromMatchEventEntity(MatchEventEntity from) {
        return new EventBO(from.getMatchId(), from.getMarketId(), from.getOutcomeId(), from.getSpecifiers(), from.getDateInsert());
    }
}
