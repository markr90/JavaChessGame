#!/bin/bash
cd ./src
javac ./Game/Main.java -d ../out
cd ..
cp -r ./src/Resources ./out/Resources
echo "Build done"
