package com.local.tacocloud.database.config;

import com.local.tacocloud.database.repository.IngredientRepository;
import com.local.tacocloud.database.repository.TacoRepository;
import com.local.tacocloud.database.repository.UserRepository;
import com.local.tacocloud.database.entity.Ingredient;
import com.local.tacocloud.database.entity.Ingredient.Type;
import com.local.tacocloud.database.entity.Taco;
import com.local.tacocloud.database.entity.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

// @Profile also works for beans methods
@Profile("dev") // For more than one profile, write "dev", "cloud", etc., or "!prod" to indicate every profile but prod
@Configuration
public class InitialDataLoad {

   @Bean
   public ApplicationRunner loadData(IngredientRepository ingredientRepo,
                                     TacoRepository tacoRepo,
                                     UserRepository userRepo,
                                     PasswordEncoder encoder) {
      return args -> {
         Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
         Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
         Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
         Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
         Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
         Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
         Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
         Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
         Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
         Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

         ingredientRepo.save(flourTortilla);
         ingredientRepo.save(cornTortilla);
         ingredientRepo.save(groundBeef);
         ingredientRepo.save(carnitas);
         ingredientRepo.save(tomatoes);
         ingredientRepo.save(lettuce);
         ingredientRepo.save(cheddar);
         ingredientRepo.save(jack);
         ingredientRepo.save(salsa);
         ingredientRepo.save(sourCream);

         Taco taco1 = new Taco();
         taco1.setName("Carnivore");
         taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
         tacoRepo.save(taco1);

         Taco taco2 = new Taco();
         taco2.setName("Bovine Bounty");
         taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
         tacoRepo.save(taco2);

         Taco taco3 = new Taco();
         taco3.setName("Veg-Out");
         taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
         tacoRepo.save(taco3);

         if(!userRepo.existsUserByUsername("tizor")) {
            userRepo.save(new User("tizor",
               encoder.encode("123456789"),
               "Alberto Ortiz",
               "Calle falsa 123",
               "Bogotá",
               "Bogotá",
               "110832",
               "3000000000"));
         }
      };
   }

}
