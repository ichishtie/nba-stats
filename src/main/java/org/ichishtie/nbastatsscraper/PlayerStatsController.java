package org.ichishtie.nbastatsscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class PlayerStatsController {
    private final PlayerStatsService playerStatsService;

    @Autowired
    public PlayerStatsController(PlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
    }

    @GetMapping("/players/{name}")
    public ResponseEntity<List<PlayerStats>> findByName(@PathVariable String name) {
        System.out.printf("Searching for %s...\n", name);

        List<PlayerStats> stats = playerStatsService.findByName(name);
        if (stats.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(playerStatsService.findByName(name), HttpStatus.OK);
    }
}
