#!/bin/bash

set -xe
mkdir -p bin
rm -f bin/*.class
javac -cp lib/*.jar src/*.java
mv src/*.class bin/

rm -f WizardGame.jar
cd bin
jar -cfe ../WizardGame.jar Main -C ../lib/*.jar .
cd ..