package be.kdg.hiFresh.application;

import be.kdg.foundation.operatie.*;
import be.kdg.hiFresh.domain.recept.Recept;
import be.kdg.hiFresh.domain.recept.WeekAanbod;
import be.kdg.hiFresh.persistentie.WeekAanbodMemoryRepo;
import be.kdg.infra.MemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.threeten.extra.YearWeek;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * In de test methoden van deze klasse mag je niks veranderen
 *
 * @author Jan de Rijke.
 */
public class BackOfficeControllerTest {
	private BackOfficeController ctrl;


	public BackOfficeControllerTest(){
		ctrl = new BackOfficeController();
	}



	@Before
	public void setUp() throws Exception {
		// TODO initialiseer het systeem
		TestData data = new TestData();
		// TODO Voeg de testdata toe aan het systeem.
		// De TestData zijn beschikbaar in collections via de getter methoden van TestData
		// Na de initialisatie mag de TestData klasse niet meer gebruikt worden.
		// Tijdens de test en in de project code moet al deze data opgezocht worden via het systeem
		// 1. Contracten worden opgesteld.
		// 2. Producten worden opgesteld en aan contractperiodes gekoppeld.
		// 3. Met behulp van de aangemaakte producten worden recepten opgesteld.
		// 4. Weekaanboden worden aangemaakt	}
		ctrl.VoegTestWeekAanbiedingenToe(data.getPlanning()); //voegt testdata toe aan de WeekAanbod Repository

	}

	@Test
	public void getLijstWeekaanbod() throws Exception {
		List<WeekAanbod> aanbodLijst = ctrl.getLijstWeekaanbod();
		assertEquals("Default wordt het aanbod van 2 weken teruggegeven", 2, aanbodLijst.size());
		assertEquals(WeekAanbod.SIZE, aanbodLijst.get(0).getRecepten().size());
		// a test should not print, but this is just for fun
		//System.out.println(aanbodLijst.get(0));
	}

	@Test
	public void zoekRecepten() throws Exception {
		final LocalDate now = LocalDate.now();
		// TODO implementeer de controller methode
		// Het is voldoende als de gegeven filter en sorter geïmplementeerd zijn
		// Code gebruikt hier YearWeek uit bibliotheek  threeten-extra-1.2.jar (www.threeten.org/threeten-extra)
		// De library zit mee in dit project. Je mag ook iets anders gebruiken om met weken te werken
		Map<Recept, Double> receptenMetAankoopprijs = ctrl.zoekRecepten(
                now.getYear(),
                YearWeek.from(now).getWeek(),
                List.of(new Operatie("naam", Operator.CONTAINS, "curry")),
                List.of(new Sort("naam", Order.DESCENDING)));
		assertEquals(2, receptenMetAankoopprijs.size());
		Iterator<Recept> recepten = receptenMetAankoopprijs.keySet().iterator();
		Recept eerste = recepten.next();
		Recept tweede = recepten.next();
		assertTrue(
			"Eerste receptnaam moet na tweede naam komen (groter zijn dan, descending)",
			eerste.getNaam().compareTo(tweede.getNaam()) > 0);
		// Manuele berekening van de aankoopprijs van zoete curry van garnalen en kokos
		// op basis van de richtprijs van de producten (bijvoorbeeld 7 euro voor 200 gram garnalen
		// en hoeveelheid van de ingredienten in het recept (bijvoorbeeld 100 gram garnalen)
		double richtprijs = 100*7.0/200.0+4*5/20+0.4*0.5+ 0.25/24*1+1*1.0/40+10*2.0/150+1*3.0/60+100*2.0/1000;
		// Vergelijk de manueel berekende richtprijs met de door het systeem berekende aankoopprijs
		// De prijs kan per producent tot 10% verschillen van de richtprijs
		assertEquals("Prijs valt niet binnen 10% variatie",richtprijs,
			receptenMetAankoopprijs.get(eerste),richtprijs*0.1);
		//System.out.println(receptenMetAankoopprijs);
	}

	@Test
	public void zetReceptInWeekAanbod() throws Exception {
		// Deze methode moet NIET geÏmplementeerd worden...
	}

}