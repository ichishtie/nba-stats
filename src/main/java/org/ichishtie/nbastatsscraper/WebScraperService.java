package org.ichishtie.nbastatsscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class WebScraperService {
    private final PlayerStatsService playerStatsService;

    @Autowired
    public WebScraperService(PlayerStatsService playerStatsService) {
        System.out.println("Inside constructor");
        this.playerStatsService = playerStatsService;

        System.out.println("Constructor finished");
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.MINUTES)
    public void scrapePlayerStats() {
        System.out.println("Starting player stats scraping");

        WebDriver driver = null;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // bypass OS security model
        options.addArguments("--headless"); // run in headless mode

        try {
            System.out.println("Instantiating driver");
            int retryCount = 1;
            while (driver == null && retryCount <= 10) {
                System.out.printf("Try #%d\n", retryCount);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                            options);
                } catch (SessionNotCreatedException e) {
                    System.out.println(e.getLocalizedMessage());
                    retryCount++;
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            assert driver != null : "Driver is null";
            System.out.println("Driver instantiated");

            System.out.println("Getting https://www.nba.com/stats/leaders");
            driver.get("https://www.nba.com/stats/leaders");
            System.out.println("Success");

            System.out.println("Show totals");
            new Select(driver.findElements(By.className("DropDown_select__4pIg9")).get(2))
                    .selectByVisibleText("Totals");

            System.out.println("Show all rows");
            new Select(driver.findElement(By.className("Crom_cromSetting__Tqtiq"))
                    .findElement(By.className("DropDown_select__4pIg9")))
                    .selectByVisibleText("All");

            // Pick up stats per player
            var playerElements = driver.findElements(By.cssSelector("tbody.Crom_body__UYOcU" + ">*"));

            playerElements.parallelStream().forEach(playerElement -> {
                List<WebElement> statisticsElements = playerElement.findElements(By.cssSelector("*"));

                try {
                    var name = statisticsElements.get(1).getText();
                    var team = statisticsElements.get(3).getText();
                    var games = Integer.parseInt(statisticsElements.get(5).getText());
                    var minutes = Integer.parseInt(statisticsElements.get(6).getText());
                    var points = Integer.parseInt(statisticsElements.get(7).getText());
                    var fieldGoalsMade = Integer.parseInt(statisticsElements.get(8).getText());

                    var itr = 9;

                    if (fieldGoalsMade != 0) itr += 1;
                    var fieldGoalsAttempted =
                            Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (fieldGoalsAttempted != 0) itr += 1;
                    var fieldGoalPercentage =
                            Float.parseFloat(statisticsElements.get(itr++).getText());

                    var threePointersMade =
                            Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (threePointersMade != 0) itr += 1;
                    var threePointersAttempted =
                            Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (threePointersAttempted != 0) itr += 1;
                    var threePointPercentage =
                            Float.parseFloat(statisticsElements.get(itr++).getText());

                    var freeThrowsMade = Integer.parseInt(statisticsElements.get(itr++).getText());
                    var freeThrowsAttempted =
                            Integer.parseInt(statisticsElements.get(itr++).getText());
                    var freeThrowPercentage = Float.parseFloat(statisticsElements.get(itr++).getText());
                    var offensiveRebounds = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (offensiveRebounds != 0) itr += 1;
                    var defensiveRebounds = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (defensiveRebounds != 0) itr += 1;
                    var rebounds = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (rebounds != 0) itr += 1;
                    var assists = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (assists != 0) itr += 1;
                    var steals = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (steals != 0) itr += 1;
                    var blocks = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (blocks != 0) itr += 1;
                    var turnovers = Integer.parseInt(statisticsElements.get(itr++).getText());

                    if (turnovers != 0) itr += 1;
                    var personalFouls = Integer.parseInt(statisticsElements.get(itr++).getText());

                    var efficiency = Integer.parseInt(statisticsElements.get(itr++).getText());
                    var assistsToTurnovers = Float.parseFloat(statisticsElements.get(itr++).getText());
                    var stealsToTurnovers = Float.parseFloat(statisticsElements.get(itr).getText());

                    PlayerStats stats = new PlayerStats(name, team, games, minutes, points,
                            fieldGoalsMade, fieldGoalsAttempted, fieldGoalPercentage, threePointersMade,
                            threePointersAttempted, threePointPercentage, freeThrowsMade, freeThrowsAttempted,
                            freeThrowPercentage, offensiveRebounds, defensiveRebounds, rebounds, assists, steals, blocks, turnovers,
                            personalFouls, efficiency, assistsToTurnovers, stealsToTurnovers);

                    System.out.printf("Saving stats for %s\n", name);
                    playerStatsService.save(stats);
                    System.out.printf("Saved stats for %s\n", name);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data received!\nContinuing scraping");
                }
            });
        } finally {
            System.out.println("Quitting driver");
            if (driver != null) driver.quit();
        }

        System.out.println("Scraping completed!");
    }
}
