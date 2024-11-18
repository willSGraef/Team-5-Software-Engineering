# Team-5-Software-Engineering
Software Engineering Project Team 5
Link to the Requirements Excel Sheet: 
https://uark-my.sharepoint.com/:x:/g/personal/azlont_uark_edu/ESHNEskoTz5GlAgqfGQOV_UBdLlBprWY1EpwH4Z0bjkqnQ?e=P6Nb2j
---------------------------------------------------------------------------------------------------------------------
**RUN INSTRUCTIONS**

Open Virtual Machine

Update sudo package index:

```sudo apt update```

Check for java install:

```java -version```

If not installed install jre:

```sudo apt install defautl-jre```

Verify installation:

```java -version```

Check for javac install:

```javac -version```

If not installed install jdk:

```sudo apt install default-jdk```

Verify install

```javac - version```

Next download the main branch source code zip file to any directory

Extract the zip file

In that directory run in the terminal:

```gmake -f Makefile```

----------------------------------------------------------------------------------------------------------------

**CONTROLS**

Navigate Left: F1 Key

Navigate Right: F2 Key

Enter Selected Player: F3 Key

Clear All Players: F12 Key

Start Game: F5 Key

----------------------------------------------------------------------------------------------------------------

**OPERATING**

Use the makefile as mentioned **above** to run the main program


In a seperate Terminal, run the traffic generator

```python3 python_trafficgenerator_v2.py``` or ```python python_trafficgenerator_v2.py```


***Player Entry***

1). Add player ID to the desired field

2). Add player's Codename if the ID is new. If the player is **not new**, leave this field blank

3). Add player's equipment ID

4). Use F3 to add the player. 
**Note:** The Codename field will update if it was left blank **and** the player's ID has a Codename in the database.

Repeat until all players are entered.

When adding a new player, remember to use `F1` and `F2` to select the desired player to enter


**Traffic Generator**

Once all players are entered run the generator program if you haven't yet

Follow the prompts and enter an equipment ID for 2 players on each team

The traffic generator will now operate on its own

**!!!DO NOT!!!** insert the 4 player's equipment ID's before finishing player entry.
Player entry requires use of a UDP socket that the traffic generator binds.
To prevent an error of trying to bind a bound socket, add all players before filling out the traffic generator.


