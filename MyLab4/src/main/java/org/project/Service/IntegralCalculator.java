package org.project.Service;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Argument;

public class IntegralCalculator {
    private final double lowerLimit, upperLimit, step;
    private final String function;

    public IntegralCalculator(double lowerLimit, double upperLimit, double step, String function) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.function = function;
    }

    public double calculateResultOfIntegral() {
        double result = 0;
        double currentStep = 0;

        if(upperLimit > lowerLimit) {
            currentStep = lowerLimit + step;

            while (currentStep <= upperLimit){
                Expression expression = new Expression(function, new Argument("x = " + currentStep));
                result += step * expression.calculate();
                currentStep += step;
            }
            if(currentStep - upperLimit != step){
                Expression expression = new Expression(function, new Argument("x = " + upperLimit));
                result += (step - currentStep + upperLimit) * expression.calculate();
            }
        } else {
            currentStep = upperLimit + step;

            while (currentStep <= lowerLimit){
                Expression expression = new Expression(function, new Argument("x = " + currentStep));
                result -= step * expression.calculate();
                currentStep += step;
            }
            if(currentStep - lowerLimit != step){
                Expression expression = new Expression(function, new Argument("x = " + lowerLimit));
                result -= (step - currentStep + lowerLimit) * expression.calculate();
            }
        }

        return result;
    }

}
