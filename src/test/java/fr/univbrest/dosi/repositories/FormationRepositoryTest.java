package fr.univbrest.dosi.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univbrest.dosi.AppTestConfig;
import fr.univbrest.dosi.bean.Formation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTestConfig.class)
public class FormationRepositoryTest {
	
	@Autowired
	FormationRepository formationRepo;
	
	@Before
	public void setup() {
		Formation formation1 = new Formation("33", "M2", "O", BigDecimal.valueOf(2.0), "DOSI");
		Formation formation2 = new Formation("29", "L1", "O", BigDecimal.valueOf(2.0), "ITIL");
	
		formationRepo.save(formation1);
		formationRepo.save(formation2);
	}
	
	@Test
	public void doitCompterLesFomations() {
		long resultat = formationRepo.count();
		
		assertThat(resultat).isEqualTo(2);
	}
	
	@Test
	public void doitRechercherParNomFormation() {
		List<Formation> resultat = formationRepo.findByNomFormation("DOSI");
		
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getNomFormation()).isEqualTo("DOSI");
		assertThat(resultat.get(0).getCodeFormation()).isEqualTo("33");
	}
	

}
