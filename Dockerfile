From openjdk:8
copy ./target/cloud-cw-coinbase-0.0.1-SNAPSHOT.jar cloud-cw-coinbase-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","cloud-cw-coinbase-0.0.1-SNAPSHOT.jar"]