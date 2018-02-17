package fr.univbrest.dosi.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.repositories.FormationRepository;

@Service
public class FormationBusinessJPA implements FormationBusiness {

	private FormationRepository repos;
	
	@Autowired
	public FormationBusinessJPA(FormationRepository repos) {
		this.repos = repos;
	}
		
	@Override
	public Formation creerFormation(Formation formationACreer) {
		
		if(formationACreer.getDebutAccreditation() == null) {
			formationACreer.setDebutAccreditation(new Date());
		}
				
		return repos.save(formationACreer);
	}
	
	@Override
	public List<Formation> rechercherFormationParNom(String nom) {
		return repos.findByNomFormation(nom);
	}

	@Override
	public List<Formation> recupererToutesLesFormations() {
		return (List<Formation>) repos.findAll();
	}

	@Override
	public Formation rechercherFormationParId(String id) {
		return repos.findOne(id);
	}

}
