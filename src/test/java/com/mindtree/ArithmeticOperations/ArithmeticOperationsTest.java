package com.mindtree.ArithmeticOperations;

import com.mindtree.ArithmeticOperations.App;
	
/*		public class ArithmeticOperationsTest {
	private App objCalcUnderTest;

	
	public void setUp() {
	//Arrange
	objCalcUnderTest = new App();
	}

	
	public void testAdd() { 
	int a = 15; int b = 20; 
	int expectedResult = 35;
	//Act 
	long result = objCalcUnderTest.add(a, b);
	//Assert
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testSubtract() {
	int a = 25; int b = 20; 
	int expectedResult = 5; 
	long result = objCalcUnderTest.subtract(a, b);
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testMultiply() {
	int a = 10; int b = 25;
	long expectedResult = 250;
	long result = objCalcUnderTest.multiply(a, b);
	Assert.assertEquals(expectedResult, result);
	}

	
	public void testDivide() {
	int a = 56; int b = 10; 
	double expectedResult = 5.6; 
	double result = objCalcUnderTest.divide(a, b);
	Assert.assertEquals(expectedResult, result,0.00005); 
	}

	
	public void testDivideByZero() { 
	int a = 15; int b = 0;
	objCalcUnderTest.divide(a, b);
	} 
	}
	*/
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rishiraj
 */
public class ArithmeticOperationsTest {
    
    private App cal;

    @Before
    public void setUp() throws Exception {
        this.cal= new calculator();
        
    }

    @After
    public void tearDown() throws Exception {
        cal = null;
    }

    @Test
    public void Testadd() throws Exception {
        assertEquals(8,cal.add(5,3));

    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(10,cal.sub(18,8));
    }

    @Test
    public void testMultiplyByZero() throws Exception {
        assertEquals(0,cal.mul(2,0));
    }

    @Test
    public void testDivideByZero() throws Exception {
        assertEquals(0,cal.dividebyZero(2,0));
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(15,cal.mul(3,5));
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(5,cal.testdevide(10,2));
    }
}

