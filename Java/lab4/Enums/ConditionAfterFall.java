package Enums;

public enum ConditionAfterFall {
    PrettyHurted("довольно сильно пострадавший"),
    NoDifference("чувствует себя без изменений"),
    InHoneyTrap("находится в медовой ловушке, не может выбраться"),
    InNutsTrap("находится в ореховой ловушке, не может выбраться"),
    NeedHelp("нуждается в медицинской помощи"),
    Dead("погибший"),
    NotCatched("ещё не пойман");
    private final String condition;
    ConditionAfterFall(String condition) {
        this.condition = condition;
    }
    public String getName() {
        return condition;
    }
}
