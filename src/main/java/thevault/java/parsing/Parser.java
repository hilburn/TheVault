package thevault.java.parsing;

import java.util.LinkedList;
import java.util.List;

public class Parser
{
    public static double doMath(String formula)
    {
        List<Double> numbers = new LinkedList<>();
        List<MathOperator> operators = new LinkedList<>();
        formula = formula.replaceAll(" ", ""); // trim all white space
        String currentString = "";
        for (char c : formula.toCharArray())
        {
            if (c == '.')
            {
                currentString += c;
                continue;
            }
            
            try
            {
                Double.parseDouble(currentString + c);
                currentString += c;
            } catch (NumberFormatException e)
            {
                numbers.add(Double.parseDouble(currentString));
                currentString = "";
                MathBasicOperator operator = MathBasicOperator.findBySign("" + c);
                if (operator == null) throw new IllegalArgumentException("Illegal operator used");
                operators.add(operator);
            }
        }
        numbers.add(Double.parseDouble(currentString));
        return doMath(numbers, operators);
    }
    
    private static double doMath(List<Double> numbers, List<MathOperator> operators)
    {
        int countNumbers = numbers.size();
        int countOperators = operators.size();
        if (countNumbers == 0 || countNumbers - 1 != countOperators) throw new IllegalArgumentException("number of numbers and operators are invalid");
        double result = numbers.get(0);
        for (int i = 1; i < countNumbers; i++)
        {
            if (operators.get(i - 1).argsCount() == 1) result += operators.get(i - 1).apply(numbers.get(i));
            if (operators.get(i - 1).argsCount() == 2) result = operators.get(i - 1).apply(result, numbers.get(i));
        }
        return result;
    }
}
