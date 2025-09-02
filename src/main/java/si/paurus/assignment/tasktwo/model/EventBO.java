package si.paurus.assignment.tasktwo.model;

import java.time.OffsetDateTime;


public record EventBO(String matchId, Integer marketId, String outcomeId, String specifiers, OffsetDateTime dateInserted) {
}