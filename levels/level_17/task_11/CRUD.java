package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CRUD {
    private static int create(String name, Sex sex, Date date) {
        if (sex == Sex.MALE) {
            Solution.allPeople.add(Person.createMale(name, date));
        } else
            Solution.allPeople.add(Person.createFemale(name, date));
        int newPersonID = Solution.allPeople.size() - 1;
        return newPersonID;
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

    public static void work(String[] s) throws Exception {
        int numberOfParam = s.length - 1;
        int id;
        String name = "";
        Sex sex;
        Date date;
        switch (s[0]) {
            case "-c":
                numberOfParam = (s.length - 1) / 3;
                for (int i = 0; i < numberOfParam; i++) {
                    name = s[1 + i * 3];
                    if (s[2 + i * 3].equals("м")) {
                        sex = Sex.MALE;
                    } else
                        sex = Sex.FEMALE;
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(s[3 + i * 3]);
                    synchronized (Solution.allPeople) {
                        System.out.println(create(name, sex, date));
                    }
                }
                break;
            case "-u":
                numberOfParam = (s.length - 1) / 4;
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(s[1 + i * 4]);
                    name = s[2 + i * 4];
                    if (s[3 + i * 4].equals("м")) {
                        sex = Sex.MALE;
                    } else
                        sex = Sex.FEMALE;
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(s[4 + i * 4]);
                    synchronized (Solution.allPeople) {
                        update(id, name, sex, date);
                    }
                }
                break;
            case "-d":
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(s[1 + i]);
                    synchronized (Solution.allPeople) {
                        delete(id);
                    }
                }
                break;
            case "-i":
                for (int i = 0; i < numberOfParam; i++) {
                    id = Integer.parseInt(s[1 + i]);
                    synchronized (Solution.allPeople) {
                        System.out.println(inform(id));
                    }
                }
        }
    }
}
