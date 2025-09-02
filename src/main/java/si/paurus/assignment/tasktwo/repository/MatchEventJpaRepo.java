package si.paurus.assignment.tasktwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchEventJpaRepo extends JpaRepository<MatchEventEntity, UUID> {

    List<MatchEventEntity> findMatchEventEntitiesByMatchIdOrderByDateInsert(String matchId);
}
