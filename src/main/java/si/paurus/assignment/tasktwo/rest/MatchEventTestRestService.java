package si.paurus.assignment.tasktwo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.paurus.assignment.tasktwo.model.EventBO;
import si.paurus.assignment.tasktwo.model.NewEventBO;
import si.paurus.assignment.tasktwo.service.MatchEventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test/match")
public class MatchEventTestRestService implements MatchEventRestServiceClient {
    private final MatchEventService matchEventService;

    public MatchEventTestRestService(MatchEventService matchEventService) {
        this.matchEventService = matchEventService;
    }


    @Override
    @PostMapping(path = "/event/raw", consumes = "text/plain")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRawLine(@RequestBody String rawLine) {
        matchEventService.addRawLine(rawLine);
    }

    @PostMapping(path = "/event/raws", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRawLine(@RequestBody List<String> rawLines) {
        matchEventService.addRawLines(rawLines);
    }

    @Override
    @PostMapping(path = "/event", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvent(@RequestBody NewEventBO newEvent) {

    }

    @Override
    @PostMapping(path = "/events", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvents(@RequestBody List<NewEventBO> newEvents) {

    }

    @Override
    @GetMapping(path = "/{matchId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<EventBO> getByMatchId(@PathVariable String matchId) {
        return List.of();
    }
}
