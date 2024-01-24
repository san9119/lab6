package com.example.reactivestream;

import reactor.core.publisher.Mono;

public class ReactiveStream {

    public static void main(String[] args) {
        int inputNumber = 5;

        Mono<Integer> reactiveMono = processNumber(inputNumber);
        reactiveMono.subscribe(result -> System.out.println("Результат: " + result));
    }

    public static Mono<Integer> processNumber(int number) {
        return square(number)
                .flatMap(ReactiveStream::subtractSum)
                .flatMap(ReactiveStream::convertToMono);
    }

    public static Mono<Integer> square(int number) {
        System.out.println("Возовдим в квадрат: " + number);
        return Mono.just(number * number);
    }

    public static Mono<Integer> subtractSum(int number) {
        System.out.println("Вычитаем сумму из: " + number);
        int sum = calculateSum(number);
        return Mono.just(number - sum);
    }

    public static int calculateSum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        System.out.println("Сумма: " + sum);
        return sum;
    }

    public static Mono<Integer> convertToMono(int number) {
        System.out.println("Конвертируем в Mono: " + number);
        return Mono.just(number);
    }
}
