package main.java.com.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMethods {


    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,2,44,76,23,32,96);


        //without usage of stream
        List<Integer> filteredList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        for(Integer i:integerList){
            if(i % 2 == 0){
                filteredList.add(i);
            }
        }

        System.out.println("Without Streams Operation : "+filteredList);

        // filter method in stream
        filteredList = integerList.stream().filter(i -> i % 2 == 0).toList();
        System.out.println("With Streams Operation : "+ filteredList);

        resultList = integerList.stream().map(i -> i * 3).toList();
        System.out.println("Result List after using map method " + resultList);

        resultList = integerList.stream().sorted().toList();
        System.out.println("Naturally sorted list " + resultList);

        resultList = integerList.stream().sorted((i,j) -> j - i).toList();
        System.out.println("Customized sorted list " + resultList);

        Integer min = integerList.stream().min(Integer::compareTo).get();
        System.out.println("Min in list " + min);

        Integer max = integerList.stream().max(Integer::compareTo).get();
        System.out.println("Max in list " + max);




    }



}
