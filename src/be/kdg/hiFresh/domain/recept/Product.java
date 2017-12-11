package be.kdg.hiFresh.domain.recept;


import be.kdg.foundation.qualified.Hoeveelheid;

import java.time.MonthDay;
import java.util.logging.Logger;
/**
 * @author Jan de Rijke.
 */
public class Product {

	// TODO: implementeer klasse
	private String naam;
	private MonthDay beginHoogSeizoen;
	private MonthDay eindHoogSeizoen;
	private Hoeveelheid eenheid;


	public Product(String naam,MonthDay startHoogseizoen,MonthDay eindeHoogseizoen, Hoeveelheid
		stdHoeveelheid) {
		this.naam = naam;
		this.beginHoogSeizoen = startHoogseizoen;
		this.eindHoogSeizoen = eindeHoogseizoen;
		this.eenheid = stdHoeveelheid;

	}


	public Product(String naam, Hoeveelheid stdHoeveelheid) {
		this.naam = naam;
		this.eenheid = stdHoeveelheid;
	}



	public Hoeveelheid getStandaardHoeveelheid (){
		return eenheid;
	}


}
