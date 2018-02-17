package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
public interface PromotionBusiness {
	Promotion creerPromotion(Promotion promotionACreer);
	void supprimerPromotion(Promotion promotionASupprimer);
	Promotion rechercherPromotionParSiglePromotion(String siglePromotion);
	List<Promotion> rechercherPromotionParProcessusStage(String processusStage);
	List<Promotion> recupererToutesLesPromotions();
	Promotion rechercherPromotionParId(PromotionPK promotionPk );	

}
