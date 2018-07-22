
package Project;
import java.util.Scanner;

interface Admin
{
	public boolean CreateAccount();
	public boolean LoginAccount();
	public void LogoutAccount();
	public void ChangePassword();

	public void AddAirline();

	public void ChangeFare();
	public void DailyTransaction();
}

abstract class AdminAccount implements Admin
{
	String FirstName,LastName;
	String PhoneNo;
	String email;
	String UserName="student",Password="student";
	
	Scanner in=new Scanner(System.in);
	Scanner s=new Scanner(System.in);


	public boolean CreateAccount()
	{
		int n;
		System.out.println("PLEASE ENTER YOUR VALID DETAILS TO CREATE AN ACCOUNT .......\n\n");
		System.out.print("FIRST NAME: 		");
		FirstName=s.next();

		System.out.print("LAST NAME: 		");
		LastName=s.next();
		System.out.println("\n");
		try
		{
			System.out.print("PHONE NUMBER: ");
			PhoneNo=s.next();
			System.out.println("\n");
			if (PhoneNo.length()<10)
			{
				throw new ArithmeticException("SHOULD CONTAIN 10 DIGIT NUMBER!!!\nPLEASE ENTER A VALID PHONE NUMBER:");
			}

		}
		catch(ArithmeticException e)
		{
			System.out.print("Error:"+e.getMessage());
			PhoneNo=s.next();
			System.out.println("\n");
		}

		finally
		{
			System.out.print("EMAIL ID : 		");
			email=s.next();
			System.out.println("\n");

			System.out.print("PLEASE ENTER A VALID USERNAME: ");
			UserName=s.next();
			System.out.println("\n");

			System.out.print("PLEASE ENTER A 8 DIGIT PASSWORD:");
			Password=s.next();
			System.out.println("\n");

			System.out.println("CONGRAGULATIONS!!!!.......YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED\n\n");
			System.out.print("Do u want to continue?\n Please enter (1 for YES/2 for NO):");
			n=s.nextInt();

			if(n==1)
				return true;
			else
				return false;

		}
	}

	public boolean LoginAccount()
	{
		int i=0;
		String name,pwd;

		while(i<=2)
		{
			System.out.print("ENTER THE USERNAME: ");
			name=s.next();
			System.out.println("\n\n");
			System.out.print("ENTER THE PASSWORD: ");
			pwd=s.next();
			if(name.equals(UserName) && pwd.equals(Password))
			{
				System.out.println("Successfully logged in!!!\n\n");
				return true;
			}
			else

				System.out.println("INCORRECT USERNAME OR PASSWORD!!!....PLEASE TRY AGAIN!!! "+(int)(2-i)+" CHANCES ARE LEFT ");

			i=i+1;
		}

		System.out.println("SORRY NUMBER OF TRIALS OVER!!!");
		return false;
	}

	public void LogoutAccount()
	{
		System.out.println("You have been successfully logged out");
	}

	public void ChangePassword()
	{
		String Passwd;
     	String Passwd2;
     	int t1=2;
     	int t2=3;
     
    	System.out.println("Your password must contain minimum 8 characters");
    	System.out.println("Atleast one character must be numeric");
    	System.out.println("Enter your new password:");
     
     	while(t1>1)
     	{
  			Passwd=in.next();
  
  			int length=Passwd.length();
  
  			if(length>=8)
  			{
  
     			System.out.println("Re-enter your password:");
      			Passwd2=in.next();
      			while(t2>1)
      			{
          
          			if(Passwd.equals(Passwd2))
          			{
              			System.out.println("Password successfully changed");
              			Password=Passwd;
              			t2=0;
              			t1=0;
          			}
          			else
          			{
              			System.out.println("Re-enter your password");
          			}
      			}
  			}

  			else
  			{
      			System.out.println("Please enter a valid password:");
      
  			}
  
     	}
	}
	
}


abstract class AdminAbstract extends AdminAccount
{
	Flights1 ob=new Flights1();

	public void AddAirline()
	{

		ob.Add();

	}

}

public class AdminModule extends AdminAbstract
{
	public void ChangeFare()
	{
		
		System.out.println("Here are the details of the Flights provided");
		ob.show(ob);

		Scanner in=new Scanner(System.in);
		System.out.println("Choose the Flight to change the Fare..\n");
		int n=in.nextInt();
		n=n-1;

		System.out.println("Enter your new Fare..\n");
		double f=in.nextDouble();

		ob.Fls[n].Fare=f;

		System.out.println("\nYour New fare is updated\n");

		ob.show(ob);
	}

	public void DailyTransaction()
	{

		System.out.println("Todays Daily Transactions are\n"+"						Rs."+ob.transaction+"/- only....");
	}
}


class Flight1
{
	
	public String Boarding,Destination;
	public double DepTime,ArrTime;
	public double Fare;
	public int FlightNumber;

	public Flight1(String b,String d,int fno,double dt,double at,double fare)
	{
		Boarding=b;
		Destination=d;
		FlightNumber=fno;
		DepTime=dt;
		ArrTime=at;
		Fare=fare;

	}

}

class Flights1
{	
	public int number=5;
	public double transaction=0;
	int i;

	public Flight1 Fls[]=new  Flight1[20];

	Scanner in=new Scanner(System.in);

	Flights1()
	{
		Menu();
	}

	public void Menu()
	{
		
		Fls[0]=new  Flight1("	Mumbai","	Delhi		",73521,3.45,5.28,6030.70);
		Fls[1]=new  Flight1("	Bangalore","Chennai		",56372,8.37,10.05,5372.50);
		Fls[2]=new  Flight1("	Hyderabad","Vizag		",23094,2.42,4.57,3956.40);
		Fls[3]=new  Flight1("	Kolkata","	Hyderabad	",56321,14.51,16.26,5986.10);
		Fls[4]=new  Flight1("	Kashmir","	Delhi		",83562,17.48,18.35,3564.70);
	}

	public void show(Flights1 ob)
	{
		System.out.println("S.No	Flight.No	Boarding-Point	Destination-Point	Dept-Time	Arr-Time	Fare\n\n");
		for(i=0;i<ob.number;i++)
		{
			System.out.print(i+1+".	"+Fls[i].FlightNumber+"	"+Fls[i].Boarding+"	"+Fls[i].Destination+"	"+Fls[i].DepTime);
			System.out.println("		"+Fls[i].ArrTime+"		"+Fls[i].Fare);
		}
	}

	public void Add()
	{
		System.out.println("Here are the Flight Details:\n");
		show(this);

		System.out.print("\nEnter the 5-digit flight number:	");
		int fno=in.nextInt();

		System.out.print("\n\nBoarding-point:		");
		String b=in.next();
		b="	"+b;

		System.out.print("\nDestination-point:	");
		String d=in.next();
		d="	"+d+"		";

		System.out.print("\n\nDeparture-time(in 24-hr format):	");
		double dt=in.nextDouble();

		System.out.print("\nArrival-time(in 24-hr format):		");
		double at=in.nextDouble();

		System.out.print("\n\nEnter the Fare(in Rupees):	");
		double fare=in.nextDouble();

		Fls[number]=new  Flight1(b,d,fno,dt,at,fare);
		number=number+1;
		System.out.print("\n\nYour Details are added to the new Airline...!!!\n");

		show(this);

	}


}

