package com.pagamento.hireus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * O projeto HireUs consiste em uma REST api com regra de negócio: Folha de pagamento.
 * O sistema permite criar funcionários, cargos, e aplicar regras de negócios financeiros e acompanhar o holerite mensal.
 *
 * @Squad:
 * @author Valdeir Camargo   Github @Camargovf
 * @author Laerson           Github @laersonjr
 * @author Gabriel Kopke     Github @Gabriel-kopke-jr
 * @author Leonardo          Github @leonardomeloti
 * @author Marcklen          Github @Marcklen
 * @author Bruno Brito       Github @brunopbrito31
 *
 */

@SpringBootApplication
public class HireUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HireUsApplication.class, args);
	}

}
