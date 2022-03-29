FROM adoptopenjdk:11-jre-hotspot
WORKDIR /
COPY activity_data_2021_01_01.txt activity_data_2021_01_01.txt
ADD /build/libs/user-retention-app-1.0.0.RELEASE.jar user-retention-app-1.0.0.RELEASE.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-retention-app-1.0.0.RELEASE.jar"]