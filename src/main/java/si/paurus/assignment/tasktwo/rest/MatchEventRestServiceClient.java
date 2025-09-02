package si.paurus.assignment.tasktwo.rest;

import si.paurus.assignment.tasktwo.model.EventBO;
import si.paurus.assignment.tasktwo.model.NewEventBO;

import java.util.List;

public interface MatchEventRestServiceClient {
    void addRawLine(String rawLine);
    void addEvent(NewEventBO newEvent);
    void addEvents(List<NewEventBO> newEvents);
    List<EventBO> getByMatchId(String matchId);
}
