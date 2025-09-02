package si.paurus.assignment.tasktwo.rest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.model.EventBO;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MatchEventResultKeeperService {
    private final ConcurrentHashMap<String, Deque<EventBO>> cachedResults = new ConcurrentHashMap<>();


    public Deque<EventBO> getEventsByMatchId(String matchId) {
        return cachedResults.computeIfAbsent(matchId, id -> new ArrayDeque<>());
    }

}
