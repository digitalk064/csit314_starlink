#!/bin/bash
cd "$(dirname "$0")"
chmod u+x csit314_starlink.jar
java -jar --add-opens=java.base/java.lang.reflect=ALL-UNNAMED csit314_starlink.jar