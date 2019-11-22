package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        int numberOfParam = args.length - 1;
        int id;
        String name = "";
        Sex sex;
        Date date;
        switch (args[0]) {
            case "-c":
                numberOfParam = (args.length - 1) / 3;
                for (int i = 0; i < numberOfParam; i++) {
                    name = args[1 + i * 3];
                    if (args[2 + i * 3].equals("м")) {
                        sex = Sex.MALE;
                    } else
                        sex = Sex.FEMALE;
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(args[3 + i * 3]);
                    synchronized (allPeople) {
                        System.out.println(create(name, sex, date));
                    }
                }
                break;
            case "-u":
                numberOfParam = (args.length - 1) / 4;
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(args[1 + i * 4]);
                    name = args[2 + i * 4];
                    if (args[3 + i * 4].equals("м")) {
                        sex = Sex.MALE;
                    } else
                        sex = Sex.FEMALE;
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(args[4 + i * 4]);
                    synchronized (allPeople) {
                        update(id, name, sex, date);
                    }
                }
                break;
            case "-d":
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(args[1 + i]);
                    synchronized (allPeople) {
                        delete(id);
                    }
                }
                break;
            case "-i":
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(args[1 + i]);
                    synchronized (allPeople) {
                        System.out.println(inform(id));
                    }
                }
        }

    }

    private static int create(String name, Sex sex, Date date) {
        if (sex == Sex.MALE) {
            Solution.allPeople.add(Person.createMale(name, date));
        } else
            Solution.allPeople.add(Person.createFemale(name, date));
        return Solution.allPeople.size() - 1;
    }

    private static void update(int id, String name, Sex sex, Date date) {
        Solution.allPeople.get(id).setName(name);
        Solution.allPeople.get(id).setSex(sex);
        Solution.allPeople.get(id).setBirthDate(date);
    }

    private static void delete(int id) {
        Solution.allPeople.get(id).setName(null);
        Solution.allPeople.get(id).setSex(null);
        Solution.allPeople.get(id).setBirthDate(null);
    }

    private static String inform(int id) {
        String information = "";
        information += Solution.allPeople.get(id).getName() + " ";
        if (Solution.allPeople.get(id).getSex() == Sex.MALE) {
            information += "м" + " ";
        } else
            information += "ж" + " ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        information += sdf.format(Solution.allPeople.get(id).getBirthDate());
        return information;

    }

}
