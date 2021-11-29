package com.company;

public class Calculator<T> {
    public Boolean calculate(T[] array, T answer) {
        try {
            Integer a=(Integer) array[0];
            Integer b=(Integer) array[1];
            return (a/b)==(Integer)answer;
        }catch (Exception e){
            String a=String.valueOf(array[0]);
            String b=String.valueOf(array[1]);
            return (a+b).equals(String.valueOf(answer));
        }
    }
}
