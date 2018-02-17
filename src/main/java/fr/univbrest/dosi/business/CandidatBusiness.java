package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Formation;

public interface CandidatBusiness {
	Candidat creerCandidat(Candidat candidatACreer);
	void supprimerCandidat(Candidat c);
	List<Candidat> rechercherCandidatParNom(String candidatNom);
	List<Candidat> rechercherCandidatParUniv(String candidatUniv);
	List<Candidat> recupererTousLesCandidats();
	Candidat rechercherCandidatParId(String id );	
}
