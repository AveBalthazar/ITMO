package utility;

import Interfaces.ConsoleInterface;
import Interfaces.SessionWorkerInterface;

import java.io.IOException;
import java.util.regex.Pattern;

public class SessionWorker implements SessionWorkerInterface {

    private final ConsoleInterface console;

    public SessionWorker(Console aConsole) {
        console = aConsole;
    }

    @Override
    public Session getSession() throws IOException {
        console.print(("\nАвторизация / Регистрация... \n"));
        boolean sessionStatus = getSessionStatus();
        return sessionStatus ? new Session(getUsername(), getUserPassword(), TypeOfSession.Login)
                : new Session(getUsername(), getUserPassword(), TypeOfSession.Register);
    }

    /**
     * Тип сессии
     *
     * @return true если происходит авторизация, false - если регистрация
     */
    private boolean getSessionStatus() throws IOException{
        String answer;

        do {
            System.out.print(("\tВведите r, если регистрируетесь, и l, если входите по существующим данным: "));
                answer = console.read();
        } while (answer == null || !answer.equals("r") && !answer.equals("l"));

        return answer.equals("l");
    }

    private String getUsername() throws IOException {

        String username;
        Pattern usernamePattern = Pattern.compile("^\\s*\\b(\\w+)\\b\\s*");

        while (true) {
            console.print(("\tВведите имя пользователя: "));
            username = console.read();
            if (username != null && usernamePattern.matcher(username).find()) break;
            console.print(TextFormatting.getRedText("\tИмя пользователя не должно быть пустым, допустимы только цифры и буквы!\n"));
        }

        return username.trim();
    }

    private String getUserPassword() throws IOException {
        if (System.console() == null) {
            String password;
            Pattern passwordPattern = Pattern.compile("^\\s*([\\d\\w]*)\\s*");
            while (true) {
                console.print(("\tВведите пароль: " +
                        "(Или нажмите enter, если хотите входить без пароля): "));
                password = console.read();
                if (password != null && passwordPattern.matcher(password).find()) break;
                console.print(TextFormatting.getRedText("\tВ пароле допустимы только цифры и буквы!\n"));
            }
            return password.trim();
        } else {
            String password;
            Pattern passwordPattern = Pattern.compile("^\\s*([\\d\\w]*)\\s*");
            while (true) {
                console.print(("\tВведите пароль: \" +\n" +
                        "                        \"(Или нажмите enter, если хотите входить без пароля): \" "));
                password = new String(System.console().readPassword());
                if (passwordPattern.matcher(password).find()) break;
                console.print(TextFormatting.getRedText("\tВ пароле допустимы только цифры и буквы!\n"));
            }
            return password.trim();
        }
    }
}
