#!/bin/bash

set -xe
mkdir -p bin
rm -f bin/*.class
javac src/*.java
mv src/*.class bin/