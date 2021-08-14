package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class DemoApplicationTests {
 Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int a  = 20;
		int b = 20;
		//when
		int result = calculator.add(a,b);
		//then
		int expected =40;
		assertThat(result).isEqualTo(expected);

	}
  	class Calculator{
		int add(int a,int b){
			return a+b;
		}
	}
}
