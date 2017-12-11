package be.kdg.hiFresh.persistentie;

import be.kdg.hiFresh.domain.recept.WeekAanbod;

import java.util.LinkedList;
import java.util.List;

public class WeekAanbodMemoryRepo {
    private List<WeekAanbod> alleAanbiedingen;

    public void setAlleAanbiedingen(List<WeekAanbod> aanbiedingen){
        alleAanbiedingen = new LinkedList<WeekAanbod>(aanbiedingen);
    }

    public List<WeekAanbod> getAlleAanbiedingen(){
        return alleAanbiedingen;
    }
}
