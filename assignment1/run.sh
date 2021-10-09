#!/bin/bash
cd src/
javac -d ../build/ com/company/Main.java
cd ../build/
java com.company.Main
cd ..
