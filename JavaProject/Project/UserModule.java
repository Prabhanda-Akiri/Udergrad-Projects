package Project;
import java.util.Scanner;


interface User
{
	public boolean CreateAccount();
	public boolean LoginAccount();

	public double BookTickets();
	public void ChangePassword();

	public double CancelTickets();
	public void FlightDetail();
	public void LogoutAccount();
}

abstract class UserAbstract implements User
{
	String FirstName,LastName;
	String PhoneNo;
	String email;
	String UserName="student",Password="student";
	boolean BookingStatus;
	int Passengers=0;

	Scanner s=new Scanner(System.in);
	Scanner in=new Scanner(System.in);
		
	public boolean CreateAccount()
	{
		int n;
		System.out.println("PLEASE ENTER YOUR VALID DETAILS TO CREATE AN ACCOUNT .......\n\n");
		System.out.print("FIRST NAME: ");
		FirstName=s.next();

		System.out.print("LAST NAME: ");
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
			System.out.print("EMAIL ID : ");
			email=s.next();
			System.out.println("\n");

			System.out.print("PLEASE ENTER A VALID USERNAME: ");
			UserName=s.next();
			System.out.println("\n");

			System.out.print("PLEASE ENTER A 8 DIGIT PASSWORD:");
			Password=s.next();
			System.out.println("\n");

			System.out.println("CONGRAGULATIONS!!!!.......YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED\n\n");
			System.out.print("Do u want to continue?\n\nPlease enter (1 for YES/2 for NO):");
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
			System.out.print("\nENTER THE USERNAME: ");
			name=s.next();
			System.out.println("\n");
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

	public void ChangePassword()
	{

		String Passwd;
     	String Passwd2;
     	int t1=2;
     	int t2=3;
     
    	System.out.println("\nYour password must contain minimum 8 characters");
    	System.out.println("\nAtleast one character must be numeric");
    	System.out.println("\nEnter your new password:");
     
     	while(t1>1)
     	{
  			Passwd=in.nextLine();
  
  			int length=Passwd.length();
  
  			if(length>=8)
  			{
  
     			System.out.println("\nRe-enter your password:");
      			Passwd2=in.nextLine();
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

public class UserModule extends UserAbstract

{

	public double BookTickets()
	{
		Flights flight=new Flights();

		System.out.println("\nThese are the Flights Offered by the SPICE JET\n\nDetails are also provided\n");
		flight.show();

		System.out.println("\nChoose a Flight(S.No):");
		int no=in.nextInt();
		no=no-1;

		System.out.print("\nEnter the number of passengers:	");
		int p=in.nextInt();

		BookingStatus=true;
		flight.transaction=p*flight.Fls[no].Fare+flight.transaction;
		Passengers=Passengers+p;

		System.out.println("Here is your Ticket:	\n");

		System.out.print("	-----------------------------------------------------------------------------\n\n");
		System.out.print("		*******************************************************************\n\n");
		System.out.println("		______________----SPICE JET DOMESTIC AIRWAYS----___________\n");
		System.out.println("		Flight Number:	"+flight.Fls[no].FlightNumber+"\n\n");
		System.out.println("		Boarding Point:		"+flight.Fls[no].Boarding);
		System.out.println("		Destination Point:	"+flight.Fls[no].Destination+"\n\n");

		System.out.println("		Departure Time:		"+flight.Fls[no].DepTime);
		System.out.println("		Arrival Time:		"+flight.Fls[no].ArrTime+"\n\n");
		System.out.println("		Total Fare:			Rs."+p*flight.Fls[no].Fare+"/- only\n\n");
		System.out.println("				-----HAVE A HAPPY JOURNEY-----\n\n");
		System.out.println("		*******************************************************************\n\n");
		System.out.println("	------------------------------------------------------------------------------\n\n");


		return flight.transaction;
	}

	public double CancelTickets()
	{
		String dec;
    	int p;
    	int num;
    	double amount;
        
  		System.out.println("\nDo you want to cancel the ticket for sure?\n");
  		System.out.println("\nEnter \"yes\" to cancel the tickets or \"no\" to return ");
  
  		dec=in.next();

  		if(dec.equals("yes"))
  		{
      
      		System.out.println("\nDetails of flights:\n\n");
        
            Flights obj=new Flights();
            obj.show();
        
            System.out.print("\nSelect the serial number of flight for which tickets are to be cancelled: ");
            num=in.nextInt();
            num=num-1;
            
      		System.out.println("\n\nYour ticket has  "+Passengers+" passengers");
      		System.out.print("\n\nEnter the no.of passengers tickets to be cancelled:");
      
      		p=in.nextInt();

      		if(p==Passengers)
      		{
      			BookingStatus=false;
      		}
      		amount=obj.Fls[num].Fare*p;
      		obj.transaction=obj.transaction-amount;
      
      		System.out.println("\nYour tickets are cancelled successfully ");
      		System.out.println("\nRupees."+amount+"/- will be refunded into your account\n");

      		return amount;
  		}

  		else
  		{
      		System.out.println("\nYour tickets are not cancelled\n");

      		return 0.00;
  		}
	}

	public void FlightDetail()
	{
		Flights ob=new Flights();

		System.out.println("\nHere are the Details of the flights provided\n");

		ob.show();
	}

	public void LogoutAccount()
	{
		System.out.println("You have been successfully logged out");
	}

}

class Flight
{
	
	public String Boarding,Destination;
	public double DepTime,ArrTime;
	public double Fare;
	public int FlightNumber;

	public Flight(String b,String d,int fno,double dt,double at,double fare)
	{
		Boarding=b;
		Destination=d;
		FlightNumber=fno;
		DepTime=dt;
		ArrTime=at;
		Fare=fare;

	}

}

class Flights
{	
	public int number=5;
	public double transaction=0;
	int i;

	public Flight Fls[]=new  Flight[20];

	Scanner in=new Scanner(System.in);

	Flights()
	{
		Menu();
	}

	public void Menu()
	{
		
		Fls[0]=new  Flight("	Mumbai","	Delhi		",73521,3.45,5.28,6030.70);
		Fls[1]=new  Flight("	Bangalore","Chennai		",56372,8.37,10.05,5372.50);
		Fls[2]=new  Flight("	Hyderabad","Vizag		",23094,2.42,4.57,3956.40);
		Fls[3]=new  Flight("	Kolkata","	Hyderabad	",56321,14.51,16.26,5986.10);
		Fls[4]=new  Flight("	Kashmir","	Delhi		",83562,17.48,18.35,3564.70);
	}

	public void show()
	{
		System.out.println("S.No	Flight.No	Boarding-Point	Destination-Point	Dept-Time	Arr-Time	Fare\n\n");
		for(i=0;i<number;i++)
		{
			System.out.print(i+1+".	"+Fls[i].FlightNumber+"	"+Fls[i].Boarding+"	"+Fls[i].Destination+"	"+Fls[i].DepTime);
			System.out.println("		"+Fls[i].ArrTime+"		"+Fls[i].Fare);
		}
	}

}



