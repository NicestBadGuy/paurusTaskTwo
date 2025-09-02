package si.paurus.assignment.tasktwo.service;

import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import si.paurus.assignment.tasktwo.repository.MatchTestEventEntity;
import si.paurus.assignment.tasktwo.repository.MatchTestEventJpaRepo;

@Service
public class MatchEventRepositoryService {
    private final MatchTestEventJpaRepo matchTestEventJpaRepo;

    public MatchEventRepositoryService(MatchTestEventJpaRepo matchTestEventJpaRepo) {
        this.matchTestEventJpaRepo = matchTestEventJpaRepo;
    }

    @Async
    @Transactional
    public void saveAsync(MatchTestEventEntity e) {
        matchTestEventJpaRepo.save(e);
    }

}
