@echo off

if "%1"=="" (echo "Must provide a file.")

java -classpath ../JavaInfrastructure/bin/ "%1"
