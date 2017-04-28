#Script for testing the different GCs

java -jar target/homework4-1.0-SNAPSHOT.jar 

#java -XX:+UseSerialGC -jar target/homework4-1.0-SNAPSHOT.jar 

#java -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -jar target/homework4-1.0-SNAPSHOT.jar 

#java -XX:+UseG1GC -jar target/homework4-1.0-SNAPSHOT.jar 