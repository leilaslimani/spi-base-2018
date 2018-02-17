package fr.univbrest.dosi.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.repositories.FormationRepository;
import fr.univbrest.dosi.util.CatalogueRecherche;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class FormationBusinessJPATest {
	
	FormationBusinessJPA business;
	
	@Test
	public void doitCreerUneFormation() {
		FormationRepository repos = new FormationRepositoryList();
		business = new FormationBusinessJPA(repos);
		Formation formationACreer = new Formation("33", "M2", "N", BigDecimal.valueOf(2), "DOSI");
		
		Formation resultat = business.creerFormation(formationACreer);
		
		assertThat(resultat.getDebutAccreditation()).isCloseTo(new Date(), 500);
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherUneFormationParNom() {
		Formation formation1 = new Formation("44", "L2", "N", BigDecimal.valueOf(2), "ENG");
		Formation formation2 = new Formation("29", "L1", "0", BigDecimal.valueOf(2), "ITIL");
		Formation formation3 = new Formation("33", "M2", "N", BigDecimal.valueOf(2), "DOSI");
		FormationRepository repos = new FormationRepositoryList(formation1, formation2, formation3);
		business = new FormationBusinessJPA(repos);
	
		List<Formation> resultat = business.rechercherFormationParNom("DOSI");
		
		assertThat(repos.count()).isEqualTo(3);
		
		assertThat(resultat.size()).isEqualTo(1);
		assertThat(resultat).hasSize(1);
		
		assertThat(resultat.get(0).getCodeFormation()).isEqualTo("33");
		assertThat(resultat.get(0).getDiplome()).isEqualTo("M2");
		
		assertThat(resultat).containsExactly(formation3);
		
		assertThat(resultat).extracting("codeFormation", "diplome")
							.containsExactly(tuple("33", "M2"));
	}
	
	class FormationRepositoryList implements FormationRepository {
		private List<Formation> data;
		public FormationRepositoryList() {
			data = Lists.newArrayList();
		}
		
		public FormationRepositoryList(Formation... formations) {
			data = Lists.newArrayList(formations);
		}
		
		@Override
		public <S extends Formation> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Formation> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Formation findOne(String id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean exists(String id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Formation> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Formation> findAll(Iterable<String> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			return data.size();
		}

		@Override
		public void delete(String id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Formation entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Iterable<? extends Formation> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Formation> findByNomFormation(String nomFormation) {

			return data.stream()
					.filter(CatalogueRecherche.parNomFormation(nomFormation))
					.collect(Collectors.toList());
		}}

}
