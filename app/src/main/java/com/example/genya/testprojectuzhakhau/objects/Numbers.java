package com.example.genya.testprojectuzhakhau.objects;

import java.util.ArrayList;

public class Numbers {

    ArrayList<Integer> numbers;
    private int count;

    public Numbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
        count = numbers.size();
    }

    public Numbers() {
        numbers = new ArrayList<>();
        count = 0;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
        count = numbers.size();
    }

    public void addElement(){
        numbers.add(count);
        count++;
    }

    public void delElement(int i){
        numbers.remove(i);
    }

    public int getNumbersSize(){
        return numbers.size();
    }

    public int getElementOfNumbers(int i){
        return numbers.get(i);
    }
}
