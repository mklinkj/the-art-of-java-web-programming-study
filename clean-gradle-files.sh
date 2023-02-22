#!/bin/sh
echo "clean gradle.properties, settings.properties, gradle wrapper to sub chapers."

PROJECT_FOLDER_LIST=$(sed 's/\\/\//g' "project-folder-list.txt")

for folder in $PROJECT_FOLDER_LIST;
do
  rm $folder/gradle.properties
  rm $folder/settings.gradle

  rm -r $folder/gradle
  rm $folder/gradlew
  rm $folder/gradlew.bat
  
  rm $folder/.gitattributes
done

echo "remove is complete."
