package dtu.calculator;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Before;


@Before
public static void before() {
    Memory.reset()
    		System.out.println("Before All init() method called");

}