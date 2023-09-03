package generators;

import com.github.javafaker.Faker;
import models.User;

public class UserGenerator {
    public static User getRandom(){
        Faker faker = new Faker();
        return new User(faker.name().name(), faker.internet().emailAddress(), faker.internet().password(6,10));
    }
}
