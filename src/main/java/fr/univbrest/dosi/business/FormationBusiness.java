package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Formation;

public interface FormationBusiness {
	
	Formation creerFormation(Formation formationACreer);

	List<Formation> rechercherFormationParNom(String nom);

	List<Formation> recupererToutesLesFormations();

	Formation rechercherFormationParId(String id);

}
