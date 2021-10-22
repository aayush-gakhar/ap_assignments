#!/bin/bash
cd "$(dirname "$0")" || exit
cd src/ || exit
javac -d ../build/ com/company/Game.java
cd ../build/ || exit
java com.company.Game
cd ..
rm -rf build
