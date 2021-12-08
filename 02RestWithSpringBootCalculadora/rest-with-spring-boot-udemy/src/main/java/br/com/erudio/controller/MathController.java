package br.com.erudio.controller;

import static br.com.erudio.converter.NumberConverter.convertToDouble;
import static br.com.erudio.converter.NumberConverter.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {

	@Autowired
	private SimpleMath math;

	@RequestMapping(value = "/average/{numbers}")
	public Double average(@PathVariable(value = "numbers") List<String> numbers) {
		numbers.forEach(value -> {
			valueIsNotNumericException(value);
		});
		return math.average(numbers);
	}

	@RequestMapping(value = "/square/{number}")
	public Double square(@PathVariable(value = "number") String number) {
		valueIsNotNumericException(number);
		return math.square(convertToDouble(number));
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {
		valueIsNotNumericException(numberOne, numberTwo);
		return math.multiplication(convertToDouble(numberOne), convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/division/{numberOne}/{numberTwo}")
	public Double division(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {
		valueIsNotNumericException(numberOne, numberTwo);
		return math.division(convertToDouble(numberOne), convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/substraction/{numberOne}/{numberTwo}")
	public Double substraction(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {
		valueIsNotNumericException(numberOne, numberTwo);
		return math.substraction(convertToDouble(numberOne), convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
		valueIsNotNumericException(numberOne, numberTwo);
		return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
	}
	
	private void valueIsNotNumericException(String number) {
		if (!isNumeric(number)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
	}
	
	private void valueIsNotNumericException(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
	}
}
