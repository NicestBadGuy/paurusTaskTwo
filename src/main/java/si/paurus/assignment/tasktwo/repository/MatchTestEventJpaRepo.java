package si.paurus.assignment.tasktwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchTestEventJpaRepo extends JpaRepository<MatchTestEventEntity, UUID> {
}
