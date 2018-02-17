package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Enseignant;

public interface EnseignantBusiness {
	Enseignant creerEnseignant(Enseignant enseignantACreer);
	void supprimerEnseignant(Enseignant c);
	List<Enseignant> rechercherEnseignantParNom(String enseignantNom);
	List<Enseignant> rechercherEnseignantParType(String enseignantUniv);
	List<Enseignant> recupererTousLesEnseignants();
	Enseignant rechercherEnseignantParId(long id );	
	Enseignant rechercherEnseignantParEmailPerso(String emailPerso );	
}
