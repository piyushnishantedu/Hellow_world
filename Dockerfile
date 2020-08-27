FROM tomcat:alpine
MAINTAINER piyush nishant
CMD curl -O /usr/local/tomcat/webapps/demosampleapplication.war http://localhost:8090/artifactory/piyush-nishant-jenkins-integration-snapshot/com/nagarro/devops-tools/devops/demosampleapplication/1.0.0-SNAPSHOT/demosampleapplication-1.0.0-20200124.222246-1.war
EXPOSE 8000
CMD /usr/local/tomcat/bin/catalina.sh run