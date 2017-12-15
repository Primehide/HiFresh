package be.kdg.hiFresh.domain.managers;

import be.kdg.hiFresh.domain.recept.Week;
import be.kdg.hiFresh.domain.recept.WeekAanbod;
import be.kdg.hiFresh.persistentie.WeekAanbodMemoryRepo;
import org.threeten.extra.YearWeek;

import java.util.LinkedList;
import java.util.List;

public class WeekAanbodManager {
    private WeekAanbodMemoryRepo repo;

    public WeekAanbodManager(){
        repo = new WeekAanbodMemoryRepo();
    }

    public List<WeekAanbod> getLijstWeekAanbod(Week week, int size){
        List<WeekAanbod> aankomendeAanbiedingen = new LinkedList<WeekAanbod>();
        for (WeekAanbod wa : repo.getAlleAanbiedingen()){
            for(int i = 1; i < size; i++){
                if(wa.getWeek().getIntWeek() == week.getIntWeek() || wa.getWeek().getIntWeek() == week.getIntWeek() + i){
                    aankomendeAanbiedingen.add(wa);
                }
            }
        }
        return aankomendeAanbiedingen;
    }

    public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
        repo.setAlleAanbiedingen(planning);
    }
}
