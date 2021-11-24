#!/bin/sh

# This will have this script check for a new version of JMusicBot every
# startup (and download it if the latest version isn't currently downloaded)
DOWNLOAD=true

# This will cause the script to run in a loop so that the bot auto-restarts
# when you use the shutdown command
LOOP=true

download() {
    if $DOWNLOAD
    then
      git pull -q
      mvn -q install
    fi
}

run() {
    java -Dnogui=true -jar $(ls -t target/*All.jar | head -1)
}

while
    download
    run
    $LOOP
do
    continue
done 
