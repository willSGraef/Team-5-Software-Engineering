

SUBDIR=photon/
JFILES=Controller.java EntryField.java Game.java Model.java Player.java postgreSQL.java SplashScreen.java View.java

SRC_FILES=$(addprefix ${SUBDIR}, ${JFILES})

all: main run

main: ${SRC_FILES}
	javac $^

run:
	java photon/Game
	@echo "---> to clean use: make clean"

clean: 
	rm photon/*.class