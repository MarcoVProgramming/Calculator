package application;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Calculator {

	private static List<String> operators;
	private static List<Double> numbers;
	private static boolean eqStartsWithMinus;

	public double calculate(String equation) {
		if (equation.startsWith("-")) {
			equation = equation.substring(1, equation.length());
			eqStartsWithMinus = true;
		}
		StringTokenizer st = new StringTokenizer(equation, "+-*/");
		numbers = new ArrayList<Double>();
		while (st.hasMoreTokens()) {

			numbers.add(Double.parseDouble(st.nextToken()));

		}

		if (eqStartsWithMinus) {
			double a = numbers.get(0);
			System.out.println(-a);
			numbers.set(0, -a);
		}

		operators = extractOperators(equation);

		while (eqHasMoreCalcOperators()) {
			int nextCalcOperator = getNextCalcOperatorIndex();
			double result = 0;
			if (operators.get(nextCalcOperator).equals("*")) {
				result = numbers.get(nextCalcOperator) * numbers.get(nextCalcOperator + 1);
			} else if (operators.get(nextCalcOperator).equals("/")) {
				result = numbers.get(nextCalcOperator) / numbers.get(nextCalcOperator + 1);
			} else {
				System.out.println("Fehler: Operator nicht * oder /");
			}
			numbers.remove(nextCalcOperator);
			numbers.remove(nextCalcOperator);
			numbers.add(nextCalcOperator, result);
			operators.remove(nextCalcOperator);

		}

		while (!operators.isEmpty()) {
			double result = 0;
			if (operators.get(0).equals("+")) {
				result = numbers.get(0) + numbers.get(1);
			} else if (operators.get(0).equals("-")) {
				result = numbers.get(0) - numbers.get(1);
			} else {
				System.out.println("Fehler bei Berechnung von + und -");
			}
			numbers.remove(0);
			numbers.remove(0);
			numbers.add(0, result);
			operators.remove(0);
		}

		return numbers.get(0);
	}

	private static boolean eqHasMoreCalcOperators() {
		for (String s : operators) {
			if (s.equals("*") || s.equals("/"))
				return true;
		}
		return false;
	}

	private static int getNextCalcOperatorIndex() {
		for (int i = 0; i < operators.size(); i++) {
			if (operators.get(i).equals("/") || operators.get(i).equals("*")) {
				return i;
			}
		}
		return -1;
	}

	private static List<String> extractOperators(String equation) {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '/'
					|| equation.charAt(i) == '*') {
				list.add(equation.substring(i, i + 1));
			}
		}

		return list;
	}
}
