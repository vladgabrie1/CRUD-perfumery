package be.kdg.programming3.individualproject;

import be.kdg.programming3.individualproject.presentation.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication
public class IndividualProjectApplication {

    public static void main(String[] args) {
        var contex = SpringApplication.run(IndividualProjectApplication.class, args);
        View view = contex.getBean(View.class);
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
