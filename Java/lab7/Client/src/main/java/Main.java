import utility.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println(getEntryInformation());
            Console.getInstance().setScanner(scanner);
            String sessionStatus;

            while (true) {
                try {
                    getRequestHandlerProperties(scanner, InetAddress.getLocalHost());
                } catch (UnknownHostException e) {
                    System.out.println(TextFormatting.getRedText("\nПроверьте соединение с сетью и перезапустите приложение!"));
                    return;
                }
                RequestHandler.getInstance().setSocketStatus(true);
                try {
                    sessionStatus = getSession();
                } catch (IOException e) {
                    System.out.println(TextFormatting.getRedText("Не получилось авторизоваться на сервере, попробуйте снова"));
                    return;
                }
                if (!sessionStatus.equals("\n\tAction processed successful!\n")) {
                    System.out.println("Успешно выполнено!");
                    continue;
                }

                System.out.println(RequestHandler.getInstance().getInformation());

                CommandReader commandReader = new CommandReader();
                if (commandReader.enable()) return;
            }
        }
    }

    private static String getEntryInformation() {
        return "\n ";
    }

    private static boolean requestTypeOfAddress(Scanner aScanner) {

        String answer;

        do {
            System.out.print("Изменить адрес сервера?" +
                    "[\"y\", \"n\"]: ");

            answer = aScanner.nextLine();

        } while (!answer.equals("y") && !answer.equals("n"));

        return answer.equals("y");
    }

    private static int getPort(Scanner scanner) {
        String arg;
        Pattern remoteHostPortPattern = Pattern.compile("^\\s*\\b(\\d{1,5})\\b\\s*");

        do {
            System.out.print("Пожалуйста, введите порт (1-65535): ");
            arg = scanner.nextLine();
        } while (!remoteHostPortPattern.matcher(arg).find() || (Integer.parseInt(arg.trim()) >= 65536)
                || (Integer.parseInt(arg.trim()) <= 0));

        return Integer.parseInt(arg.trim());
    }

    private static void getRequestHandlerProperties(Scanner scanner, InetAddress localHostAddress) {

        InetAddress remoteHostAddress = localHostAddress;

        if (requestTypeOfAddress(scanner)) {

            String arg;
            Pattern hostAddress = Pattern.compile("^\\s*([\\w.]+)\\s*");

            do {
                System.out.print("\nВведите адрес сервера (Example: 5.18.215.199): ");
                arg = scanner.nextLine();
            }
            while (!hostAddress.matcher(arg).find());

            try {
                remoteHostAddress = InetAddress.getByName(arg.trim());
            } catch (UnknownHostException e) {
                System.out.println(TextFormatting.getRedText(
                        "\nНе получилось найти сервер по указанному адресу\n " +
                                "Будет использован стандартный адрес (localhost)!"));
            }
        }
        RequestHandler.getInstance().setRemoteHostSocketAddress(
                new InetSocketAddress(remoteHostAddress, getPort(scanner)));
    }

    private static String getSession() throws IOException {
        Session session = new SessionWorker(Console.getInstance()).getSession();
        if (session.getTypeOfSession().equals(TypeOfSession.Register)) {
            return RequestHandler.getInstance().register(session);
        } else {
            return RequestHandler.getInstance().login(session);
        }
    }
}