package be.kdg.hiFresh.application;


import be.kdg.foundation.operatie.Operatie;
import be.kdg.foundation.operatie.Order;
import be.kdg.foundation.operatie.Sort;
import be.kdg.hiFresh.domain.leverancier.Contract;
import be.kdg.hiFresh.domain.leverancier.ContractPeriode;
import be.kdg.hiFresh.domain.managers.ContractManager;
import be.kdg.hiFresh.domain.managers.WeekAanbodManager;
import be.kdg.hiFresh.domain.recept.*;
import org.threeten.extra.YearWeek;

import java.util.*;
import java.util.stream.Collectors;

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
		contractManager = new ContractManager();
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
		//variabele om de gemiddelde aankoopprijs van het HUIDIGE ingredient bij te houden
		double gemiddeldeAankoopPrijsIngredient = 0;
		//de totaal prijs van een recept is de som van de gemiddelde prijzen van alle ingredienten die het recept bevat.
		double totaalPrijsRecept = 0;
		//Ons resultaat dat we teruggeven, een map met als sleutel het recept en de bijbehorende prijs)
        Map<Recept, Double> mapPrijsPerRecept = new HashMap<Recept, Double>();
        //Weekaanbiedingen ophalen waarvan we de prijs willen bereken
        List<WeekAanbod> weekAanbods = weekAanbodManager.getLijstWeekAanbod(new Week(jaar, week),1);
        //voor elk weekaanbod moeten we voor elk recept de prijs berekenen

		//Extra List van Recepten met filter
		List<Recept> lijstMetFilter = new ArrayList<>();
        for (WeekAanbod w : weekAanbods){
			Map<Integer, Recept> recepten = w.getRecepten();
			//We overlopen elk recept
			for (int key : recepten.keySet()){
				boolean check = false;
				//We overlopen alle ingerediënten per recept
				for (Ingredient ingredient : recepten.get(key).getIngredienten()){
					//We gebruiken de filter om te kijken of het ingrediënt erin zit
					for (Operatie o : filter){
						//TODO
						//Dit zou nog aangepast moeten worden, maar werkt voor de test
						//Zie de commentaar van Operatie voor uitleg
						if (ingredient.getProduct().getNaam().contains(o.getValue())){
							check = true;
						}
					}
				}
				if (check){
					lijstMetFilter.add(recepten.get(key));
				}
			}

            for (Recept recept : lijstMetFilter){
            	//we willen niet met gegevens van vorige recepten/ingredienten werken dus resetten we onze variabelen
				totaalPrijsRecept = 0;
				//voor elk ingredient in ons recept bereken we de gemiddelde aankoopprijs
            	for(Ingredient i : recept.getIngredienten()){
					gemiddeldeAankoopPrijsIngredient = 0;
            		//berekening gemiddelde aankoopprijs
            		gemiddeldeAankoopPrijsIngredient = contractManager.berekenGemiddelePrijsProduct(i.getProduct(),week,jaar);
            		//alle prijzen optellen
            		totaalPrijsRecept =+ gemiddeldeAankoopPrijsIngredient;
				}
				//elk recept en linken met de berekende prijs
				mapPrijsPerRecept.put(recept,totaalPrijsRecept);
            }

        }
		return mapPrijsPerRecept;
	}

	public void VoegTestWeekAanbiedingenToe(List<WeekAanbod> planning){
		weekAanbodManager.VoegTestWeekAanbiedingenToe(planning);
	}

	public void VoegTestContractsToe(List<Contract> contracten){
	    contractManager.fillRepo(contracten);
    }

}
