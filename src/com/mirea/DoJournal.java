package com.mirea;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.lang.String;
import java.io.*;
import java.lang.String;

public class DoJournal implements Journal {
    @Override
    public void addUserAction(int UserId, ActionUser Act) throws SaveUserException {
        FileOutputStream f1 = null;      //создаём файловую переменную со значением нулл
        String name;                     //строковая переменная для имени файла
        name = String.valueOf(UserId);   //присваиваем имя файлу
        try {                            //попытка на открытие файла (здесь подчёркивал красным и вывел вот это, далее подобная вещь будет и в других местах встречаться)
            f1 = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            switch (Act) {                //выбор действия и его запись в файл
                case USER_CREATED:
                    f1.write(ActionUser.USER_CREATED.name().getBytes());
                    break;
                case USER_LOGGED_IN:
                    f1.write(ActionUser.USER_LOGGED_IN.name().getBytes());
                    break;
                case ADMIN_LOGGED:
                    f1.write(ActionUser.ADMIN_LOGGED.name().getBytes());
                    break;
                case ADMIN_CHANGED_USER_LEVEL:
                    f1.write(ActionUser.ADMIN_CHANGED_USER_LEVEL.name().getBytes());
                    break;
                case USER_LOGGED_OUT:
                    f1.write(ActionUser.USER_LOGGED_OUT.name().getBytes());
                    break;
                case UPDATED_USER_PASSWORD:
                    f1.write(ActionUser.UPDATED_USER_PASSWORD.name().getBytes());
                    break;
            }
        } catch (IOException e1) {       //обработка базовой ошибки, если такая возникла
            e1.printStackTrace();
        }

        try {                       //попытка закрытия файла с обработкой базовых ошибок, если такие обнаружились
            f1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocumentAction(int UserId, String DocId, ActionDoc Act) throws SaveDocException {
        FileOutputStream f2 = null;           //создаём файловую переменную со значением нулл
        try {                                 //попытка на открытие файла
            f2 = new FileOutputStream(DocId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String name1;                          //строковая переменная для имени файла
        name1 = String.valueOf(UserId);        //переводим ID пользователя в строку
        try {
            f2.write(name1.getBytes());         //каждый раз перед записью действия записывает кто это сделал.
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {                                   //выбор действия и его запись в файл
            switch (Act) {
                case DOCUMENT_CREATED:
                    f2.write(ActionDoc.DOCUMENT_CREATED.name().getBytes());
                    break;
                case DOCUMENT_EDITED:
                    f2.write(ActionDoc.DOCUMENT_EDITED.name().getBytes());
                    break;
                case DOCUMENT_SEARCHED_BY_THE_NAME:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_THE_NAME.name().getBytes());
                    break;
                case DOCUMENT_SEARCHED_BY_THE_TYPE:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_THE_TYPE.name().getBytes());
                    break;
                case DOCUMENT_SEARCHED_BY_DATA_OF_CREATION:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_DATA_OF_CREATION.name().getBytes());
                    break;
                case DOCUMENT_SEARCHED_BY_DATA_OF_EDITION:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_DATA_OF_EDITION.name().getBytes());
                    break;
                case DOCUMENT_SEARCHED_BY_AUTHOR_ID:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_AUTHOR_ID.name().getBytes());
                    break;
                case  DOCUMENT_SEARCHED_BY_DOCUMENT_ID:
                    f2.write(ActionDoc.DOCUMENT_SEARCHED_BY_DOCUMENT_ID.name().getBytes());
                    break;
                case DOCUMENT_UPDATED:
                    f2.write(ActionDoc.DOCUMENT_UPDATED.name().getBytes());
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f2.close();                     //попытка закрытия файла с обработкой базовых ошибок, если такие обнаружились
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionUser[] getUserAction(int UserId) throws WrongUserIdException {
        String s1, name2;                       //создание переменной для считывания из файла и имя файла
        ActionUser[] s = new ActionUser[50];    //создание массива для записи действий
        name2 = String.valueOf(UserId);         //присваиваем имя файла
        File f = new File(name2);               //далее идёт махинация создания 3-х файловых переменных для считывания файла по строкам (т.к. упрощённых идей для реализации не поступало, оставил так)
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        int i = 0;                              //счётчик для массива
        try {
            while ((s1 = br.readLine()) != null) {  //считываем файл ПОСТРОЧНО, нам это важно.
                //обрабатываем считанную строку - пишем ее в массив
                s[i] = ActionUser.valueOf(s1);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {                               //закрытие всех файлов и возврат заполненного массива
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
        // return new ActionUser[0];
    }

    @Override
    public Information[] getDocumentAction(String DocId) throws WrongDocIdException {
        File f = new File(DocId);//далее идёт махинация создания 3-х файловых переменных для считывания файла по строкам
        int j=0;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String s1;//создание переменной для считывания из файла
        Information s2 = new Information();
        ArrayList<Information> s = new ArrayList<>();
        int i = 1;                                  //номер строки файла
        try {
            while ((s1 = br.readLine()) != null) {
                //обрабатываем считанную строку - пишем ее в массив
                s2.writing(i,s1);
               if (i%2==1)
               {s.add(j,s2);
               j++;}
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {                                   //закрытие всех файлов и возврат заполненного массива
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Information [] ss={};
        ss=s.toArray(new Information[s.size()]);
       return ss;
        //return null;
    }


}