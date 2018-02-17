package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.business.EnseignantBusiness;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {
	
	private EnseignantBusiness business;

	@Autowired
	public EnseignantController(EnseignantBusiness business) {
		this.business = business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Enseignant creerEnseignant(@RequestBody Enseignant enseignantACreer) {
		return business.creerEnseignant(enseignantACreer);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/del/{id}")
	public String supprimerEnseignant( @PathVariable long id) {
		Enseignant c=  recupererEnseignantAvecLId(id);
		business.supprimerEnseignant(c);
		return "Enseignant supprimé";
	}
	//rechercher tous les enseignants
	@RequestMapping(method = RequestMethod.GET)
	public List<Enseignant> recupererTousLesEnseignants() {
		return business.recupererTousLesEnseignants();
	}
	//recherche par nom qui renvoit une liste
	@RequestMapping(method = RequestMethod.GET, value="/nom/{nom}")
	public List<Enseignant> recupererEnseignantAvecLeNom(@PathVariable String nom) {
		return business.rechercherEnseignantParNom(nom.toUpperCase());
	}
	//recherche par Id qui renvoit un objet Enseignant
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Enseignant recupererEnseignantAvecLId(@PathVariable long id) {
		return business.rechercherEnseignantParId(id);
	}
	
	//recherche par Email Personnelle qui renvoit un objet Enseignant
		@RequestMapping(method = RequestMethod.GET, value="/email/{email:.+}")
		public Enseignant recupererEnseignantAvecEmailPerso(@PathVariable String email) {
			return business.rechercherEnseignantParEmailPerso(email);
		}
	
	//recherche par Type qui renvoit une liste
	@RequestMapping(method = RequestMethod.GET, value="/type/{type}")
	public List<Enseignant> recupererEnseignantAvecType(@PathVariable String type) {
		return business.rechercherEnseignantParType(type.toUpperCase());
	}

}
