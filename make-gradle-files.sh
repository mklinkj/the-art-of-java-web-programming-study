#!/bin/sh
echo "copy gradle.properties, settings.properties, gradle wrapper to sub chapers."

PROJECT_FOLDER_LIST=$(sed 's/\\/\//g' "project-folder-list.txt")

for folder in $PROJECT_FOLDER_LIST;
do
  cp gradle.properties $folder
  cp $folder/local-settings.gradle $folder/settings.gradle

  cp -r gradle $folder/
  cp gradlew $folder
  cp gradlew.bat $folder
done

echo "copy is complete."
