package be.kdg.hiFresh.domain.recept;


import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;
/**
 * @author Jan de Rijke.
 */
public class Recept {
	// TODO: implementeer klasse
	private String naam;
	private Duration bereidingsTijd;
	private int moeilijkheidsgraad;
	private List<String> gerechtInstructies;
	private List<Ingredient> ingredienten;
	private List<Label> labels;



	public Recept(
		String naam,
		Duration bereidingsTijd,
		int moeilijkheid,
		List<String> instructies,
		List<Ingredient> ingredienten
	) {
		this.naam = naam;
		this.bereidingsTijd = bereidingsTijd;
		this.moeilijkheidsgraad = moeilijkheid;
		this.gerechtInstructies = new LinkedList<String>(instructies);
		this.ingredienten = new LinkedList<Ingredient>(ingredienten);
		this.labels = new LinkedList<Label>();
	}

	public void addLabel(Label label) {
		labels.add(label);
	}


	public String getNaam() {
		return naam;
	}
}
