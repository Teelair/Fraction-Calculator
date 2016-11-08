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

}
