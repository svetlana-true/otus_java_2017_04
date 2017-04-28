#Script for testing the different GCs

java -jar target/homework4-1.0-SNAPSHOT.jar

#java -Xmx512m -Xms512m -XX:+UseSerialGC -jar target/homework4-1.0-SNAPSHOT.jar

#java -Xmx512m -Xms512m -XX:+UseParallelGC -jar target/homework4-1.0-SNAPSHOT.jar

#java -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -jar target/homework4-1.0-SNAPSHOT.jar

#java -Xmx512m -Xms512m -XX:+UseG1GC -jar target/homework4-1.0-SNAPSHOT.jar