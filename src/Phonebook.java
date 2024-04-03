import java.util.*;

class Phonebook {
    public static void create(HashMap<String, ArrayList<Integer>> map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To use READY data press \"Enter\"");
        System.out.println("To INSERT your data type \"i\"");
        String answer1 = scanner.nextLine();

        if (answer1.isEmpty()) {
            map.put("Denis", new ArrayList<>(Arrays.asList(+799988871, +799988872)));
            map.put("Oleg", new ArrayList<>(Arrays.asList(+799988871, +799988872)));
            map.put("Ivan", new ArrayList<>(Arrays.asList(+799988873, +799988874, +799988875, +799988876)));
            map.put("Pavel", new ArrayList<>(Arrays.asList(+799988877, +799988878, +799988879)));
            map.put("Nikolay", new ArrayList<>(Arrays.asList(+799988810, +799988811, +799988812, +799988813, +799988814)));
            map.put("Maksim", new ArrayList<>(Arrays.asList(+799988872)));
        }else{
            while(true){
                System.out.println("Input name: ");
                String nameKey = scanner.nextLine();
                System.out.println("Input number: ");
                int numValue = scanner.nextInt();
                scanner.nextLine();

                //1-st option:
                if(map.containsKey(nameKey)) {
                    map.get(nameKey).add(numValue);
                } else {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    numbers.add(numValue);
                    map.put(nameKey, numbers);
                }
                //2-nd option:
                // if (map.get(nameKey) == null) {
                //     map.put(nameKey, new ArrayList<Integer>());
                // }
                // map.get(nameKey).add(nameValue);

                System.out.println("To add more contacts press \"Enter\"");
                System.out.println("To exit create mode type \"e\"");
                String answer2 = scanner.nextLine();
                if (answer2.equals("e")) {break;}
            }
        }
    }



    public static void read(HashMap<String, ArrayList<Integer>> map) {
        System.out.println("\nPhoneBook BEFORE sorting the amount of phones in descending order: \n");
        map.forEach((key, value) -> System.out.println(key + "\t-\t" + value.size() + " phones: " + value));
    }



    public static void sortedRead(HashMap<String, ArrayList<Integer>> map) {
        Comparator<String> comparator = new SortByPhoneAmount(map);

        TreeMap<String, ArrayList<Integer>> sortedPhoneBook = new TreeMap<>(comparator);
        sortedPhoneBook.putAll(map);
        System.out.println("\nPhoneBook AFTER sorting the amount of phones in descending order: \n");
        sortedPhoneBook.forEach((key, value) -> System.out.println(key + "\t-\t" + value.size() + " phones: " + value));
    }



    public static void update(HashMap<String, ArrayList<Integer>> map) {
        read(map);
        System.out.println("_______________________________________");

        class Local{
            String name;

            public Local(String name){
                this.name = name;
            }

            void updatePhone(){
                Scanner scanner = new Scanner(System.in);
                while(true){
                    System.out.println(name + " has the following phone\\phones: " + map.get(name));
                    System.out.println("INSERT contact's PHONE number that you want to update: ");
                    Integer currentPhone = scanner.nextInt();
                    System.out.println("INSERT contact's NEW PHONE number: ");
                    Integer newPhone = scanner.nextInt();
                    scanner.nextLine();
                    map.get(name).add(newPhone);
                    map.get(name).remove(currentPhone);
                    System.out.println("Phone number is updated!");
                    System.out.println(name + " has the following phone data after update" + map.get(name));

                    if(map.get(name).size() > 1){
                        System.out.println("Type \"p\" if you want to update " + name + "'s another phone number.\nOtherwise PRESS \"Enter\" to exit update mode.");
                        String answer = scanner.nextLine();
                        if(answer.isEmpty()) {break;}
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println("Insert/select contact name: ");
        Scanner scanner = new Scanner(System.in);
        String currentName = scanner.nextLine();

        System.out.println("INSERT NEW NAME if you want to update the current name.\nOtherwise PRESS \"Enter\" BUTTON to only update contact's phone number");

        String newName = scanner.nextLine();

        if(newName.isEmpty()){
            new Local(currentName).updatePhone();
        }else{
            ArrayList<Integer> mapValue = map.remove(currentName);
            map.put(newName, mapValue);
            System.out.println("Name is updated!");
            System.out.println("Type \"p\" if you want to update contact's phone number.\nOtherwise PRESS \"Enter\" BUTTON to exit from update mode.");
            String answer = scanner.nextLine();
            if(answer.equals("p")){
                new Local(newName).updatePhone();
            }
        }
    }


    public static void delete(HashMap<String, ArrayList<Integer>> map) {
        read(map);
        System.out.println("_______________________________________");

        System.out.println("Insert/select contact name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Type \"d\" if you want to delete selected contact with all its phone numbers.\nOtherwise PRESS \"Enter\" BUTTON just to delete contact's only ONE PHONE NUMBER");
        String answer1 = scanner.nextLine();

        if(answer1.isEmpty()){
            while(true){
                System.out.println(name + " has the following phone\\phones: " + map.get(name));
                System.out.println("INSERT contact's PHONE number that you want to delete: ");
                Integer phone = scanner.nextInt();
                scanner.nextLine();
                map.get(name).remove(phone);
                System.out.println("Phone number is deleted!");
                System.out.println(name + " has the following phone data after deletion: " + map.get(name));

                if(map.get(name).size() > 1){
                    System.out.println("Type \"p\" if you want to delete " + name + "'s another phone number.\nOtherwise PRESS \"Enter\" to exit update mode.");
                    String answer2 = scanner.nextLine();
                    if(answer2.isEmpty()) {break;}
                } else {
                    break;
                }
            }
        }else{
            map.remove(name);
            System.out.println(name + " contact with all its phone numbers is deleted!");
        }
    }


    public static void main(String[] args) {
        HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

        System.out.println("Instruction for the program: ");
        System.out.println("type 'c' to create contact");
        System.out.println("type 'r' to read contact without sorted");
        System.out.println("type 'sr' to sort and read contact");
        System.out.println("type 'u' to update contact");
        System.out.println("type 'd' to delete contact");
        System.out.println("type 'e' to exit the program\n");

        System.out.println("Start of the program. \nAt least one contact must be created.\n");
        create(phoneBook);

        Scanner scanner = new Scanner(System.in);

        loop : while(true) {
            System.out.println("\nSelect an action:  ");
            String action = scanner.nextLine();

            switch (action) {
                case "c":
                    create(phoneBook);
                    break;
                case "r":
                    read(phoneBook);
                    break;
                case "sr":
                    sortedRead(phoneBook);
                    break;
                case "u":
                    update(phoneBook);
                    break;
                case "d":
                    delete(phoneBook);
                    break;
                case "e":
                    break loop;
                default:
                    System.out.println("Wrong selection!");
            }
        }
    }
}

class SortByPhoneAmount implements Comparator<String>{
    HashMap<String, ArrayList<Integer>> map;

    public SortByPhoneAmount(HashMap<String, ArrayList<Integer>> map){
        this.map = map;
    }

    @Override
    public int compare(String key1, String key2){
        List<Integer> mapValue1 = this.map.get(key1);
        List<Integer> mapValue2 = this.map.get(key2);
        Integer length1 = mapValue1.size();
        Integer length2 = mapValue2.size();
        if ((length2 - length1) == 0){return length2;}
        else{return length2-length1;}
    }
}
