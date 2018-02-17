package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.repositories.PromotionRepository;

public class PromotionBusinessJPATest {
	PromotionBusinessJPA business;
	
	@Test
	public void doitCreerUnePromotion() {
		PromotionRepository repos=new PromotionRepositoryList();
		business=new PromotionBusinessJPA(repos);
		Promotion promotionACreer=new Promotion(new PromotionPK("2017-2018","M2DOSI"),"EC","DOSI8");
		Promotion resultat=business.creerPromotion(promotionACreer);
		assertThat(repos.count()).isEqualTo(1);
	}
	@Test
	public void doitSupprimerPromotion() {
		PromotionRepository repos=new PromotionRepositoryList();
		business=new PromotionBusinessJPA(repos);
		Promotion promotion=new Promotion(new PromotionPK("2017-2018","M2DOSI"),"EC","DOSI8");
		List<Promotion> ListPromotions=Lists.newArrayList();
		ListPromotions.add(promotion);
		PromotionRepository promotionRepository=new PromotionRepositoryList(ListPromotions);
		assertThat(promotionRepository.count()).isEqualTo(1);
		business=new PromotionBusinessJPA(promotionRepository);
		
		 business.supprimerPromotion(promotion);
		 assertThat(promotionRepository.count()).isEqualTo(0);
		
	}
	
	@Test
	public void doitRechercherUnePromotionParProcessusStage() {
		Promotion promotion=new Promotion(new PromotionPK("2017-2018","M2DOSI"),"EC","DOSI8");
		List<Promotion> listPromotions=Lists.newArrayList();
		listPromotions.add(promotion);
		PromotionRepository repos=new PromotionRepositoryList(listPromotions);
		business=new PromotionBusinessJPA(repos);
		List<Promotion> resultat=business.rechercherPromotionParProcessusStage("EC");
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getProcessusStage()).isEqualTo("EC");
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherToutesLesPromotions() {
		Promotion promotion1=new Promotion(new PromotionPK("2017-2018","M2DOSI"),"EC","DOSI8");
		Promotion promotion2=new Promotion(new PromotionPK("2018-2019","M2DOSI"),"RECH","DOSI9");
		List<Promotion> listPromotions=Lists.newArrayList();
		listPromotions.add(promotion1);
		listPromotions.add(promotion2);
		PromotionRepository repos=new PromotionRepositoryList(listPromotions);
		business=new PromotionBusinessJPA(repos);
		List<Promotion> resultat=business.recupererToutesLesPromotions();
		assertThat(resultat).hasSize(2);
		assertThat(resultat.get(0).getProcessusStage()).isEqualTo("EC");
		assertThat(resultat.get(1).getProcessusStage()).isEqualTo("RECH");
		//assertThat(repos.count()).isEqualTo(2);
	}
	@Test
	public void doitRechercherUnePromotionParID() {
		PromotionPK p=new PromotionPK("2018-2019","M2DOSI");
		Promotion promotion=new Promotion(p,"RECH","DOSI9");
		List<Promotion> listPromotions=Lists.newArrayList();
		listPromotions.add(promotion);
		PromotionRepository repos=new PromotionRepositoryList(listPromotions);
		business=new PromotionBusinessJPA(repos);
        Promotion resultat=business.rechercherPromotionParId(p);
		//assertThat(resultat.getProcessusStage().equals("RECH"));
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherUnePromotionParSiglePromotion() {
		PromotionPK p=new PromotionPK("2018-2019","M2DOSI");
		Promotion promotion=new Promotion(p,"RECH","DOSI9");
		List<Enseignant> listEnseignant=Lists.newArrayList();
		List<Promotion> listPromotions=Lists.newArrayList();
		listPromotions.add(promotion);
		PromotionRepository repos=new PromotionRepositoryList(listPromotions);
		business=new PromotionBusinessJPA(repos);
		Promotion resultat=business.rechercherPromotionParSiglePromotion("DOSI9");
		assertThat(resultat.getSiglePromotion().equals("DOSI9"));
		assertThat(repos.count()).isEqualTo(1);
	}
	class PromotionRepositoryList implements PromotionRepository{

		 private List<Promotion> data;
		 public PromotionRepositoryList(List<Promotion>ListPromotions) {
				super();
				data=ListPromotions;
			}
	     public PromotionRepositoryList() {
	       	data=Lists.newArrayList();
	         }
		@Override
		public <S extends Promotion> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Promotion> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Promotion findOne(PromotionPK id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean exists(PromotionPK id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Promotion> findAll() {
			return data;
		}

		@Override
		public Iterable<Promotion> findAll(Iterable<PromotionPK> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			return data.size();
		}

		@Override
		public void delete(PromotionPK id) {
			
			
		}

		@Override
		public void delete(Promotion entity) {
			data.remove(entity);
			
		}

		@Override
		public void delete(Iterable<? extends Promotion> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Promotion> findByProcessusStage(String processusStage) {
			return data.stream()
					.filter(promotion -> promotion.getProcessusStage().equalsIgnoreCase(processusStage))
					.collect(Collectors.toList());
		}

		@Override
		public Promotion findBySiglePromotion(String siglePromotion) {
			Promotion c = new Promotion();
			for( Promotion e : data) {
				if (e.getSiglePromotion()==siglePromotion) {
					c=e;
				}
			}
			return c;
		}
		@Override
		public Promotion findById(PromotionPK promotionPk) {
			Promotion c = new Promotion();
			for( Promotion e : data) {
				if (e.getId().equals(promotionPk)) {
					c=e;
				}
			}
			return c;
		}
}
}