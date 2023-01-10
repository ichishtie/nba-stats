package org.ichishtie.nbastatsscraper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player_stats")
public class PlayerStats {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String team;
    private int games;
    private int minutes;
    private int points;
    private int fieldGoalsMade;
    private int fieldGoalsAttempted;
    private float fieldGoalPercentage;
    private int threePointersMade;
    private int threePointersAttempted;
    private float threePointPercentage;
    private int freeThrowsMade;
    private int freeThrowsAttempted;
    private float freeThrowPercentage;
    private int offensiveRebounds;
    private int defensiveRebounds;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int turnovers;
    private int personalFouls;
    private int efficiency;
    private float assistsToTurnovers;
    private float stealsToTurnovers;

    public PlayerStats(String name, String team, int games, int minutes, int points,
                       int fieldGoalsMade, int fieldGoalsAttempted, float fieldGoalPercentage, int threePointersMade,
                       int threePointersAttempted, float threePointPercentage, int freeThrowsMade, int freeThrowsAttempted,
                       float freeThrowPercentage, int offensiveRebounds, int defensiveRebounds, int rebounds, int assists, int steals,
                       int blocks, int turnovers, int personalFouls, int efficiency, float assistsToTurnovers, float stealsToTurnovers) {
        this.name = name;
        this.team = team;
        this.games = games;
        this.minutes = minutes;
        this.points = points;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.threePointersMade = threePointersMade;
        this.threePointersAttempted = threePointersAttempted;
        this.threePointPercentage = threePointPercentage;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.freeThrowPercentage = freeThrowPercentage;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personalFouls = personalFouls;
        this.efficiency = efficiency;
        this.assistsToTurnovers = assistsToTurnovers;
        this.stealsToTurnovers = stealsToTurnovers;
    }

    public PlayerStats() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public void setFieldGoalsAttempted(int fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public float getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public void setFieldGoalPercentage(float fieldGoalPercentage) {
        this.fieldGoalPercentage = fieldGoalPercentage;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public void setThreePointersMade(int threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public void setThreePointersAttempted(int threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public float getThreePointPercentage() {
        return threePointPercentage;
    }

    public void setThreePointPercentage(float threePointPercentage) {
        this.threePointPercentage = threePointPercentage;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public float getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public void setFreeThrowPercentage(float freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }

    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(int defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public void setPersonalFouls(int personalFouls) {
        this.personalFouls = personalFouls;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public float getAssistsToTurnovers() {
        return assistsToTurnovers;
    }

    public void setAssistsToTurnovers(float assistsToTurnovers) {
        this.assistsToTurnovers = assistsToTurnovers;
    }

    public float getStealsToTurnovers() {
        return stealsToTurnovers;
    }

    public void setStealsToTurnovers(float stealsToTurnovers) {
        this.stealsToTurnovers = stealsToTurnovers;
    }
}
