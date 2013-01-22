@echo off


cd C:\work\bin\apache-tomcat-6.0.16\bin\
call shutdown.bat

cd..
cd webapps
del ROOT /F /Q


cd C:\work\med\proj\java\draft-struts
call mvn clean compile install

move target\draft-struts.war C:\work\bin\apache-tomcat-6.0.16\webapps\ROOT.war

cd C:\work\bin\apache-tomcat-6.0.16\bin\
call C:\work\bin\apache-tomcat-6.0.16\bin\startup.bat

cd C:\work\med\proj\java\draft-struts

pause