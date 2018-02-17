package fr.univbrest.dosi.business;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.repositories.CandidatRepository;
import fr.univbrest.dosi.repositories.EnseignantRepository;
@Service
public class EnseignantBusinessJPA implements EnseignantBusiness{
private EnseignantRepository repos;
    
    @Autowired
    public EnseignantBusinessJPA(EnseignantRepository repos)
    {
    	this.repos=repos;
    }
	@Override
	public Enseignant creerEnseignant(Enseignant enseignantACreer) {
		return repos.save(enseignantACreer);
	}

	@Override
	public void supprimerEnseignant(Enseignant c) {
		repos.delete(c);
		
	}

	@Override
	public List<Enseignant> rechercherEnseignantParNom(String enseignantNom) {
		return repos.findByNom(enseignantNom);
	}

	@Override
	public List<Enseignant> rechercherEnseignantParType(String enseignantType) {
		return repos.findByType(enseignantType);
	}

	@Override
	public List<Enseignant> recupererTousLesEnseignants() {
		return (List<Enseignant>) repos.findAll();
	}

	@Override
	public Enseignant rechercherEnseignantParId(long id) {
		return repos.findBynoEnseignant(id);
	}
	public Enseignant rechercherEnseignantParEmailPerso(String emailPerso) {
		return repos.findByEmailPerso(emailPerso);
	}
	


}
