#!/bin/bash
cd "$(dirname "$0")" || exit
cd src/ || exit
javac -d ../build/ com/company/Backpack.java
cd ../build/ || exit
java com.company.Backpack
cd ..
rm -rf build
