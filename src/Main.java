import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		System.out.println("Welcome to my Fraction Calculator!");
		System.out.println("Available Operations:");
		System.out.println(" - Addition (+)");
		System.out.println(" - Subtract (-)");
		System.out.println(" - Multiply (*)");
		System.out.println(" - Division (/)\n");
		List<String> validOperations = Arrays.asList(" + ", " - ", " * ", " / ");
		while(running)
		{
			String toSolve = "";
			while(toSolve.equals(""))
			{
				System.out.print("Please enter something to calculate (ex. 1/2 + 1/4): ");
				String input = scanner.nextLine();
				int operationCount = 0;
				String operationType = "";
				for(String operation : validOperations)
				{
					if(input.contains(operation))
					{
						operationCount++;
						operationType = operation;
					}
				}

				if(operationCount != 1 && operationType.equals(""))
				{
					System.out.println("Couldn't detect any operation. Are you entering the fraction like 1/2 + 1/4? (Spaces matter!)");
				}
				else
				{
					if(operationType.equals(" + "))
					{
						String[] inputSplit = input.split(" \\+ ");
						List<Long> newFraction = basicFractionOperations(handleInput(inputSplit), true);
						System.out.println(newFraction.get(0) + "/" + newFraction.get(1));
					}
					else if(operationType.equals(" - "))
					{
						String[] inputSplit = input.split(" - ");
						List<Long> newFraction = basicFractionOperations(handleInput(inputSplit), false);
						System.out.println(newFraction.get(0) + "/" + newFraction.get(1));
					}
					else if(operationType.equals(" * "))
					{
						String[] inputSplit = input.split(" \\* ");
						handleInput(inputSplit);
					}
					else if(operationType.equals(" / "))
					{
						String[] inputSplit = input.split(" \\/ ");
						handleInput(inputSplit);
					}
				}
			}
		}
		scanner.close();
	}

	public static List<List<Long>> handleInput(String[] input)
	{
		List<Long> firstFractionData  = new ArrayList<Long>();
		while(firstFractionData.isEmpty())
		{
			firstFractionData = retrieveFractionData(input[0]);
			if(firstFractionData.isEmpty())
			{
				System.out.println("Sorry, your first fraction is invalid, please enter your input again.");
				break;
			}
		}

		List<Long> secondFractionData  = new ArrayList<Long>();
		while(secondFractionData.isEmpty())
		{
			secondFractionData = retrieveFractionData(input[1]);
			if(secondFractionData.isEmpty())
			{
				System.out.println("Sorry, your second fraction is invalid, please enter your input again.");
				break;
			}
		}
		return Arrays.asList(firstFractionData, secondFractionData);
	}

	public static boolean[] breakFraction(String input)
	{
		int minusCount = 0;
		int slashCount = 0;
		int underscoreCount = 0;
		for(char ch : input.toCharArray())
		{
			slashCount += (ch == '/' ? 1 : 0);
			underscoreCount += (ch == '_' ? 1 : 0);
			minusCount += (ch == '-' ? 1 : 0);

			if(!(ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == '0' || ch == '_' || ch == '/' || ch == '-') && minusCount < 2 && slashCount < 2 && underscoreCount < 2)
			{
				break;
			}
		}
		return new boolean[] { minusCount == 1, underscoreCount == 1, (underscoreCount == 1 || underscoreCount == 0) && (minusCount == 1 || minusCount == 0) && slashCount == 1 };
	}

	public static boolean secondaryValidationCheck(String input)
	{
		if(!input.contains("_/") &&
				!input.endsWith("/") &&
				!input.endsWith("_") &&
				!input.startsWith("_") &&
				!input.startsWith("/") &&
				!input.endsWith("/0") &&
				!input.contains("."))
		{
			return true;
		}
		return false;
	}

	public static List<Long> retrieveFractionData(String fraction)
	{
		List<Long> fractionData = new ArrayList<Long>();
		boolean[] results = breakFraction(fraction);
		boolean isNegative = results[0];
		boolean isMixed = results[1];
		boolean isValid = results[2];
		if(isValid)
		{
			isValid = secondaryValidationCheck(fraction);
		}
		if(isValid)
		{
			String[] lineSplit = fraction.split("/");
			String numeratorAndMixed = lineSplit[0];
			long denominator = Long.parseLong(lineSplit[1]);
			long mixedNum = 0;
			long numerator = 0;
			if(isMixed)
			{
				String[] splitNumerator = numeratorAndMixed.split("_");
				mixedNum = Long.parseLong(splitNumerator[0]);
				numerator = Long.parseLong(splitNumerator[1]);
			}
			else
			{
				numerator = Long.parseLong(numeratorAndMixed);
			}
			if(mixedNum != 0)
			{
				fractionData.add(mixedNum);
			}
			fractionData.add(numerator);
			fractionData.add(denominator);
		}
		return fractionData;
	}

	public static List<Long> basicFractionOperations(List<List<Long>> fractionData, boolean addition)
	{
		List<Long> firstFraction = fractionData.get(0);
		List<Long> secondFraction = fractionData.get(1);
		if(firstFraction.size() == 3)
		{
			long mixedNumber = firstFraction.get(0);
			long numerator = firstFraction.get(1);
			long denominator = firstFraction.get(2);
			numerator += (mixedNumber * denominator);
			mixedNumber = 0;
			firstFraction.clear();
			firstFraction.add(numerator);
			firstFraction.add(denominator);
		}

		if(secondFraction.size() == 3)
		{
			long mixedNumber = secondFraction.get(0);
			long numerator = secondFraction.get(1);
			long denominator = secondFraction.get(2);
			numerator += (mixedNumber * denominator);
			mixedNumber = 0;
			secondFraction.clear();
			secondFraction.add(numerator);
			secondFraction.add(denominator);
		}

		if(firstFraction.get(1) != secondFraction.get(1))
		{
			long firstFractionNumerator = firstFraction.get(0);
			long secondFractionNumerator = secondFraction.get(0);

			long firstFractionDenominator = firstFraction.get(1);
			long secondFractionDenominator = secondFraction.get(1);

			firstFractionNumerator *= secondFractionDenominator;
			secondFractionNumerator *= firstFractionDenominator;

			firstFractionDenominator *= secondFractionDenominator;
			secondFractionDenominator = firstFractionDenominator;
			return Arrays.asList(addition ? (firstFractionNumerator + secondFractionNumerator) : (firstFractionNumerator - secondFractionNumerator) , firstFractionDenominator);
		}
		else
		{
			long firstFractionNumerator = firstFraction.get(0);
			long secondFractionNumerator = secondFraction.get(0);
			long commonDenominator = firstFraction.get(1);
			return Arrays.asList(addition ? (firstFractionNumerator + secondFractionNumerator) : (firstFractionNumerator - secondFractionNumerator) , commonDenominator);
		}
	}
}
