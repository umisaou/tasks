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

    public static void work(String[] s) throws Exception {
        int numberOfParam = s.length - 1;
        int id;
        String name = "";
        Sex sex;
        Date date;
        if (s[0].equals("-c")) {
            numberOfParam = (s.length - 1) / 3;
            for (int i = 0; i < numberOfParam; i++) {
                name = s[1];
                if (s[2].equals("м")) {
                    sex = Sex.MALE;
                } else
                    sex = Sex.FEMALE;
                date = new SimpleDateFormat("dd/MM/yyyy").parse(s[3]);
                System.out.println(create(name, sex, date));
            }

        } else if (s[0].equals("-u")) {
            numberOfParam = (s.length - 1) / 4;
            for (int i = 0; i < numberOfParam; i++) {
                id = Integer.parseInt(s[1]);
                name = s[2];
                if (s[3].equals("м")) {
                    sex = Sex.MALE;
                } else
                    sex = Sex.FEMALE;
                date = new SimpleDateFormat("dd/MM/yyyy").parse(s[4]);
                update(id, name, sex, date);
            }
        } else if (s[0].equals("-d")) {
            for (int i = 0; i < numberOfParam; i++) {
                id = Integer.parseInt(s[1]);
                delete(id);
            }
        } else if (s[0].equals("-i")) {
            for (int i = 0; i < numberOfParam; i++) {
                id = Integer.parseInt(s[1]);
                System.out.println(inform(id));
            }
        }
    }
}
