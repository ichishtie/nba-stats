package org.ichishtie.nbastatsscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsService {
    private final PlayerStatsRepository repository;

    @Autowired
    public PlayerStatsService(PlayerStatsRepository repository) {
        this.repository = repository;
    }

    public List<PlayerStats> list() {
        return repository.findAll();
    }

    public void save(PlayerStats stats) {
        repository.save(stats);
    }

    public List<PlayerStats> findByName(String name) {
        return repository.findByName(name);
    }
}
