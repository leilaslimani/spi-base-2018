package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.business.FormationBusinessJPATest.FormationRepositoryList;
import fr.univbrest.dosi.repositories.CandidatRepository;
import fr.univbrest.dosi.repositories.FormationRepository;

public class CandidatBusinessJPATest {
CandidatBusinessJPA business;
	
	@Test
	public void doitCreerUnCandidat() {
		CandidatRepository repos=new CandidatRepositoryList();
		business=new CandidatBusinessJPA(repos);
		Candidat candidatACreer=new Candidat("jk","slimani.ilham@gmail.com","slimani","ilham","ensao");
		Candidat resultat=business.creerCandidat(candidatACreer);
		//assertThat(resultat.getDebutAccreditation()).isCloseTo(new Date(),500);
		assertThat(repos.count()).isEqualTo(1);
	}
	@Test
	public void doitSupprimerCandidat() {
		CandidatRepository repos=new CandidatRepositoryList();
		business=new CandidatBusinessJPA(repos);
		Candidat candidat=new Candidat("jk","slimani.ilham@gmail.com","slimani","ilham","ensao");
		List<Candidat> ListCandidat=Lists.newArrayList();
		ListCandidat.add(candidat);
		CandidatRepository candidatRepository=new CandidatRepositoryList(ListCandidat);
		assertThat(candidatRepository.count()).isEqualTo(1);
		business=new CandidatBusinessJPA(candidatRepository);
		
		 business.supprimerCandidat(candidat);
		 assertThat(candidatRepository.count()).isEqualTo(0);
		
	}
	
	@Test
	public void doitRechercherUnCandidatParNom() {
		Candidat candidat=new Candidat("jk","slimani.ilham@gmail.com","slimani","ilham","ensao");
		List<Candidat> listCandidat=Lists.newArrayList();
		listCandidat.add(candidat);
		CandidatRepository repos=new CandidatRepositoryList(listCandidat);
		business=new CandidatBusinessJPA(repos);
		List<Candidat> resultat=business.rechercherCandidatParNom("slimani");
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getNom()).isEqualTo("slimani");
		assertThat(repos.count()).isEqualTo(1);
	}


	class CandidatRepositoryList implements CandidatRepository{
		
		 private List<Candidat> data;
		 public CandidatRepositoryList(List<Candidat>ListCandidatDonnee) {
				super();
				data=ListCandidatDonnee;
			}
	     public CandidatRepositoryList() {
	       	data=Lists.newArrayList();
	         }
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return data.size();
			}

		@Override
		public void delete(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Candidat entity) {
			data.remove(entity);
			
		}

		@Override
		public void delete(Iterable<? extends Candidat> arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean exists(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Candidat> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Candidat> findAll(Iterable<String> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Candidat findOne(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public <S extends Candidat> S save(S entity) {
			data.add(entity);
			return entity;
		}
	
		@Override
		public <S extends Candidat> Iterable<S> save(Iterable<S> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Candidat> findByNom(String nom) {
			return data.stream()
					.filter(candidat -> candidat.getNom().equalsIgnoreCase(nom))
					.collect(Collectors.toList());
		
		}

		@Override
		public List<Candidat> findByUniversiteOrigine(String universiteOrigine) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
