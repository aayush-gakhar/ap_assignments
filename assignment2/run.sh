#!/bin/bash
cd "$(dirname "$0")"
cd src/
javac -d ../build/ com/company/Backpack.java
cd ../build/
java com.company.Backpack
cd ..
rm -rf build
