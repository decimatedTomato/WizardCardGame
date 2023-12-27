#!/bin/bash

set -xe
mkdir -p bin
rm -f bin/*.class
javac -cp lib/*.jar src/*.java
mv src/*.class bin/