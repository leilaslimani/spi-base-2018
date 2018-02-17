package fr.univbrest.dosi.util;

import java.util.function.Predicate;

import fr.univbrest.dosi.bean.Formation;

public class CatalogueRecherche {
	
	public static Predicate<Formation> parNomFormation(String nom) {
		return formation -> formation.getNomFormation().equalsIgnoreCase(nom);
	}
	

}
