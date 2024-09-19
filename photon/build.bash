#!/bin/bash
echo Building PHOTON Project

javac Game.java View.java Controller.java Model.java Player.java SplashScreen.java
javac -d . Game.java View.java Controller.java Model.java Player.java SplashScreen.java
java photon/Game

echo removing generated files

rm Game.class View.class Controller.class Model.class Player.class SplashScreen.class
rm photon/Game.class photon/View.class photon/Controller.class photon/Model.class photon/Player.class photon/SplashScreen.class
rmdir photon