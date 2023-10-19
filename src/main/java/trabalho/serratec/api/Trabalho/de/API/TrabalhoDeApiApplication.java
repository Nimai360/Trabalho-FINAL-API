package trabalho.serratec.api.Trabalho.de.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabalhoDeApiApplication {
   
	/*
	 * Etapas Rota:
	 * - Filter
	 * - Controller
	 * - Service
	 * - Repository
	 * 
	 * Independentes:
	 * - Exception
	 * - Model
	 * - Util
	 */

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoDeApiApplication.class, args);
	}

}
