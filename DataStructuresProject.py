class Node:
	
	def __init__(self):			#attributes of tree nodes

		self.parent=None
		self.left=None
		self.right=None
		self.start=-1
		self.end=-1
		self.val=0


class SegmentTree:

	def __init__(self,n):

		self.n=n-1			#pointer to keep the trace of total elements
		self.root=None		

	def design(self,s,e):

		
		x=Node()
		x.start=s
		x.end=e

		if e==self.n and s==0:

			self.root=x									#assigning the root if the tree is empty

		if s==e:
			return x

		else:

			m=int((s+e)/2)

			p=self.design(s,m)
			q=self.design(m+1,e)

			p.parent=x
			x.left=p

			q.parent=x
			x.right=q

		return x

	def sear(self,x,i,j):

		if x==None:
			return

		if x.start==i and x.end==j:
			return x

		else:		
									#recursive search
			s=x.start
			e=x.end

			m=int((s+e)/2)

			if i<=m and j<=m:

				p=self.sear(x.left,i,j)

				if p!=None:
					return p

			elif i>m and j>m:

				q=self.sear(x.right,i,j)

				if q!=None:
					return q
				

	def insert(self,a):

		n=len(a)

		for i in range(0,n):

			t=self.sear(self.root,i,i)			#searching the right leaf to insert the array element
			t.val=a[i]

	def summing(self,x):					

		if x==None:

			return 0

		else:

			p=self.summing(x.left)
			q=self.summing(x.right)

			x.val=x.val+p+q

			return x.val

	def traversal(self,x):			
										#enables us to verify if the values are managed properly in the tree
		if x==None:
			return

		else:

			print(x.val)

			self.traversal(x.left)
			self.traversal(x.right)

	def intraversal(self,x):

		if x==None:
			return
		else:
			self.intraversal(x.left)

			print(x.val)

			self.intraversal(x.right)

	def rangesum(self,x,s,e):
										#gives the sum of prescribed range in the array
		if s==x.start and e==x.end:

			return x.val

		elif (x.start<s and e<=x.end) or (s>=x.start and e<x.end) :

			p=0
			q=0

			if x.left!=None:

				if x.left.end>=s:

					if e<x.left.end:
						q=self.rangesum(x.left,s,e)

					else:
						q=self.rangesum(x.left,s,x.left.end)

			if x.right!=None:

				if x.right.start<=e:

					if s>x.right.start:
						p=self.rangesum(x.right,s,e)

					else:
						p=self.rangesum(x.right,x.right.start,e)
			
			return p+q

		elif x.start<s<x.end or x.start<e<x.end:

			p=0
			q=0

			if x.left!=None:
				if x.left.end>=s:
					q=self.rangesum(x.left,s,x.left.end)

			if x.right!=None:
				if x.right.start<=e:
					p=self.rangesum(x.right,x.right.start,e)
			
			return p+q

		else:

			return 0

	def update(self,i,n):
									#updates the array element from initial value to the new value n

		t=self.sear(self.root,i,i)

		k=n-t.val

		self.add(self.root,t.start,k)

	def add(self,x,s,k):						
									#adds the extra value to the elements in the tree when it's child value is updated

		if x.start<=s<=x.end:

			x.val=x.val+k

			if x.left!=None:
				self.add(x.left,s,k)

			if x.right!=None:	
				self.add(x.right,s,k)

class TrainSchedules:

	def __init__(self):
							#Constructor to initialise required attributes for the train
		self.T=tr()

		self.Stops=[]
		self.times=[]
		self.distances=[]

		self.name=None
		self.no=None

	def build(self):
						#builds two segment trees for each attribute time and distance

		self.T.time=SegmentTree(len(self.times))

		self.T.distance=SegmentTree(len(self.Stops))

		self.T.time.design(0,len(self.times)-1)
		self.T.time.insert(self.times)
		self.T.time.summing(self.T.time.root)

		self.T.distance.design(0,len(self.Stops)-1)
		self.T.distance.insert(self.distances)
		self.T.distance.summing(self.T.distance.root)


	def intro(self):

									#Gives introduction about the train selected

		print('\nTrain Name:		',self.name,'\n')
		
		print('Train Number:		',self.no,'\n')
		print('Starting Station:	',self.Stops[0])
		print('Final Station:		',self.Stops[-1],'\n')

		print('Route List:\n')

		n=len(self.Stops)

		for i in range(0,n):
			print(i+1,'.',self.Stops[i])

		print('\n')

	def Time(self,i,j):
															#Calculates time between respective stops selected by the user

		s=self.T.time.rangesum(self.T.time.root,i,j-1)
		return s

	def Distance(self,i,j):
															#Calculates distance between respective stops selected by the user
		s=self.T.distance.rangesum(self.T.distance.root,i,j-1)
		return s


	def upTime(self,n,k):
															#Updates the element n to k(Update time)
		self.T.time.update(n,k)


	def upDistance(self,n,k):
															#Updates the element n to k(Update distance)
		self.T.distance.update(n,k)


	
