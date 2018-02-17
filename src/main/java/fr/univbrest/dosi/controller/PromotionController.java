package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.business.PromotionBusiness;

@RestController
@RequestMapping("/promotions")
public class PromotionController {
	private PromotionBusiness business;

	@Autowired
	public PromotionController(PromotionBusiness business) {
		this.business = business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Promotion creerpromotionPk(@RequestBody Promotion promotionACreer) {
		return business.creerPromotion(promotionACreer);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/del/{codeFormation}/{anneeUniversitaire}")
	public String supprimerPromotion( @PathVariable String codeFormation,@PathVariable String  anneeUniversitaire ) {
		Promotion c=  recupererPromotionAvecLId(codeFormation, anneeUniversitaire);
		business.supprimerPromotion(c);
		return "Promotion supprimé";
	}
	//rechercher tous les Promotions
	@RequestMapping(method = RequestMethod.GET)
	public List<Promotion> recupererToutesLesPromotions() {
		return business.recupererToutesLesPromotions();
	}
	//rechercher par Processus Stage qui renvoit une liste
	@RequestMapping(method = RequestMethod.GET, value="/ProcessusStage/{PS}")
	public List<Promotion> recupererPromotionAvecProcessusStage(@PathVariable String PS) {
		return business.rechercherPromotionParProcessusStage(PS.toUpperCase());
	}
	//recherche par Id qui renvoit un objet Promotion
	@RequestMapping(method = RequestMethod.GET, value="/{anneeUniversitaire}/{codeFormation}")
	public Promotion recupererPromotionAvecLId(@PathVariable String codeFormation,@PathVariable String  anneeUniversitaire ) {
		PromotionPK promotionPk=new PromotionPK(anneeUniversitaire,codeFormation);
		return business.rechercherPromotionParId(promotionPk);
	}
	
	//recherche par Sigle Promotion qui renvoit un objet Promotion
		@RequestMapping(method = RequestMethod.GET, value="/siglePromotion/{sigle}")
		public Promotion recupererEnseignantAvecSiglePromotion(@PathVariable String sigle) {
			return business.rechercherPromotionParSiglePromotion(sigle);
		}
	
	
}
