package ru.levelp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Olga on 20.09.2016.
 */
public class Phonebook {
    private static ArrayList<Contact> allContacts =null;

    public static void menu(){

        System.out.println("Wellcome! Выберите пункт меню.");
        System.out.println("1 - Добавить контакт");
        System.out.println("2 - Вывести все контакты");
        System.out.println("3 - Удалить контакт");
        System.out.println("4 - Выход");
        systemStart();
    }
    public static void systemStart() {
        Scanner scanner = new Scanner(System.in);
        String entry = scanner.next();

        try {
            if (entry.equals("4")) {
                System.out.println("Выход из системы. Good by!");
                System.exit(0);
            } else if (entry.equals("1")) {

                createContact();
                systemStart();

            } else if (entry.equals("2")) {

                loadAllContacts();
                systemStart();

            } else if (entry.equals("3")) {

                System.out.println("Метод в работе......");
                menu();

            }
            else{
                menu();
            }
        } catch (Exception e) {
            System.out.println("Ошибка:" + e);
            e.printStackTrace();
        }
    }

    private static void createContact() {
        loadAllContacts();
        System.out.println("Введите имя.....");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.next();
        System.out.println("Введите тел. номер.....");
        String phone = scanner1.next();
        System.out.println("Введите эл. адрес.....");
        String email = scanner1.next();
        Contact contact = new Contact(name, phone, email);
        allContacts.add(contact);
        System.out.println("Имя: " + contact.getName().toString() + "\n" +
                "Телефон: " + contact.getPhone().toString() + "\n" +
                "Эл. адрес: " + contact.getEmail().toString());
        System.out.println("Контакт добавлен.");
        saveContacts();
    }

    public static void loadAllContacts(){
        System.out.println("Загрузка телефонной книги....");
        boolean check = true;
        File file = new File("contacts.data");
        try {
           // File file = new File("contacts.data");
            if(!file.exists()){
                file.createNewFile();
                //System.out.println("Контактов нет.");
                allContacts=new ArrayList<Contact>();
            }
            else{
                FileInputStream fileInputStream = new FileInputStream(file.getName());
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
                allContacts=new ArrayList<Contact>();
                for (int i = 0; check; i++) {
                    Contact contact = (Contact) inputStream.readObject();
                    allContacts.add(contact);
                    System.out.println("Имя: " + contact.getName() + " Телефон: " + contact.getPhone() + " eMail: " + contact.getEmail());
                }
                inputStream.close();
            }
        } catch (EOFException e){
            check=false;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не существует.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            check=false;
        }
        if(allContacts==null){
           // System.out.println("Всего контактов "+0);
        }
        else{
            System.out.println("Всего контактов "+allContacts.size());
        }

        //System.out.println(file.getName());
    }

    public static synchronized void saveContacts(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("contacts.data");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for(Contact contact:allContacts){
                outputStream.writeObject(contact);
            }
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Всего контактов "+allContacts.size());
    }
    public static void main(String[] args) {
        menu();
    }
}
