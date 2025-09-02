package si.paurus.assignment.tasktwo.repository;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_match_id", columnList = "matchId"),
                @Index(name = "idx_match_seq", columnList = "matchId, eventSeq", unique = true)
        }
)
public class MatchTestEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dateInsert;

    // Intentionally not using lombok
    private String matchId;
    private Integer eventType;
    private Integer eventSequence;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer marketId) {
        this.eventType = marketId;
    }

    public Integer getEventSequence() {
        return eventSequence;
    }

    public void setEventSequence(Integer outcomeId) {
        this.eventSequence = outcomeId;
    }

    @PrePersist
    public void prePersist() {
        dateInsert = OffsetDateTime.now();
    }
}
