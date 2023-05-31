#!/bin/sh
echo "copy gradle.properties, settings.properties, gradle wrapper to sub chapers."

PROJECT_FOLDER_LIST=$(sed 's/\\/\//g' "project-folder-list.txt")

for folder in $PROJECT_FOLDER_LIST;
do
  cp gradle.properties $folder
  cp .gitattributes $folder
  cp $folder/local-settings.gradle $folder/settings.gradle
  
  mkdir -p $folder/gradle/wrapper/
  cp -r gradle/wrapper/* $folder/gradle/wrapper
  cp gradlew $folder
  cp gradlew.bat $folder
done

echo "copy is complete."
