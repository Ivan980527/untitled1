package com.mirea;


public class Information {
    private String UI;
    private ActionDoc Act0;

    Information()                           //коструктор
    {
        UI = "10000000";

        Act0 = ActionDoc.DOCUMENT_CREATED;
    }

    void writing(int m, String str) {
        if (m % 2 != 0) {               //если строка нечётная - это пользовательский ID, чётная - действие
            UI = str;

        } else {
            Act0 = ActionDoc.valueOf(str);

        }

    }

    public String getUI() {           //геттер пользоватеольского ID
        return UI;
    }

    public void setUI(String UI) {   //сеттер пользовательского ID
        this.UI = UI;
    }

    public ActionDoc getAct0() {     //геттер ID документа
        return Act0;
    }

    public void setAct0(ActionDoc act0) {  //сеттер ID документа
        Act0 = act0;
    }
}

