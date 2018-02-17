package fr.univbrest.dosi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import fr.univbrest.dosi.AppTestConfig;
import fr.univbrest.dosi.bean.Candidat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppTestConfig.class)
public class CandidatRepositoryTest {
	@Autowired
	CandidatRepository candidatRepo;
	
	@Before
	public void setup() {
		Candidat candidat1=new Candidat("jh","slimanii.leila@gmail.com","slimani","leila","Université Med 1er");
		Candidat candidat2=new Candidat("jk","slimani.ilham@gmail.com","boutob","ilham","ensao");
		candidatRepo.save(candidat1);
		candidatRepo.save(candidat2);
	}
@Test
public void doitCompterLesCandidats() {
	long resultat = candidatRepo.count();
	assertThat(resultat).isEqualTo(2);
}

@Test
public void doitRechercherParNom() {
	List<Candidat> resultat=candidatRepo.findByNom("slimani");
	assertThat(resultat).hasSize(1);
	assertThat(resultat.get(0).getNom()).isEqualTo("slimani");
	//assertThat(resultat.get(0).getCodeFormation()).isEqualTo("33");
}
@Test
public void doitRechercherParUniversiteOrigine() {
	List<Candidat> resultat=candidatRepo.findByUniversiteOrigine("ensao");
	assertThat(resultat).hasSize(1);
	assertThat(resultat.get(0).getUniversiteOrigine()).isEqualTo("ensao");
	assertThat(resultat.get(0).getNom()).isEqualTo("boutob");
}

}
