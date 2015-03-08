This is a program that will immitate a SLot Machine Poker Game: Jacks Or Better.

You run the program. A window opens.
On the top there is a little screen (1) showing all the possible combinations in this game and how much money you get in return for your bet in multiples.

Under this screen it has screen with all the cars the user was delt. 
Under it, are the control elemets:
Buttons 1, 2, 3, 5 that mean how much money your betting. Next to this buttons a little screen that show how much money is left. And a little screen that shows how much money is currently being bet. 

Then comes Deal/Draw button.



After the program starts, all cards are face down. By defult, $1 bet is chosen but user is free to press any other betting button. If he does so, it is equivalent of pressing the Deal button (see below).

If the cards are still face down, and a bet is chosen, it is time for the user to press Deal button. This "turns" the 5 cards face up and the Deal button becomes Draw button.

There is a Card class, that represents a playing card. 
It is one out of 52 other cards. The value of the card is being assigned at the creation.


There is a GameField class



Now 5 cards are seen on the screen. They may or may not have some kind of poker combination. In case if they have a combination, the combination screen (1) is highlating that combination in its list and showing the return value for it.

User now should pick those cards that he wants to remain (0 to 5) by clicking over them. Once clicked, a HOLD lavel appeares above that card. After all desired cards have been picked, user presses the DRAW button and those cards that weren't picked, are being substituted with new cards from the deck. 

Now again, if the new cards contain a poker combination then combination screen (1) is highlating that combination in its list and showing the return value for it. And user is winning the money he had bet times the return value for the given combination. The DRAW button becomed Deal again. 


