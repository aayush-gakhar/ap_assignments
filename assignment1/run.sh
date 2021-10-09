#!/bin/bash
cd "$(dirname "$0")"
cd src/
javac -d ../build/ com/company/Main.java
cd ../build/
java com.company.Main
cd ..
rm -rf build
