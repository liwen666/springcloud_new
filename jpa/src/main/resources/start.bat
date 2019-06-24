
for /f "delims=" %%A in ('dir /b *.jar') do set "filename=%%A"
title %filename%
java -jar -Djava.ext.dirs=lib %filename% --debug=false --portal.devMode=false