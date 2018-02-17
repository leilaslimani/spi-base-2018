package fr.univbrest.dosi.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.repositories.PromotionRepository;
@Service
public class PromotionBusinessJPA implements PromotionBusiness {
private PromotionRepository repos;
    
    @Autowired
    public PromotionBusinessJPA(PromotionRepository repos)
    {
    	this.repos=repos;
    }
	@Override
	public Promotion creerPromotion(Promotion promotionACreer) {
		return repos.save(promotionACreer);
	}

	@Override
	public void supprimerPromotion(Promotion promotionASupprimer) {
		repos.delete(promotionASupprimer);
		
	}

	@Override
	public Promotion rechercherPromotionParSiglePromotion(String siglePromotion) {
		return repos.findBySiglePromotion(siglePromotion);
	}

	@Override
	public List<Promotion> rechercherPromotionParProcessusStage(String processusStage) {
		return repos.findByProcessusStage(processusStage);
	}

	@Override
	public List<Promotion> recupererToutesLesPromotions() {
		return (List<Promotion>) repos.findAll();
	}

	@Override
	public Promotion rechercherPromotionParId(PromotionPK promotionPk) {
		 return repos.findOne(promotionPk);
	}

}
