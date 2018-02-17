package fr.univbrest.dosi.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.repositories.CandidatRepository;
@Service
public class CandidatBusinessJPA implements CandidatBusiness {
private CandidatRepository repos;
    
    @Autowired
    public CandidatBusinessJPA(CandidatRepository repos)
    {
    	this.repos=repos;
    }
	@Override
	public Candidat creerCandidat(Candidat candidatACreer) {
		return repos.save(candidatACreer);
	}

	@Override
	public void supprimerCandidat(Candidat noCandidat) {
		repos.delete(noCandidat);		
	}

	@Override
	public List<Candidat> rechercherCandidatParNom(String candidatNom) {
		return repos.findByNom(candidatNom);
	}

	@Override
	public List<Candidat> rechercherCandidatParUniv(String candidatUniv) {
		return repos.findByUniversiteOrigine(candidatUniv);
	}
	@Override
	public List<Candidat> recupererTousLesCandidats() {
		return (List<Candidat>) repos.findAll();
	}
	@Override
	public Candidat rechercherCandidatParId(String id) {
		return repos.findOne(id);
	}

}
