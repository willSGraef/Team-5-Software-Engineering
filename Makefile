

SUBDIR=photon/
JFILES=Controller.java EntryField.java Game.java Model.java Player.java postgreSQL.java SplashScreen.java View.java UDP_Client.java UDP_Server.java

SRC_FILES=$(addprefix ${SUBDIR}, ${JFILES})

all: main run

main: ${SRC_FILES}
	javac $^

run:
	java photon/Game
	@echo "---> to clean use: make clean"

clean: 
	ifeq ($(OS),Windows_NT)
#		have not verified this works
		del photon/*.class
	else
		rm photon/*.class
	endif