import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main obj = new Main();
        Scanner sc = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File usersFile = obj.getCreateFile("users.json");
        List<User> usersList = new ArrayList<>();

        try {
            usersList = objectMapper.readValue(usersFile, new TypeReference<>() {
            });
        } catch (Exception ignored) {
        }

        User newUser;

        while(true){
            newUser = obj.enterData(obj, sc, usersList);
            if(!obj.isUserValid(usersList, newUser)){break;}else{
                System.out.println("Blogai ivesti duomenis arba toks User jau egzistuoja.");
                sc.nextLine();
            }
        }

        usersList.add(newUser);

        obj.menu(sc, usersList);

        try {
            objectMapper.writeValue(usersFile, usersList);
            System.out.println("Useriai irasyti.");
        } catch (IOException e) {
            System.out.println("Useriu irasyti nepavyko.");
        }
        System.out.println("Lauksime sugriztant.");


    }

    public User menu(Scanner sc, List<User> usersList) {
        sc.nextLine();
        while (true) {
            System.out.println(" ___________________________________");
            System.out.println("| 1 - Spausdint visus               |");
            System.out.println("| 2 - Iseiti                        |");
            System.out.println("|___________________________________|");

            String select = sc.nextLine();
            switch (select) {
                case "1" -> {
                    System.out.println("Spausdint visus");
                    usersList.forEach(e -> System.out.println(e.toString()));
                }
                case "2" -> {
                    return null;
                }
                default -> {
                    System.out.println("Pasirinkite veiksma.");
                }
            }
        }
    }

    public File getCreateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        return file;
    }

    public User enterData(Main obj, Scanner sc, List<User> usersList){
        String firstname = obj.insertFirstname(sc, usersList);
        String lastname = obj.insertLastname(sc);
        long personalCode = obj.insertPersonalCode(sc);
        return new User(firstname, lastname, personalCode);
    }

    public String insertFirstname(Scanner sc, List<User> usersList) {
        String str;
        while (true) {
            System.out.println("Iveskite varda:");
            try {
                str = sc.nextLine();
                return str;
            } catch (Exception e) {
                System.out.println("Blogai ivestas vardas.");
            }
        }
    }

    private boolean isUserValid(List<User> usersList, User user) {
        return usersList.stream().anyMatch(o -> o.toString().equals(user.toString()));
    }

    public String insertLastname(Scanner sc) {
        String str;
        while (true) {
            System.out.println("Iveskite pavarde:");
            try {
                str = sc.nextLine();
                return str;
            } catch (Exception e) {
                System.out.println("Blogai ivesta pavarde.");
            }
        }
    }

    public long insertPersonalCode(Scanner sc) {
        long num;
        while (true) {
            System.out.println("Iveskite asmens koda:");
            try {
                num = sc.nextLong();
                return num;
            } catch (Exception e) {
                System.out.println("Blogai ivestas asmens kodas.");
            }
        }
    }
}
