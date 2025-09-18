package utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGeneration {
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
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }
    public static String randomUsername() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }
    public static String randomEmail() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@gmail.com";
    }
    public static String randomFirstName() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }
    public static String randomLastName() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }
    public static String randomNumber() {
        return RandomStringUtils.randomNumeric(10).toLowerCase();
    }
}