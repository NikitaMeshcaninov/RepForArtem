package pages;

import java.util.Random;

/**
 * Created by Nikita on 19.07.2016.
 */
public class TestData {
    public static final String PERSONAGE_NAME = "Тестовый персонаж_";
    public static final String MASTER_LOGIN = "shepard";
    public static final String MASTER_ROLE = "Мастер";
    //public static final String PLAYER_ROLE = "Игрок";
    public static final String RACE = "Гном";
    public static final String XP = "200";
    public static final String USER_LOGIN = "shepard";//"shmublon";
    public static final String MERIT = "Внушительность";
    //название навыка
    public static final String SKILL_NAME = "TestSkillName";
    //описание навыка
    public static final String SKILL_DESCRIPTION = "TestSkillDescription";
    public static final int timeSleep = 500;

    public static String generateString(String characters, int length)
    {
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
