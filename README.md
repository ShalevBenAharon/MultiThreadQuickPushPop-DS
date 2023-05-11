# Quick Push & Pop PQ

<h2> Description : </h2>

This Project is Priority Queue data structure with the ability to push elements into the queue and pop the highest priority element from the queue.<br>
The class is implemented using a doubly linked list and a custom comparator to determine element priority.

<h2> Usage </h2>
<p>
To use these implementations of Priority Queue, you will need to provide a comparator to determine the order of objects in your queue.<br>
<b>**Please note </b> that there are two different implementations for the PQ with different time complexities for push and pop.
</p>

#Create :
```java
 QuickPopDataStructure<Integer> PQ = new QuickPopDataStructure(new TestIntComperator());
```
#Push :
```java
 PQ.push(3);
 PQ.push(6);
 PQ.push(7);
 ```
 
 #Pop :
 ```java
 PQ.pop(3);
 PQ.pop(6);
 PQ.pop(7);
 ```
 
 <h2> Time Complexity </h2>
 <p>
 <b>Quick POP data structure:</b> <br>
 Push O(n)<br>
 Pop O1)
 </p>
 <p>
<b>Quick Push data structure: </b><br>
Pop O(n) <br>
Push O(1) <br>
</p>

<h2> Thread safety </h2>
<p>
To ensure thread safety in a multi-threaded environment,i used synchronization block on Object to make sure that only one thread <br>will perform at a time
and to prevent race conditions at the Pop method when accessing the head of the queue.<br>
Additionaly a Semphore is used to to control the value of the queue and to block thread from trying to Pop when the queue is empty.
</p>

<h2> Iterator </h2>
<p>
I have also provided an Iterator implementation for the sake of users who wish to traverse over the elements in the queue.<br>
While it is not necessary for the Priority Queue data structure itself,<br>
it provides better encapsulation in the test files and may be useful for some users.
</p>

