docker build -t connect-us-service .
docker stop connect-us-service || true && docker rm connect-us-service || true
docker run -d -p 8190:8190 -p 58190:58190 --restart=always -e SPRING_PROFILES_ACTIVE="development" -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:58190" --name connect-us-service connect-us-service