package si.paurus.assignment.tasktwo.service;

import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.repository.MatchEventJpaRepo;
import si.paurus.assignment.tasktwo.rest.MatchEventResultKeeperService;
import si.paurus.assignment.tasktwo.util.RawLineParser;

import java.util.List;

@Service
public class MatchEventService {
    private final MatchEventJpaRepo matchEventRepo;

    private final MatchEventResultKeeperService matchEventResultKeeperService;

    public MatchEventService(MatchEventJpaRepo matchEventRepo, MatchEventResultKeeperService matchEventResultKeeperService) {
        this.matchEventRepo = matchEventRepo;
        this.matchEventResultKeeperService = matchEventResultKeeperService;
    }

    public void addRawLine(String rawLine) {
        var result = RawLineParser.parse(rawLine);
    }

    public void addRawLines(List<String> rawLines) {
        var results = rawLines.stream().map(RawLineParser::parse).toList();
    }
}
