package si.paurus.assignment.tasktwo.rest;

import java.util.List;

public interface MatchEventRestServiceClient<C, R> {
    void addEvent(C newEvent);
    void addEvents(List<C> newEvents);
    List<R> getByMatchId(String matchId);
}
