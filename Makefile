SUBDIR=photon/
JFILES=Controller.java Game.java Model.java postgreSQL.java Player.java SplashScreen.java View.java UDP_Client.java UDP_Server.java PlayerAction.java

SRC_FILES=$(addprefix ${SUBDIR}, ${JFILES})

all: main run

main: ${SRC_FILES}
	javac $^

run:
	java -classpath $(CURDIR)/lib/postgresql-42.7.4.jar:$(CURDIR)/lib/jl1.0.1.jar:. photon/Game 

	
	
clean: 
ifeq ($(OS),Windows_NT)
#		have not verified this works
	del photon/*.class
else
	rm photon/*.class
endif
