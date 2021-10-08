#!/bin/bash
cd src/
javac -d ../out/production/assignment1/ com/company/Main.java
cd ../out/production/assignment1/
java com.company.Main
cd ../../..