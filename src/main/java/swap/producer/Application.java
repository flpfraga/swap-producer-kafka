package swap.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Classe principal da aplicação produtora Kafka.
 * Responsável pela inicialização do Spring Boot e habilitação do Kafka.
 */
@SpringBootApplication
@EnableKafka
public class Application {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 * 
	 * @param args Argumentos de linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
