@ECHO remove gradle.properties, settings.properties, gradle wrapper to sub chapers.
@ECHO OFF

for /F "delims=" %%a in (project-folder-list.txt) do (
  del %%a\gradle.properties
  del %%a\settings.gradle

  rmdir /S /Q %%a\gradle\
  del %%a\gradlew.bat
  del %%a\gradlew

  del %%a\.gitattributes
)

@ECHO remove is complete.
PAUSE