class tr:

	def __init__(self):

							#creates two attributes to the Train Schedules objects...
		self.time=None
		self.distance=None


def main():

	n=4

	names=['Mangalore Central Malabar Express','Poorna Express		','Mangalore Spcl.		','Mysore Basava Express	']
	nums=['16630','11097','01095','17307']

	Train=[]

	for i in range(0,n):

											#creates TrainSchedule objects for all the trains
		Train.append(TrainSchedules())
		Train[i].name=names[i]
		Train[i].no=nums[i]
	
	Train[0].Stops=['Mangalore','Ullal','Manjeshwar','Uppala','Kumbala','Kasaragod','Koticulum','Bekal-Fort','Kanhangad','Nileshwar']
	Train[0].times=[20,8,7,14,11,12,7,9,13]
	Train[0].distances=[9,8,7,10,12,10,5,8,10]
	Train[0].build()


	Train[1].Stops=['Pune Junction','Karad','Miraj Junction','Belgam','Castle Rock','Madgon','Gokarna Road','Murudeshwar','Udipi','Mangalore Juncton']
	Train[1].times=[180,120,140,150,185,93,58,54,107]
	Train[1].distances=[204,76,137,75,93,133,82,135,81]
	Train[1].build()

	Train[2].Stops=['Mumbai jn.','Thane','Panvel','Ratnagiri','Kankavali','Madagoan','Karwar','Kumta','Byndoor','Udipi']
	Train[2].times=[30,35,270,80,120,55,45,58,64]
	Train[2].distances=[34,34,362,156,179,82,77,98,92]
	Train[2].build()

	Train[3].Stops=['Mysore Jn.','Pandavapura','Mandya','Maddur','Channapatna','Ramanagaram','Bidadi','Kengeri','Bangalore Cy Jn.','Yeswantpur Jn.']
	Train[3].times=[20,20,20,20,15,15,25,75,40]
	Train[3].distances=[20,26,19,18,11,15,18,12,5]		
	Train[3].build()


	print('\n\n*********** Welcome to South Western Railway Zone ************\n')

	while 1:

		k=int(input('\nChoose Your Module:\n\n1.Passenger Module\n2.Administrative Module\n3.Exit\n\n'))

		if k==1:

						#passenger module
			print('\n\nWelcome to the customer..\n\nNow you are accessed to choose boarding point and destination point, then check journey time and journey distance multiple times\nFinally book your ticket..!!\n')

			print('Trains provided :\n\n    Train name          			Train Number\n')

			for i in range(0,n):
				print(i+1,'.',names[i],'		',nums[i])

			while 1:

				c=int(input('\nSelect Your Train\n'))-1

				if c<0 or c>n-1:
					print('Enter a proper choice:')

				else:
					break

			Train[c].intro()


			while 1:


				s=int(input('\nEnter the boarding station.no:		'))
				e=int(input('Enter the destination station.no:	'))

				h=-1

				t=Train[c].Time(s-1,e-1)
				d=Train[c].Distance(s-1,e-1)


				m=t%60
				h=int(t/60)
				o=h
				h=h%24
				da=int(o/24)

				if da>0:

					if h>0:
						print('\nThe Train takes ',da,'Days ',h,' Hours ',m,' Minutes to travel ',d,'kms\n')
					else:
						print('\nThe Train takes ',da,'Days ',m,' Minutes to travel ',d,'kms\n')

				else:
					if h>0:
						print('\nThe Train takes ',h,' Hours ',m,' Minutes to travel ',d,'kms\n')
					else:
						print('\nThe Train takes ',m,' Minutes to travel ',d,'kms\n')

				p=0

				p=int(input('\nDo you want your ticket to be displayed and exit the Passenger Module?\n\n1.Yes\n2.No\n\n'))

				if p==1:

					f=int(input('\nEnter number of Passengers:	'))

					r=(1.5*d)*f 							#bill calculation

					print('\n\n 	_________________________________________________')
					print('\n\n	*******************************************	\n')

					print('	Starting point:	',Train[c].Stops[s-1],'					')
					print('	Ending point:	',Train[c].Stops[e-1],'					\n\n')

					if h>0:
						print('	Journey Time:	',h,' Hours ',m,' Minutes \n\n')
					else:
						print('	Journey Time:	',m,' Minutes\n\n')

					print('	Journey distance:	',d,' kms\n\n')

					if s+1==e:
						print('You don\'t have any intermediate stations\n')

					else:
						print('	Your intermediate stations:				\n')

						for i in range(s,e-1):
							print('	',Train[c].Stops[i],'				')

					print('\n	Bill:			Rs.',r,'	')

					print('\n	**********************************	\n\n')
					print('	Thank You for using the South Western Railways\n\n        ***** Have a Happy Journey *****\n')
					print('\n 	_________________________________________________\n')

					break

		elif k==2:
												#administrative module..

			while 1:

				p=int(input('\nChoose your operation:\n1.Update delay timings at a station\n2.Change a stop\n3.Add a new Train\n\n'))

				if p==1:

					print('Trains provided :\n\n    Train name          			Train Number\n')

					for i in range(0,n):
						print(i+1,'.',names[i],'		',nums[i])

					while 1:

						c=int(input('\nSelect Your Train\n'))-1

						if c<0 or c>n-1:
							print('Enter a proper choice:')

						else:
							break

					Train[c].intro()

					s=int(input('\nenter the station number at which delay occured:		'))
					u=int(input('enter the new travel time to reach the next station(in mins):	'))

					Train[c].upTime(s-1,u)

					t=int(input('\nYour changes are updated..!!!\n\nDo you still want to continue in the Administrative Module and update some changes?:\n\n1.Yes\n2.No\n'))

					if t==2:

						print('\n***Railways offer sincere regards for your service***\n\n        Have a nice Day...!!!!\n\n')

						break


				elif p==2:

					print('Trains provided :\n\n    Train name          			Train Number\n')

					for i in range(0,n):
						print(i+1,'.',names[i],'		',nums[i])

					while 1:

						c=int(input('\nSelect Your Train\n'))-1

						if c<0 or c>n-1:
							print('Enter a proper choice:')

						else:
							break

					Train[c].intro()

					s=int(input('\nenter the station number:	'))

					na=input('enter new station name:		')

					di=int(input('\nenter distance to be travelled to reach the new station(in kms):		'))
					de=int(input('enter distance to be travelled from new station to next station(in kms):	'))

					u=int(input('\nenter time to be travelled to reach the new station (in mins):		'))
					v=int(input('enter time to be travelled from new station to next station(in mins):	'))

					Train[c].Stops[s-1]=na

					Train[c].upDistance(s-2,di)
					Train[c].upDistance(s-1,de)

					Train[c].upTime(s-2,u)
					Train[c].upTime(s-1,v)

					t=-1
					print('\nYour changes are updated..!!!\n\nDo you still want to continue in the Administrative Module and update some changes?:\n\n1.Yes\n2.No\n')

					while 1:

						t=int(input('\nEnter your Choice\n'))

						if t<1 or t>2:
							print('\nInvalid choice..!!')

						else:
							break

					if t==2:

						print('\n***Railways offer sincere regards for your service***\n\n        Have a nice Day...!!!!\n\n')

						break

				elif p==3:

					Name=input('\n\nEnter the Train Name:		')
					Number=input('\n\nEnter the Train Number:	')

					n=n+1
					Name=Name+'			'
					names.append(Name)
					nums.append(Number)

					Train.append(TrainSchedules())
					Train[n-1].name=names[n-1]
					Train[n-1].no=nums[n-1]

					StopNo=int(input('\n\nEnter the total number of Stops for the Train:	'))
					print('\n\nNow start entering the Stop names one by one according to the route:	\n')

					for i in range(StopNo):

						print('\nStop',i+1,':	',end=' ')
						Train[n-1].Stops.append(input())


					print('\nEnter the Time taken to travel between consecutive stations:	\n')

					for i in range(StopNo-1):

						print('\nFrom',Train[n-1].Stops[i],'To',Train[n-1].Stops[i+1],'(in mins):	',end=' ')
						Train[n-1].times.append(int(input()))


					print('\nEnter the Distance to travel between consecutive stations:	\n')

					for i in range(StopNo-1):

						print('\nFrom',Train[n-1].Stops[i],'To',Train[n-1].Stops[i+1],'(in kms):	',end=' ')
						Train[n-1].distances.append(int(input()))

					Train[n-1].build()

					t=-1
					print('\nYour changes are updated..!!!\n\nDo you still want to continue in the Administrative Module and update some changes?:\n\n1.Yes\n2.No\n')

					while 1:

						t=int(input('\nEnter your Choice\n'))

						if t<1 or t>2:
							print('\nInvalid choice..!!')

						else:
							break

					if t==2:

						print('\n***Railways offer sincere regards for your service***\n\n        Have a nice Day...!!!!\n\n')

						break

				else:

					print('\nSelect a proper case...\n')

		elif k==3:
							#exit case

			print('		South Western Railways - Always at people\'s service....!!!!')
			break

		else:
					#invalid case

			print('Select a proper case....')



if __name__ == '__main__':
	main()
