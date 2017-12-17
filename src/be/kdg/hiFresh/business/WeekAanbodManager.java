package be.kdg.hiFresh.business;

import be.kdg.hiFresh.domain.Week;
import be.kdg.hiFresh.domain.recept.Recept;
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
            for(int i = 0; i < size; i++){
                if(wa.getWeek().getIntWeek() == week.getIntWeek() + i){
                    aankomendeAanbiedingen.add(wa);
                }
            }
        }
        return aankomendeAanbiedingen;
    }

    public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
        repo.setAlleAanbiedingen(planning);
    }

    public WeekAanbod voegReceptToe(WeekAanbod weekAanbod, Recept recept, int volgnummer){
        WeekAanbod wa;
        for (WeekAanbod w : repo.getAlleAanbiedingen()){
            if(w == weekAanbod){
                wa = w;
                wa.voegToe(recept,volgnummer);
                return wa;
            }
        }
        return new WeekAanbod(YearWeek.now(),1);
    }
}
