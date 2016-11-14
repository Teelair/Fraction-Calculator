import java.util.Scanner;

/**
Checking against errors

contains _
contains /
!contains _/
!endsWith /
!endsWith _
!contains /
!startsWith _
!startsWith /
!endsWith /0
!contains .

Prewritten method for further checking against errors
int minusCount = 0;
int slashCount = 0;
int underscoreCount = 0;
for(char ch : input.toCharArray()
{
	slashCount += (ch == '/' ? 1 : 0);
	underscoreCount += (ch == '_' ? 1 : 0);
	minusCount += (ch == '-' ? 1 : 0);

	if(!(ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == '0'))
	{
		System.out.println("Invalid input!");
		break;
	}
}
 */
public class Main 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while(input.equals(""))
		{
			String line = scanner.nextLine();
			boolean[] results = breakFraction(line);
			boolean isNegative = results[0];
			boolean isMixed = results[1];
			boolean isValid = results[2];
			if(isValid)
			{
				isValid = secondaryValidationCheck(line);
			}
			if(isValid)
			{
				String[] lineSplit = line.split("/");
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
				
				System.out.println("Mixed number: " + mixedNum);
				System.out.println("Numerator: " + numerator);
				System.out.println("Denominator: " + denominator);
			}
		}
		scanner.close();
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
}
