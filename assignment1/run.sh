#!/bin/bash
cd "$(dirname "$0")"
cd src/
javac -d ../build/ com/company/Portal.java
cd ../build/
java com.company.Portal
cd ..
rm -rf build
