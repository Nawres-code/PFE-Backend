FROM tomcat:8.5.64

# Set environment variables
ENV NEXUS_USER=admin
ENV NEXUS_PASS=Rafik.123
ENV NEXUS_URL=http://10.10.10.1:8081
ENV NEXUS_REPO=maven-local-repo
# Run commands to download and extract files

RUN wget --user=$NEXUS_USER --password=$NEXUS_PASS --auth-no-challenge "$NEXUS_URL/repository/$NEXUS_REPO/target.tar.gz"

RUN tar -xvzf target.tar.gz
RUN rm target.tar.gz

# Copy the WAR file from the extracted directory to Tomcat webapps

RUN cp target/*.war /usr/local/tomcat/webapps/

# Start Tomcat
CMD ["catalina.sh", "run"]
