package com.example.braintraining;

public class Generator {

	private String Equation = "";
	private double EquationResult = 0.0;
	int counter;

	public void novice() {
		Loop(0);
	}

	public void easy() {
		int HowManyIntegers = 2 + (int) (Math.random() * ((3 - 2) + 1));
		if (HowManyIntegers == 2) {
			Loop(0);
		} else if (HowManyIntegers == 3) {
			Loop(1);
		}
	}

	public void medium() {
		int HowManyIntegers = 2 + (int) (Math.random() * ((4 - 2) + 1));
		if (HowManyIntegers == 2) {
			Loop(0);
		} else if (HowManyIntegers == 3) {
			Loop(1);
		} else if (HowManyIntegers == 4) {
			Loop(2);
		}
	}

	public void guru() {
		int HowManyIntegers = 4 + (int) (Math.random() * ((6 - 4) + 1));
		if (HowManyIntegers == 4) {
			Loop(2);
		} else if (HowManyIntegers == 5) {
			Loop(3);
		} else if (HowManyIntegers == 6) {
			Loop(4);
		}
	}

	public void TwoIntergers() {
		double Value1 = 1 + (int) (Math.random() * ((10 - 1) + 1));
		double Value2 = 1 + (int) (Math.random() * ((10 - 1) + 1));
		int Operator = 1 + (int) (Math.random() * ((4 - 1) + 1));
		if (Operator == 1) {
			Equation = (int) Value1 + "+" + (int) Value2;
			EquationResult = Value1 + Value2;
			EquationResult = Math.round(EquationResult);
		} else if (Operator == 2) {
			Equation = (int) Value1 + "-" + (int) Value2;
			EquationResult = Value1 - Value2;
			EquationResult = Math.round(EquationResult);
		} else if (Operator == 3) {
			Equation = (int) Value1 + "*" + (int) Value2;
			EquationResult = Value1 * Value2;
			EquationResult = Math.round(EquationResult);
		} else if (Operator == 4) {
			Equation = (int) Value1 + "/" + (int) Value2;
			EquationResult = Value1 / Value2;
			EquationResult = Math.round(EquationResult);
		}
	}

	public void Loop(int count) {
		counter = count;
		TwoIntergers();
		for (int x = 0; x < counter; x++) {
			double Value1 = 1 + (int) (Math.random() * ((10 - 1) + 1));
			int Operator = 1 + (int) (Math.random() * ((4 - 1) + 1));
			Equation = getEquation();
			EquationResult = getEquationResult();
			if (Operator == 1) {
				Equation = Equation + "+" + (int) Value1;
				EquationResult = EquationResult + Value1;
			} else if (Operator == 2) {
				Equation = Equation + "-" + (int) Value1;
				EquationResult = EquationResult - Value1;
			} else if (Operator == 3) {
				Equation = Equation + "*" + (int) Value1;
				EquationResult = EquationResult * Value1;
			} else if (Operator == 4) {
				Equation = Equation + "/" + (int) Value1;
				EquationResult = EquationResult / Value1;
			}
		}
	}

	public String getEquation() {
		return Equation;
	}

	public int getEquationResult() {

		return (int) EquationResult;
	}
}