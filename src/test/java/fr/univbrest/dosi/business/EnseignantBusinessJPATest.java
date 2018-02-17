package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.business.FormationBusinessJPATest.FormationRepositoryList;
import fr.univbrest.dosi.repositories.CandidatRepository;
import fr.univbrest.dosi.repositories.EnseignantRepository;
import fr.univbrest.dosi.repositories.FormationRepository;

public class EnseignantBusinessJPATest {
	EnseignantBusinessJPA business;
	
	@Test
	public void doitCreerUnEnseignant() {
		EnseignantRepository repos=new EnseignantRepositoryList();
		business=new EnseignantBusinessJPA(repos);
		Enseignant enseignantACreer=new Enseignant(1,"slimani","leila","INTER","");
		Enseignant resultat=business.creerEnseignant(enseignantACreer);
		assertThat(repos.count()).isEqualTo(1);
	}
	@Test
	public void doitSupprimerEnseignant() {
		EnseignantRepository repos=new EnseignantRepositoryList();
		business=new EnseignantBusinessJPA(repos);
		Enseignant enseignant=new Enseignant(1,"slimani","leila","INTER","");
		List<Enseignant> ListEnseignant=Lists.newArrayList();
		ListEnseignant.add(enseignant);
		EnseignantRepository enseignantRepository=new EnseignantRepositoryList(ListEnseignant);
		assertThat(enseignantRepository.count()).isEqualTo(1);
		business=new EnseignantBusinessJPA(enseignantRepository);
		
		 business.supprimerEnseignant(enseignant);
		 assertThat(enseignantRepository.count()).isEqualTo(0);
		
	}
	
	@Test
	public void doitRechercherUnEnseignantParNom() {
		Enseignant enseignant=new Enseignant(1,"slimani","ilham","INTER","");
		List<Enseignant> listEnseignant=Lists.newArrayList();
		listEnseignant.add(enseignant);
		EnseignantRepository repos=new EnseignantRepositoryList(listEnseignant);
		business=new EnseignantBusinessJPA(repos);
		List<Enseignant> resultat=business.rechercherEnseignantParNom("slimani");
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getNom()).isEqualTo("slimani");
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherTousLesEnseignants() {
		Enseignant enseignant1=new Enseignant(1,"slimani","ilham","INTER","");
		Enseignant enseignant2=new Enseignant(2,"lele","ilham","INTER","");
		List<Enseignant> listEnseignant=Lists.newArrayList();
		listEnseignant.add(enseignant1);
		listEnseignant.add(enseignant2);
		EnseignantRepository repos=new EnseignantRepositoryList(listEnseignant);
		business=new EnseignantBusinessJPA(repos);
		List<Enseignant> resultat=business.recupererTousLesEnseignants();
		assertThat(resultat).hasSize(2);
		assertThat(resultat.get(0).getNom()).isEqualTo("slimani");
		assertThat(resultat.get(1).getNom()).isEqualTo("lele");
		//assertThat(repos.count()).isEqualTo(2);
	}
	@Test
	public void doitRechercherUnEnseignantParID() {
		Enseignant enseignant=new Enseignant(1,"slimani","leila","INTER","");
		List<Enseignant> listEnseignant=Lists.newArrayList();
		listEnseignant.add(enseignant);
		EnseignantRepository repos=new EnseignantRepositoryList(listEnseignant);
		business=new EnseignantBusinessJPA(repos);
		Enseignant resultat=business.rechercherEnseignantParId(1);
		assertThat(resultat.getNom().equals("slimani"));
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherUnEnseignantParEmailPerso() {
		Enseignant enseignant=new Enseignant(1,"slimani","leila","INTER","lele@gmail.com");
		List<Enseignant> listEnseignant=Lists.newArrayList();
		listEnseignant.add(enseignant);
		EnseignantRepository repos=new EnseignantRepositoryList(listEnseignant);
		business=new EnseignantBusinessJPA(repos);
		Enseignant resultat=business.rechercherEnseignantParEmailPerso("lele@gmail.com");
		assertThat(resultat.getEmailPerso().equals("lele@gmail.com"));
		assertThat(repos.count()).isEqualTo(1);
	}
	class EnseignantRepositoryList implements EnseignantRepository{
		
		 private List<Enseignant> data;
		 public EnseignantRepositoryList(List<Enseignant>ListEnseignants) {
				super();
				data=ListEnseignants;
			}
	     public EnseignantRepositoryList() {
	       	data=Lists.newArrayList();
	         }
		@Override
		public <S extends Enseignant> S save(S entity) {
			data.add(entity);
			return entity;
		}
		@Override
		public <S extends Enseignant> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		@Override
		public long count() {
			return data.size();
		}
		
		
		@Override
		public void delete(Enseignant entity) {
			data.remove(entity);
			
		}
		@Override
		public void delete(Iterable<? extends Enseignant> entities) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public List<Enseignant> findByNom(String nom) {
			return data.stream()
					.filter(enseignant -> enseignant.getNom().equalsIgnoreCase(nom))
					.collect(Collectors.toList());
		}
		@Override
		public List<Enseignant> findByType(String type) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Enseignant findOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean exists(Long id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void delete(Long id) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Iterable<Enseignant> findAll() {
			return data;
		}
		@Override
		public Iterable<Enseignant> findAll(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Enseignant findBynoEnseignant(long id) {
			Enseignant c = new Enseignant();
			for( Enseignant e : data) {
				if (e.getNoEnseignant()==id) {
					c=e;
				}
			}
			return c;
		}
		@Override
		public Enseignant findByEmailPerso(String emailPerso) {
			Enseignant c = new Enseignant();
			for( Enseignant e : data) {
				if (e.getEmailPerso()==emailPerso) {
					c=e;
				}
			}
			return c;
		}
		
		
	}
}