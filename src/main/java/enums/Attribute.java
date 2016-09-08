package enums;

public enum Attribute {
    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    SPEED("Скорость"),
    REACTION("Реакция"),
    PERCEPTION("Восприятие"),
    ENDURANCE("Выносливость"),
    VITALITY("Живучесть"),
    WISDOM("Мудрость"),
    INTELLIGENCE("Интеллект"),
    WILL("Воля"),
    CHARISMA("Харизма");

    private String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
