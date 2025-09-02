package si.paurus.assignment.tasktwo.util;

import si.paurus.assignment.tasktwo.repository.MatchEventEntity;

import java.util.Optional;
import java.util.regex.Pattern;

public class RawLineMapper {

    private static final Pattern LINE = Pattern
            .compile("^'(?<matchId>[^']+)'\\|(?<marketId>\\d+)\\|'(?<outcomeId>[^']+)'\\|(?:'(?<spec>[^']*)')?$");

    public static Optional<MatchEventEntity> toEntity(String rawLine) {
        var matcher = LINE.matcher(rawLine);
        if (!matcher.matches()) {
            System.err.println("Couldn't parse " + rawLine);
            return Optional.empty();
        }
        var to = new MatchEventEntity();
        to.setMatchId(matcher.group("matchId"));
        to.setMarketId(Integer.parseInt(matcher.group("marketId")));
        to.setOutcomeId(matcher.group("outcomeId"));
        to.setSpecifiers(matcher.group("spec"));
        return Optional.of(to);
    }
}
