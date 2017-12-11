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

    public List<WeekAanbod> getLijstWeekAanbod(){
        //test
        List<WeekAanbod> aankomendeAanbiedingen = new LinkedList<WeekAanbod>();
        Week huidigeWeek = new Week(YearWeek.now().getYear(),YearWeek.now().getWeek());
        for (WeekAanbod wa : repo.getAlleAanbiedingen()){
            if(wa.getWeek().getIntWeek() == huidigeWeek.getIntWeek() || wa.getWeek().getIntWeek() == huidigeWeek.getIntWeek() + 1){
                aankomendeAanbiedingen.add(wa);
            }
        }
        return aankomendeAanbiedingen;
    }

    public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
        repo.setAlleAanbiedingen(planning);
    }
}
