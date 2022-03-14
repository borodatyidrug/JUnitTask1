package tests;

import borodatyidrug.lifttest.Movement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static borodatyidrug.lifttest.Lift.buildRoute;

public class LiftTests {
    
    @BeforeAll
    static void initSuite() {
        System.out.println("Start testing");
    }
    
    @AfterAll
    static void completeSuit() {
        System.out.println("End testing");
    }
    
    @BeforeEach
    void initTest() {
        System.out.println("New test:");
    }
    
    @AfterEach
    void finalizeTest() {
        System.out.println("Test completed!");
    }
    
    @Test
    public void testValidArgs_Movement_of() {
        
        final int startFloor = 3;
        final int nextFloor = 9;
        Assertions.assertDoesNotThrow(() -> Movement.of(startFloor, nextFloor));
    }
    
    @Test
    public void testValidArgs_Movement_getTravelTime () {
        int startFloor = 2;
        int nextFloor = 25;
        int expectedResult = 115;
        Assertions.assertEquals(expectedResult, Movement.of(startFloor, nextFloor).getTravelTime());
    }
    
    @ParameterizedTest
    @MethodSource("invalidArgsGenerator")
    void testInvalidArgs_Movement_of(int startFloor, int nextFloor) {
        Class<IllegalArgumentException> exceptionType = IllegalArgumentException.class;
        Assertions.assertThrows(exceptionType, () -> Movement.of(startFloor, nextFloor));
    }

    @Test
    void testTotalTimeOfRoute_validArgs_Lift_buildRoute() {
        // валидные этажи
        int[] floors = new int[] {14, 5, 7, 22, 3};
        int startFloor = 16;
        // ожидаемое totalTime маршрута
        int expected = 285;
        Deque<Movement> route = new ArrayDeque<>();
        for(int currFloor : floors) {
            Movement mv = Movement.of(startFloor, currFloor);
            route.add(mv);
            startFloor = mv.stoppedAt();
        }
        // buildRoute() возвращает totalTime маршрута
        Assertions.assertEquals(borodatyidrug.lifttest.Lift.buildRoute(route), expected);
    }

    static Stream<Arguments> invalidArgsGenerator() {
        return Stream.of(
                Arguments.of(-1, 3),
                Arguments.of(0, 3),
                Arguments.of(4, 67),
                Arguments.of(4, 4)
        );
    }
}
