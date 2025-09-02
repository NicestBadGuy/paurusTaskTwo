package si.paurus.assignment.tasktwo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.paurus.assignment.tasktwo.model.NewTestEventBO;
import si.paurus.assignment.tasktwo.model.TestEventBO;
import si.paurus.assignment.tasktwo.service.MatchEventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testMatch")
public class MatchEventTestRestService implements MatchEventRestServiceClient<NewTestEventBO, TestEventBO> {
    private final MatchEventService matchEventService;

    public MatchEventTestRestService(MatchEventService matchEventService) {
        this.matchEventService = matchEventService;
    }

    @Override
    @PostMapping(path = "/event", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvent(@RequestBody NewTestEventBO newEvent) {
        matchEventService.addNewEvent(newEvent);

    }

    @Override
    @PostMapping(path = "/events", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEvents(@RequestBody List<NewTestEventBO> newEvents) {
        matchEventService.addNewEvents(newEvents);
    }

    @Override
    @GetMapping(path = "/{matchId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<TestEventBO> getByMatchId(@PathVariable String matchId) {
        return matchEventService.getTestByMatchId(matchId);
    }
}
