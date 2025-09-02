package si.paurus.assignment.tasktwo.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MatchEventEntity {
    @Id
    private Long matchId;

    private Long marketId;
    private Long outcomeId;


    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }
}
