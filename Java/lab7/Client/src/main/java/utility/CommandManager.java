package utility;

import Interfaces.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommandManager implements CommandManagerInterface {

    private final CommandReaderInterface commandReader;
    private final ValidatorInterface validator;
    private final RequestHandlerInterface requestHandler;
    private final ConsoleInterface console;
    private final Set<String> usedScripts;
    private final StudyGroupFactoryInterface studyGroupFactory;

    public CommandManager(CommandReaderInterface aCommandReader) {

        commandReader = aCommandReader;
        validator = Validator.getInstance();
        requestHandler = RequestHandler.getInstance();
        console = Console.getInstance();
        usedScripts = new HashSet<>();
        studyGroupFactory = new StudyGroupFactory();
    }

    @Override
    public void transferCommand(Command aCommand) {

        if (aCommand == null) console.print(TextFormatting.getRedText("\n\tКоманда введена неверно!\n"));

        else if (validator.nonObjectArgumentCommands(aCommand))
            console.print(requestHandler.send(aCommand) + "\n");

        else if (validator.objectArgumentCommands(aCommand))
            console.print(requestHandler.send(aCommand, studyGroupFactory.createStudyGroup()) + "\n");

        else if (validator.validateScriptArgumentCommand(aCommand)) executeScript(aCommand.getArg());

        else console.print(TextFormatting.getRedText("\tКоманда введена неверно!\n"));
    }

    private void executeScript(String scriptName) {

        if (usedScripts.add(scriptName)) {

            try {
                if (usedScripts.size() == 1) console.setExeStatus(true);

                ScriptReader scriptReader = new ScriptReader(this, commandReader, new File(scriptName));
                try {
                    scriptReader.read();

                    System.out.println("\nСкрипт " + scriptName
                            + " успешно выполнен!\n");
                } catch (IOException exception) {

                    usedScripts.remove(scriptName);

                    if (usedScripts.isEmpty()) console.setExeStatus(false);

                    if (!new File(scriptName).exists()) console.print(
                            TextFormatting.getRedText("\n\tЭтот скрипт не найден!\n\n"));
                    else if (!new File(scriptName).canRead()) console.print(
                            TextFormatting.getRedText("\n\tНет привилегий для чтения файла!\n\n"));
                    else console.print("\n\tПроизошла неизвестная ошибка при чтении скрипта!\n\n");
                }
                usedScripts.remove(scriptName);
                if (usedScripts.isEmpty()) console.setExeStatus(false);
            } catch (FileNotFoundException e) {
                console.print("\n\tСкрипт не найден!\n");
            }
        } else console.print(TextFormatting.getRedText("\nПри запуске " + scriptName +
                " возникнет рекурсия, скрипт не может быть выполнен!\n"));
    }
}