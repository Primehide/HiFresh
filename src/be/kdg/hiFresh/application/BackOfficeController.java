package be.kdg.hiFresh.application;


import be.kdg.foundation.operatie.Operatie;
import be.kdg.foundation.operatie.Sort;
import be.kdg.hiFresh.domain.leverancier.Contract;
import be.kdg.hiFresh.domain.leverancier.ContractPeriode;
import be.kdg.hiFresh.domain.managers.ContractManager;
import be.kdg.hiFresh.domain.managers.WeekAanbodManager;
import be.kdg.hiFresh.domain.recept.*;
import org.threeten.extra.YearWeek;

import java.util.*;

/**
 * @author Jan de Rijke.
 */
// Dit is de controllerklasse van het BackOffice subsysteem
public class BackOfficeController {

	private static int WEEK_PAGE_SIZE = 2; 	// Constante die vastlegt hoeveel weken weekaanbod wordt teruggegeven.
	private WeekAanbodManager weekAanbodManager;
	private ContractManager contractManager;


	public BackOfficeController() {
		weekAanbodManager = new WeekAanbodManager();
	} //TODO indien nodig


	public List<WeekAanbod> getLijstWeekaanbod() {
		return weekAanbodManager.getLijstWeekAanbod(new Week(YearWeek.now().getYear(),YearWeek.now().getWeek()),WEEK_PAGE_SIZE);
	}

	public Map<Recept, Double> zoekRecepten(
		int jaar, int week, List<Operatie>
		filter,
		List<Sort> sorter
	) {
		// TODO
        Map<Recept, Double> res = new HashMap<Recept, Double>();
        List<WeekAanbod> weekAanbods = weekAanbodManager.getLijstWeekAanbod(new Week(jaar, week),WEEK_PAGE_SIZE);
        for (WeekAanbod w : weekAanbods){
            Map<Integer, Recept> recepten = w.getRecepten();
            for (int i : recepten.keySet()){
                Recept r = recepten.get(i);
                r.berekenGemPrijs(new Week(jaar, week));
            }
        }
		return res; //placeholder om compileerbaar te maken
	}

	public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
		weekAanbodManager.VoegTestWeekAanbiedingenToe(planning);
	}

	public void VoegTestContractsToe(List<Contract> planning){
	    contractManager.fillRepo(planning);
    }

}
