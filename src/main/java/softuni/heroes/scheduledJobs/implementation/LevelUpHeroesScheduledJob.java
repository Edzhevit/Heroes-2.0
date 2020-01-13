package softuni.heroes.scheduledJobs.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import softuni.heroes.scheduledJobs.ScheduledJob;
import softuni.heroes.service.services.HeroesService;

public class LevelUpHeroesScheduledJob implements ScheduledJob {

    private final HeroesService heroesService;

    @Autowired
    public LevelUpHeroesScheduledJob(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @Override
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void scheduledJob() {
        heroesService.levelUpHeroes();
    }
}
