package si.paurus.assignment.tasktwo.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import si.paurus.assignment.tasktwo.model.EventBO;
import si.paurus.assignment.tasktwo.service.DataInsertionService;
import si.paurus.assignment.tasktwo.service.MatchEventService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/match")
public class MatchEventRestService implements MatchEventRestServiceClient<String, EventBO> {
    private final DataInsertionService dataInsertionService;
    private final MatchEventService matchEventService;

    public MatchEventRestService(DataInsertionService dataInsertionService, MatchEventService matchEventService) {
        this.dataInsertionService = dataInsertionService;
        this.matchEventService = matchEventService;
    }

    @PostMapping(path = "/event/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Method reads from supplied file and starts inserting into database.")
    public void startAutomatedInsertion(@RequestParam("file") MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("events-", ".txt");
        file.transferTo(tempFile);
        dataInsertionService.startDataInsertion(tempFile);
    }

    @Override
    @PostMapping(path = "/event/raw", consumes = "text/plain")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void addEvent(@RequestBody String rawLine) {
    }


    @Override
    @PostMapping(path = "/event/raws", consumes = "application/json")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void addEvents(@RequestBody List<String> rawLines) {
    }

    @Override
    @GetMapping(path = "/{matchId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<EventBO> getByMatchId(@PathVariable String matchId) {
        return matchEventService.getByMatchId(matchId);
    }
}
