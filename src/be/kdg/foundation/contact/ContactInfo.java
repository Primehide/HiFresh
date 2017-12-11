package be.kdg.foundation.contact;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Jan de Rijke.
 */
public class ContactInfo {
	private String email;
	private String[] telefoon;
	private Adres adres;




	public ContactInfo(Adres adres, String email, String... tel) {
		this.email = email;
		this.telefoon = tel;
		this.adres = adres;
	}
}
