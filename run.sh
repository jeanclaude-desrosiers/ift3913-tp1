#! /bin/bash
#
# Little script to run tp1 on a git project
#
# author : jclaude
# date : 2022/02/10

jar_version="2"

url=https://github.com/jfree/jfreechart.git
source_root=src/main/java/org/

echo "Downloading project from $url..."
git clone "$url" ./temp-project

java -jar "./tp1-$jar_version.jar" "./temp-project/$source_root" -od ./PARTIE4/

rm -rf ./temp-project