package br.com.erudio.math;

import static br.com.erudio.converter.NumberConverter.convertToDouble;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SimpleMath {

	public Double average(List<String> numbers) {
		Double average = 0D;
		for (String value : numbers) {
			average += convertToDouble(value);
		}
		return average / numbers.size();
	}

	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}

	public Double substraction(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}

	public Double multiplication(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}

	public Double division(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}

	public Double square(Double number) {
		return Math.sqrt(number);
	}
}
