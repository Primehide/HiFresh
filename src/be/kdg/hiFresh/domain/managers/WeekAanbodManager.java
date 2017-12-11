package be.kdg.hiFresh.domain.managers;

import be.kdg.hiFresh.domain.recept.Week;
import be.kdg.hiFresh.domain.recept.WeekAanbod;
import be.kdg.hiFresh.persistentie.WeekAanbodMemoryRepo;
import java.util.LinkedList;
import java.util.List;

public class WeekAanbodManager {
    private WeekAanbodMemoryRepo repo;

    public WeekAanbodManager(){
        repo = new WeekAanbodMemoryRepo();
    }

    public List<WeekAanbod> getLijstWeekAanbod(Week week){
        //TODO weekaanbiedingen van 2 weken ophalen (huidige en de week erna);
        List<WeekAanbod> aankomendeAanbiedingen = new LinkedList<WeekAanbod>();
        for (WeekAanbod wa : repo.getAlleAanbiedingen()){
            if(wa.getWeek()== week || wa.getWeek().getIntWeek() == week.getIntWeek() + 1){
                aankomendeAanbiedingen.add(wa);
            }
        }
        return aankomendeAanbiedingen;
    }

    public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
        repo.setAlleAanbiedingen(planning);
    }
}
