package product_2k20.devchallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Ednilson Luis ALfredo Sarmento
 * **/

@SpringBootApplication
public class DevchallengeApplication{

    public static void main(String[] args) {

        SpringApplication.run(DevchallengeApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
