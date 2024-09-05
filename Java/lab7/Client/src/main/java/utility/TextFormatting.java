package utility;

/**
 * Класс, подсвечивающий ошибки
 */
public class TextFormatting {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static String getRedText(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }

    public static String capitalize(String oldString) {
        return oldString.substring(0, 1).toUpperCase() + oldString.substring(1).toLowerCase();
    }
}