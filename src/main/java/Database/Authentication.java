package Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Authentication {
    private static String currentUser = null;
    public static String getCurrentUser() {
        return currentUser;
    }
    public static void userAuthentication() {
        System.out.println("Введите имя пользователя");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine().trim();
        if (login.contains("'")) throw new NoSuchElementException();
        try {
            if (Connection.executePreparedStatement("SELECT * FROM USERS WHERE login = '" + login + "'").next()) {
                userLoggingIn(login, scanner);
            } else {
                System.out.println("Пользователя с таким именем не существует, если хотите зарегистрироваться, введите Y, если выйти - нажмите любую клавишу");
                if (scanner.nextLine().trim().equals("Y")) userRegistration(login, scanner);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void userRegistration(String login, Scanner scanner) {
        System.out.println("Введите пароль");
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String salt = saltGetter();
        byte[] hash = md.digest(("U2i1e@!@231" + scanner.nextLine().trim() + salt).getBytes(StandardCharsets.UTF_8));
        Connection.executeStatement("INSERT INTO users (login, hash, salt) VALUES ('" + login + "', '" + Arrays.toString(hash) + "', '" + salt + "')");
        System.out.println("Вы успешно прошли регистрацию");
        currentUser = login;
    }
    private static void userLoggingIn(String login, Scanner scanner) {
        boolean userTrying = true;
        while (userTrying) {
            System.out.println("Введите пароль");
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-384");
                ResultSet resultSetSalt = Connection.executePreparedStatement("SELECT salt FROM USERS WHERE login = '" + login + "'");
                resultSetSalt.next();
                String salt = resultSetSalt.getString(1);
                ResultSet resultSetHash = Connection.executePreparedStatement("SELECT hash FROM USERS WHERE login = '" + login + "'");
                resultSetHash.next();
                byte[] hash = md.digest(("U2i1e@!@231" + scanner.nextLine().trim() + salt).getBytes(StandardCharsets.UTF_8));
                if (Arrays.toString(hash).equals(resultSetHash.getString(1))) {
                    System.out.println("Вы успешно прошли аутентификацию");
                    currentUser = login;
                    userTrying = false;
                } else {
                    System.out.println("Неверный пароль (если хотите выйти из программы, вызовите EOF)");
                }
            } catch (SQLException | NoSuchAlgorithmException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static String saltGetter() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) new Random().nextInt(40, 120));
        }
        return stringBuilder.toString();
    }
}