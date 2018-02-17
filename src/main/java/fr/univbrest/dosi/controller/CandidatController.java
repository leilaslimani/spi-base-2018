package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.business.CandidatBusiness;

@RestController
@RequestMapping("/candidats")
public class CandidatController {
	
	private CandidatBusiness business;

	@Autowired
	public CandidatController(CandidatBusiness business) {
		this.business = business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Candidat creerCandidat(@RequestBody Candidat candidatACreer) {
		return business.creerCandidat(candidatACreer);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/del/{id}")
	public String supprimerCandidat( @PathVariable String id) {
		Candidat c=  recupererLeCandidatAvecLId(id);
		business.supprimerCandidat(c);
		return "candidat supprimé";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Candidat> recupererTousLesCandidats() {
		return business.recupererTousLesCandidats();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/nom/{nom}")
	public List<Candidat> recupererLeCandidatAvecLeNom(@PathVariable String nom) {
		return business.rechercherCandidatParNom(nom.toUpperCase());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Candidat recupererLeCandidatAvecLId(@PathVariable String id) {
		return business.rechercherCandidatParId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/nomUniv/{nom}")
	public List<Candidat> recupererLeCandidatAvecLUniv(@PathVariable String nom) {
		return business.rechercherCandidatParUniv(nom.toUpperCase());
	}

}
