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
import fr.univbrest.dosi.bean.Enseignant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppTestConfig.class)
public class EnseignantRepositoryTest {
	@Autowired
	EnseignantRepository enseignantRepo;
	
	@Before
	public void setup() {
		Enseignant candidat1=new Enseignant(1,"slimani","leila","INTER","");
		Enseignant candidat2=new Enseignant(2,"boutob","ilham","PHD","");
		enseignantRepo.save(candidat1);
		enseignantRepo.save(candidat2);
	}
@Test
public void doitCompterLesEnseignants() {
	long resultat = enseignantRepo.count();
	assertThat(resultat).isEqualTo(2);
}

@Test
public void doitRechercherParNom() {
	List<Enseignant> resultat=enseignantRepo.findByNom("slimani");
	assertThat(resultat).hasSize(1);
	assertThat(resultat.get(0).getNom()).isEqualTo("slimani");
	//assertThat(resultat.get(0).getCodeFormation()).isEqualTo("33");
}
@Test
public void doitRechercherParType() {
	List<Enseignant> resultat=enseignantRepo.findByType("PHD");
	assertThat(resultat).hasSize(1);
	assertThat(resultat.get(0).getType()).isEqualTo("PHD");
	assertThat(resultat.get(0).getNom()).isEqualTo("boutob");
}

}
