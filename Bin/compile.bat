@echo off

if "%1"=="" (echo "Must provide a file.")

javac -classpath ../JavaInfrastructure/bin/ -d ../JavaInfrastructure/bin/ "%1.java"
