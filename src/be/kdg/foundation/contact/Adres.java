package be.kdg.foundation.contact;

import java.util.logging.Logger;

/**
 * @author Jan de Rijke.
 */
public class Adres {
	private static Logger logger = Logger.getLogger("be.kdg.foundation.contact.Adres");
	private final Gemeente gemeente;
	private final String nummer;
	private final String straat;

	public Adres(String straat, String nr, Gemeente gemeente) {
		this.straat = straat;
		this.nummer = nr;
		this.gemeente = gemeente;
	}
}
