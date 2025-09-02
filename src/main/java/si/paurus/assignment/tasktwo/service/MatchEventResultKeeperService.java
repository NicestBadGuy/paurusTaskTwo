package si.paurus.assignment.tasktwo.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.model.TestEventBO;

import java.time.OffsetDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MatchEventResultKeeperService {
    private static final int CACHED_LIMIT = 10;

    private final ConcurrentHashMap<String, CachedResult> cachedResults = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IntermediateValueKey, TestEventBO> cachedIntermediateResult = new ConcurrentHashMap<>();

    // Inside constructor a procedure to build beginning state (reading from db or something similar)

    @Cacheable("eventsByMatchId")
    public List<TestEventBO> getEventsByMatchId(String matchId) {
        System.out.println("Getting not cached");
        // We have to make sure this is not changed by the caller (if we are not sure, we can create copy when returning)
        return new ArrayList<TestEventBO>(getCachedResult(matchId).result);
    }

    private CachedResult getCachedResult(String matchId) {
        return cachedResults.computeIfAbsent(matchId, id -> new CachedResult());
    }

    @Async
    @CacheEvict(value = "eventsByMatchId", key = "#event.matchId()")
    public void insertIntoResultKeeper(TestEventBO event) {
        var matchId = event.getMatchId();
        var sequence = event.getEventSeq();
        var cachedResult = getCachedResult(matchId);
        if (sequence - cachedResult.lastInsertedSequence != 1) {
            // Assumption: only be called once or if it is called multiple times that it always contains same event
            cachedIntermediateResult.putIfAbsent(new IntermediateValueKey(matchId, sequence), event);
            return;
        }
        // Presentation and testing purposes
        if (event.getEventType() == 369) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        event.setAddedToResult(OffsetDateTime.now());
        cachedResult.result.add(event);
        cachedResult.lastInsertedSequence++;
        // Check if there are values in cachedIntermediateResult that should be entered
        TestEventBO loop;
        do {
            sequence++;
            loop = cachedIntermediateResult.remove(new IntermediateValueKey(matchId, sequence));
            if (loop != null) {
                if (loop.getEventType() == 369) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                loop.setAddedToResult(OffsetDateTime.now());
                cachedResult.result.addLast(loop);
                // Probably some better way to not increase twice this and sequence
                cachedResult.lastInsertedSequence++;
            }
        } while (loop != null);
        trim(cachedResult);
    }



    private void trim(CachedResult state) {
        while (state.result.size() > CACHED_LIMIT) state.result.removeFirst();
    }

    private static final class CachedResult {
        final Deque<TestEventBO> result;
        long lastInsertedSequence;

        CachedResult() {
            result = new ArrayDeque<>(CACHED_LIMIT);
            lastInsertedSequence = 0L;
        }
    }

    private record IntermediateValueKey(String matchId, Integer sequence) {
    }

}
