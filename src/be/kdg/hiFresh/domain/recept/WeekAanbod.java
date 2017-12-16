package be.kdg.hiFresh.domain.recept;

import org.threeten.extra.YearWeek;

import java.util.*;

/**
 * @author Jan de Rijke.
 * Weekaanbod met geordende recepten
 * Het eerste recept staat op plaats 1
 */


public class WeekAanbod {
	// TODO: implementeer klasse
	private	Map<Integer,Recept> mapRecepten;
	private Week week;
	private double verkoopPrijsPerPersoon;

	public static final int SIZE =10;

  public WeekAanbod(YearWeek yearWeek, double prijs) {
	  // TODO
	  verkoopPrijsPerPersoon = prijs;
	  week = new Week(yearWeek.getYear(),yearWeek.getWeek());
	  mapRecepten = new HashMap<Integer, Recept>();
  }

  public Week getWeek(){
  	return  week;
  }



	/**
	 *
	 * @param recept recept dat toegevoegd moet worden
	 * @param plaats plaats in de lijst. Eventueel bestaande recepten worden naar onder geshift
	 * @return Indien een recept uit de lijst valt omdat het voorbij de maximum size geshift wordt,
	 * wordt dit gereturned, anders returns null
	 */
	public Recept voegToe(Recept recept,int plaats){
		//TODO moet nog rekening houden met het verplaatsen van recepten.
		// Max is 10 per aanbod dus als er 1 bijkomt moet alles verplaatsen.Indien er dan meer dan 10 zijn geeft je het recept terug dat afvalt.
		mapRecepten.putIfAbsent(plaats,recept);
		return recept;
	}


	public  Map<Integer,Recept>  getRecepten() {
		return mapRecepten;
	}

	public void setVerkoopPrijsPerPersoon(double verkoopPrijsPerPersoon) {
		this.verkoopPrijsPerPersoon = verkoopPrijsPerPersoon;
	}
}
