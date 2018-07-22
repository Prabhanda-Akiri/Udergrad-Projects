#!/bin/bash

echo "Welcome"

n=4
Trains=("Mangalore Malabar Express" "Poorna Express" "Mangalore Special" "Mysore Basava Express")

while((1))
do
	echo -e "Please select your module to continue\n"
	echo -e "Modules provided:\n\n1.User Module\n2.Admin Module\n3.Help Desk\n4.Exit"

	read module

	if test $module -eq 1

		then

		echo -e "Here are the Trains provided\n\nSelect one train\n"

		for((i=0;i<n;i++))
		do
			no=`expr $i + 1`
			echo "$no.${Trains[$i]}"

		done

		read trainno

		case $trainno in 1 2 3 4 

			1) cat MalabarExpress.txt > SelectedTrain.txt;;
			2) cat PoornaExpress.txt > SelectedTrain.txt;;
			3) cat MangaloreSPL.txt > SelectedTrain.txt;;
			4) cat MysoreBasavaExpress.txt > SelectedTrain.txt;;

		esac

		echo -e "The Route list of your selected train is as below:\n"

		cut -d "	" -f -2 SelectedTrain.txt

		echo -ne "\n\nSelect the boarding point name	:	"
		read bp 

		echo -ne "\nSelect the destination point name :		"
		read dp 

		grep -i "$bp" SelectedTrain.txt > BoardingDetails.txt
		grep -i "$dp" SelectedTrain.txt >> BoardingDetails.txt

		t1=`cut -d "	" -f 3 BoardingDetails.txt | head -n 1`
		t2=`cut -d "	" -f 3 BoardingDetails.txt | tail -n 1`

		t=`expr $t2 - $t1`
		min=`expr $t % 60`
		hrs=`expr $t / 60`

		d1=`cut -d "	" -f 4 BoardingDetails.txt | head -n 1`
		d2=`cut -d "	" -f 4 BoardingDetails.txt | tail -n 1`

		dis=`expr $d2 - $d1`

		if((hrs>0))
			then
			echo -ne "\n\nTime taken for the journey is $hrs Hours and $min Minutes and the journey distance is $dis Kms.\n\n"

		else
			echo -ne "\n\nTime taken for the journey is $min Minutes and the journey distance is $dis Kms.\n\n"

		fi

		echo -ne "Do you want to take a ticket?\n\n1.Yes\n2.No\n\n"

		read opt

		if test $opt -eq 1
			then

			echo -ne "\n\nEnter the number of passengers:	"
			read pas 

			rate=`expr $dis * $pas * 2`

			echo -ne "\n\n-----------------------------------------------------------------------------\n\n"

			echo -ne "\n\n********************************************************\n\n"

			echo -ne "\nBoarding Point:			$bp"
			echo -ne "\n\nDestination Point:	$dp"

			if ((hrs>0))
				then
				echo -ne "\nJourney Time:	$hrs Hours, $min Minutes"

			else 
				echo -ne "\nJourney Time:	$min Minutes"

			fi

			echo -ne "\n\nJourney Distance:	$dis"

			echo -ne "\n\nIntermediate Stations:\n\n"

			sed -ni "/$bp/,/$dp/p" SelectedTrain.txt > Inter.txt			
			cut -d "	" -f 2 Inter.txt

			echo -ne "\n\nYour Fare:		Rs.$rate.00/- Only\n\n"

			echo -e "\n*****************************************************"

			echo -ne "\nThank You for using South Western Railways\n\n\n********Have a Happy Journey********\n\n"
			echo -ne "\n----------------------------------------------------------------------------------------\n\n"

		else

			echo -ne "\n\nThank you for concerning South Western Railways \n\nWe are delighted to provide our services to you\n"
		fi

	elif test $module -eq 2

		then

		while((1))
		do

		echo -ne "\n\nOperations provided :\n\n1.Extending Train Route\n2."

		read opt

		if test $opt -eq 1

			then 

			echo -e "Here are the Trains provided\n\nSelect one train\n"


			for((i=0;i<n;i++))
			do
				no=`expr $i + 1`
				echo "$no.${Trains[$i]}"

			done

			read trainno

			case $trainno in 1 2 3 4 

				1) cat MalabarExpress.txt > SelectedTrain.txt;;
				2) cat PoornaExpress.txt > SelectedTrain.txt;;
				3) cat MangaloreSPL.txt > SelectedTrain.txt;;
				4) cat MysoreBasavaExpress.txt > SelectedTrain.txt;;

			esac

			echo -e "The Route list of your selected train is as below:\n"

			cut -d "	" -f -2 SelectedTrain.txt

			echo -ne "\n\nEnter the next stop to be extended:		"
			read stop

			echo -ne "\n\nEnter the distance to travel from the last stop(in kms): "
			read dis 

			echo -ne "\n\nEnter the time to travel from last stop(in mins):			"
			read Minutes

			n=`wc -l SelectedTrain.txt`
			((n=n+1))

			sed '$a\
			$n.	$stop	$Minutes	$dis
			' SelectedTrain.txt > $$

			case $trainno in 1 2 3 4 

				1) mv $$ MalabarExpress.txt;;
				2) mv $$ PoornaExpress.txt;;
				3) mv $$ MangaloreSPL.txt;;
				4) mv $$ MysoreBasavaExpress.txt;;

			esac

			echo -ne "\nYour changes are updated..!!!\n\nDo you want to continue in The Administrative Module?\n\n1.Yes\n2.No\n\n"

			read opt

			if((opt==2))
				then

				break
			fi

		elif test $opt -eq 2

			then

		else

		fi

	elif test $module -eq 3

		then

	else


		break

	fi

done
