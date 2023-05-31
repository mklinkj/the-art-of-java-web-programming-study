@ECHO copy gradle.properties, settings.properties, gradle wrapper to sub chapers.
@ECHO OFF

for /F "delims=" %%a in (project-folder-list.txt) do (
  copy gradle.properties %%a
  copy .gitattributes %%a
  copy %%a\local-settings.gradle %%a\settings.gradle

  xcopy /S /Y gradle\wrapper\ %%a\gradle\wrapper\
  copy gradlew.bat %%a
  copy gradlew %%a
)

@ECHO copy is complete.
PAUSE
