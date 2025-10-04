package utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGeneration {
    private static final Faker faker = new Faker();
    private static final String[] REGION_PREFIXES = {
            "A","B","CH","Y","TX","H","CC","PP","T","P",
            "BT","EB","CT","X","K","CM","PB","OB","EH","PA",
            "E","KH","PK","CA","C","CB","CO","BP","M","BH"
    };
    private static final String BG_LETTERS = "ABEKMHOPCTYX";

    public static String randomPlate() {
        return REGION_PREFIXES[ThreadLocalRandom.current().nextInt(REGION_PREFIXES.length)]
                + RandomStringUtils.randomNumeric(4)
                + RandomStringUtils.random(2, BG_LETTERS);
    }
    public static String randomVin() {
        return RandomStringUtils.randomAlphabetic(10).toUpperCase() + RandomStringUtils.randomNumeric(7);
    }
    public static String randomServiceName() {
          return faker.lorem().word().toLowerCase();
    }
    public static String randomUsername() {
          return faker.name().username();
    }
    public static String randomEmail() {
          return faker.internet().emailAddress();
    }
    public static String randomFirstName() {
          return faker.name().firstName().toLowerCase();
    }
    public static String randomLastName() {
          return faker.name().lastName().toLowerCase();
    }
    public static long randomNumber() {
          return faker.number().randomNumber(10, true);
    }
    public static String randomPrice() {
        return faker.number().digits(5);
    }
    public static String randomModelName() {
        return faker.lorem().word().toLowerCase();
    }
}