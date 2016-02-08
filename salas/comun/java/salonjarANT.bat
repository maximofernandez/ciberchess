
del /Q c:\temp\*.*

javac -d c:/temp/ Salon.java

pause

jar -cvf L:\wamp\www\ciberchess\salas\comun\java\tablero.jar -C c:/temp . processing/core/*.class fractal/*.class fractal/*.gif imagenes/*

del /Q c:\temp\*.*