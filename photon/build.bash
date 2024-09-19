#!/bin.bash
echo Building PHOTON Project for MACOS

javac Game.java View.java Controller.java Model.java Player.java
javac -d . Game.java View.java Controller.java Model.java Player.java
java photon/Game

echo Build finished
