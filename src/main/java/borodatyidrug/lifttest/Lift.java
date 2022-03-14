package borodatyidrug.lifttest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Lift {
    
    public static final String INVITE = "Ожидаю ввода этажа: (для завершения "
            + "введите 0)";
    public static final String RESUME = "Лифт перемещался по следующему маршруту: ";
    public static final String START = "Вы на этаже №";
    public static final String TOTAL_TIME = "Время, затраченное на маршрут, с: ";
    public static final String ERR0 = "Вы ввели некорректное значение.";
    public static final String ERR1 = "Очередь пуста ИЛИ переполнена. Как-то - так.";
    public static final int STOP_TIME = 10; // время пребывания лифта на этаже
    // За это уже получил от Филиппа нагоняй в свое время )) Но работает - не лезь!
    // Вот я и не лезу, а только тестирую )
    public static void p(String s) {System.out.print(s);};
    public static void pln(String s) {System.out.println(s);};
    
    public static int buildRoute(Deque<Movement> route) {
        int totalTime = 0;
        Movement currentMovement = null;
        try {
            while (!route.isEmpty()) {
                currentMovement = route.poll();
                totalTime += currentMovement.getTravelTime() + STOP_TIME;
                pln(currentMovement.toString());
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            p(ERR1);
        }
        p(TOTAL_TIME);
        pln("" + totalTime);
        return totalTime;
    }

    public static void main(String[] args) {
        
        Deque<Movement> route = new ArrayDeque<>();
        Random random = new Random();
        int startFloor = random.nextInt(1, 25); // лифт можно вызвать с любого этажа
        pln(START + startFloor);
        Scanner sc = new Scanner(System.in);
        while(true) {
            pln(INVITE);
            int nextFloor;
            try {
                String input = sc.nextLine();
                nextFloor = Integer.parseInt(input);
                if (nextFloor == 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                pln(ERR0);
                continue;
            }
            try {
                route.addLast(Movement.of(startFloor, nextFloor));
                startFloor = route.getLast().stoppedAt();
            } catch (IllegalArgumentException | IllegalStateException 
                    | NoSuchElementException e) {
                pln(e.getMessage());
            }
        }
        buildRoute(route);
    }
}
