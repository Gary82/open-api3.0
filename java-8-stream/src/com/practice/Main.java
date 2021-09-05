package com.practice;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        loadedType();
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "peach", "watermelon","mango", "orange"));
        System.out.println("data: " + fruits);
        streamFilter(fruits);
        streamMap(fruits);
        streamFlatMap();
        streamDistinct();
        streamSorted(fruits);
        streamPeek(fruits);
        streamLimit(fruits);
        streamSkip(fruits);
        streamCollect(fruits);
        streamCount(fruits);
        streamReduce(fruits);
        streamForeach(fruits);
    }

    /**
     * 延遲加載與急切加載
     * 延遲加載宣告時並不會使用，它們只是存於內存中，為當需要時才會call相關資源，主要用於節省資源
     */
    private static void loadedType(){
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "peach", "watermelon","mango", "orange"));
        //intermediate
        fruits.stream().peek(fruit-> System.out.print(fruit));
        System.out.println("---------------------");
        //terminal
        fruits.stream().peek(fruit-> System.out.print( fruit + " " )).collect(Collectors.toList());
    }

    /**
     * filter 過濾
     * @param fruits 水果
     */
    private static void streamFilter(List<String> fruits){
        //取得字串開頭為p的水果們
        List<String> filtered = fruits.stream().filter(fruit -> fruit.startsWith("p"))
                .collect(Collectors.toList());
        System.out.println("filtered: " + filtered);
    }

    /**
     * map 映射
     * @param fruits 水果
     */
    private static void streamMap(List<String> fruits){
        //將值轉為大寫
        List<String> mapped = fruits.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("mapped: " + mapped);
    }


    /**
     * flatMap 攤平映射
     */
    private static void streamFlatMap(){
        List<String> fruits1=new ArrayList<>(Arrays.asList("apple", "banana"));
        List<String> fruits2=new ArrayList<>(Arrays.asList("pear", "peach", "watermelon"));
        List<String> fruits3=new ArrayList<>(Arrays.asList("mango", "orange"));
        List<List<String>> fruits=Arrays.asList(fruits1,fruits2,fruits3);
        //將巢狀List值轉為大寫並直接合併為一個List
        List<String> flatMapped = fruits.stream().flatMap(fruit->fruit.stream().map(data->data.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
        System.out.println("flatMapped: " + flatMapped);
    }

    /**
     * distinct 去除重複
     */
    private static void streamDistinct(){
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "peach", "watermelon","mango", "orange"));
        //新增重複的值
        fruits.addAll(new ArrayList<>(Arrays.asList("apple","apple","banana","mango")));
        //去除重複
        List<String> distinct = fruits.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct: " + distinct);
    }

    /**
     * sorted 排序
     * @param fruits 水果
     */
    private static void streamSorted(List<String> fruits){
        //排序
        List<String> sorted = fruits.stream().sorted().collect(Collectors.toList());
        System.out.println("sorted: " + sorted);
    }

    /**
     * peek 擷取操作 (不會回傳變更的內容)
     * @param fruits 水果
     */
    private static void streamPeek(List<String> fruits){
        //用peek將值轉換為大寫
        List<String> peeked = fruits.stream().peek(String::toUpperCase).collect(Collectors.toList());
        System.out.println("peeked: " + peeked);
    }

    /**
     * limit 取前n個值
     * 小於0會報IllegalArgumentException
     * 超過length時，等於回傳全部元素
     * @param fruits 水果
     */
    private static void streamLimit(List<String> fruits){
        //取前三筆
        List<String> limited = fruits.stream().limit(3).collect(Collectors.toList());
        System.out.println("limited: " + limited);
    }

    /**
     * skip 略過前n個值
     * 小於0會報IllegalArgumentException
     * 超過length時，等於回傳空集合
     * @param fruits 水果
     */
    private static void streamSkip(List<String> fruits){
        //略過前三筆
        List<String> skipped = fruits.stream().skip(3).collect(Collectors.toList());
        System.out.println("skipped: " + skipped);
    }

    /**
     * collect 蒐集
     * @param fruits 水果
     */
    private static void streamCollect(List<String> fruits){
        //將List資料蒐集為一個Map
        Map<String,String> collected=fruits.stream().collect(Collectors.toMap(e1->e1, e2->e2));
        System.out.println("collected: " + collected);
    }

    /**
     * count 計數
     * @param fruits 水果
     */
    private static void streamCount(List<String> fruits){
        //算出List數量
        System.out.println("count: " + fruits.stream().count());
    }

    /**
     * reduce 集合處理
     * @param fruits 水果
     */
    private static void streamReduce(List<String> fruits){
        //用reduce設置一個起始參數 然後將運算結果做運算後再將結果推回做下一個運算
        System.out.println(fruits.stream().reduce("reduce:",(total,fruit)->total+" "+fruit));
    }

    /**
     * foreach 遍歷
     * @param fruits 水果
     */
    private static void streamForeach(List<String> fruits){
        //兩者功能類似 但前者基於lambda表達式規定，不可修改原資料之外的值，會報錯
        System.out.println("foreach: ");
        String strawberry="strawberry";
        fruits.forEach(fruit->{
//            strawberry=strawberry+"";
            System.out.print(fruit+" ");
        });
        System.out.println("\nfor-each: ");
        for (String fruit : fruits) {
//            strawberry=strawberry+"";
            System.out.print(fruit + " ");
        }
        System.out.println();
    }
}
