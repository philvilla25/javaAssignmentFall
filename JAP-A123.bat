:: ---------------------------------------------------------------------
:: JAP COURSE - SCRIPT
::
:: Name:Akpoguma Oghenerukevwe and Philogene Villanueva
:: Student Number: 041075624 and 041063813
:: CST8221 A12
:: Date: 2nd October, 2023.
::
:: SCRIPT CST8221 - Fall 2023
:: ---------------------------------------------------------------------
:: Begin of Script (A13 - F23)
:: ---------------------------------------------------------------------

CLS

:: LOCAL VARIABLES ....................................................

::SET LIBDIR=openjfx-20.0.2_windows-x64_bin-sdk/javafx-sdk-20.0.2/lib
SET SRCDIR=src
SET BINDIR=bin
SET BINERR=jap-javac.err
SET JARNAME=Java_Assignment.jar
SET JAROUT=Java_Assignment.out
SET JARERR=Java_Assignment.err
SET DOCDIR=src/doc
SET DOCPACK=cs
SET PACKAGE=cs
SET DOCERR=jap-javadoc.err
SET MAINCLASSSRC1=src/CSModel.java
SET MAINCLASSSRC2=src/mainGUI.java
SET MAINCLASSSRC3=src/GridClass.java
SET MAINCLASSSRC4=src/Game.java
SET MAINCLASSSRC5=gameOfLife_Model/GameModel.java
SET MAINCLASSSRC6=gameOfLife_COntroller/GameController.java
SET MAINCLASSSRC7=gameOflife_View/GameView.java
SET MAINCLASSBIN=CSModel
SET RESOURCES=resources

@echo off

ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
ECHO "@                                                                   @"
ECHO "@                   #       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@                  ##       @  A L G O N Q U I N  C O L L E G E  @  @"
ECHO "@                ##  #      @    JAVA APPLICATION PROGRAMMING    @  @"
ECHO "@             ###    ##     @          F A L L - 2 0 2 3         @  @"
ECHO "@          ###    ##        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@        ###    ##                                                  @"
ECHO "@        ##    ###                 ###                              @"
ECHO "@         ##    ###                ###                              @"
ECHO "@           ##    ##               ###   #####  ##     ##  #####    @"
ECHO "@         (     (      ((((()      ###       ## ###   ###      ##   @"
ECHO "@     ((((     ((((((((     ()     ###   ######  ###  ##   ######   @"
ECHO "@        ((                ()      ###  ##   ##   ## ##   ##   ##   @"
ECHO "@         ((((((((((( ((()         ###   ######    ###     ######   @"
ECHO "@         ((         ((           ###                               @"
ECHO "@          (((((((((((                                              @"
ECHO "@   (((                      ((        /-----------------------\    @"
ECHO "@    ((((((((((((((((((((() ))         |  COMPUTATIONAL MODEL  |    @"
ECHO "@         ((((((((((((((((()           \-----------------------/    @"
ECHO "@                                                                   @"
ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"

ECHO "[LABS SCRIPT ---------------------]"


ECHO "0. Preconfiguring ................."
:: mkdir "%BINDIR%/%RESOURCES%"
:: xcopy "%SRCDIR%/%RESOURCES%" "%BINDIR%/%RESOURCES%" /Y
::mkdir "%BINDIR%/%IMAGES%"
::xcopy "%SRCDIR%/%IMAGES%" "%BINDIR%/%IMAGES%" /Y

mkdir "%BINDIR%"
mkdir "%BINDIR%\%PACKAGE%"
copy "%RESOURCES%\*.png" "%BINDIR%\%RESOURCES%"
copy "%RESOURCES%\*.gif"  "%BINDIR%\%RESOURCES%"
copy "%RESOURCES%\*.xml"  "%BINDIR%\%RESOURCES%"

ECHO "1. Compiling ......................"
::javac -Xlint -cp ".;src;/SOFT/copy/dev/java/javafx/lib/*;/SOFT/COPY/db/derby/lib/*" src/Lab.java -d bin 2> labs-javac.err
javac -Xlint -cp ".;%SRCDIR%" %MAINCLASSSRC1% %MAINCLASSSRC2% %MAINCLASSSRC3% %MAINCLASSSRC4% %MAINCLASSSRC5% %MAINCLASSSRC6% %MAINCLASSSRC7% -d %BINDIR% 2> %BINERR%


:: ECHO "Running (outside JAR) ........................."
:: start java -cp ".;bin;/SOFT/copy/dev/java/javafx/lib/*" CST8221.Main

ECHO "2. Creating Jar ..................."
cd bin
::jar cvfe CST8221.jar Lab . > labs-jar.out 2> labs-jar.err
jar cvfe %JARNAME% %MAINCLASSBIN% . > ../%JAROUT% 2> ../%JARERR%
::jar cvfe %JARNAME% %MAINCLASSBIN% -C src . -C src/gameOfLife_Controller -C src/gameOfLife_Model -C src/gameOfLife_View > ../%JAROUT% 2> ../%JARERR%


ECHO "3. Creating Javadoc ..............."
cd ..
::javadoc -cp ".;bin;/SOFT/copy/dev/java/javafx/lib/*;/SOFT/COPY/db/derby/lib/*;/SOFT/COPY/dev/LIBS/jar/javax.servlet.jar" --module-path "C:\SOFT\COPY\dev\LIBS\javafx\lib" --add-modules javafx.controls -d doc -sourcepath src -subpackages CST8221 2> labs-javadoc.err
javadoc -cp ".;%BINDIR%;../%LIBDIR%" --module-path "%LIBDIR%" -d %DOCDIR% -sourcepath %SRCDIR% -subpackages %DOCPACK% 2> %DOCERR%


ECHO "4. Running Jar ...................."
cd bin
::start java --module-path "/SOFT/COPY/dev/LIBS/javafx/lib;/SOFT/COPY/db/derby/lib" --add-modules javafx.controls,javafx.fxml -jar CST8221.jar
start java --module-path "../%LIBDIR%" -jar %JARNAME%
cd ..

ECHO "[END OF SCRIPT -------------------]"
ECHO "                                   "
@echo on

:: ---------------------------------------------------------------------
:: End of Script (A13 - F23)
:: ---------------------------------------------------------------------
