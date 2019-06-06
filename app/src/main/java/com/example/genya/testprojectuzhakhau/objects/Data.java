package com.example.genya.testprojectuzhakhau.objects;
//класс объекта, в котором будет храниться информация и идентификационны номер в БД
public class Data {


    private int id, number;

    public Data(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
