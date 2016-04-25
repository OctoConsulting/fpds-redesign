FROM ubuntu:trusty
MAINTAINER Dan Montgomery <dan.montgomery@octoconsulting.com>

RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN apt-get install oracle-java8-installer
RUN echo "Setting environment variables for Java 8.."
RUN apt-get install -y oracle-java8-set-default

RUN apt-get update \
  && apt-get install -y --force-yes --no-install-recommends \
     apt-transport-https \
     build-essential \
     ca-certificates \
     lsb-release \
     libssl-dev \
     curl \
     git \
     openssh-client \
     rlwrap \
     maven \
     nodejs \
     npm \
  && apt-get clean 

#Install NPM packages
RUN npm install -g gulp bower browserify 

# Add id_rsa for github
RUN mkdir -p /root/.ssh
COPY ./id_rsa /root/.ssh/id_rsa
RUN chmod 600 /root/.ssh/id_rsa
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts

RUN git clone git@github.com:OctoConsulting/fpds-redesign.git
RUN chmod u+x fpds-redesign/app/fpds/mvnw

RUN java -version
RUN mvn -version

RUN cd fpds-redesign/app/fpds && ./mvnw clean install
RUN cd src/main/resources/static && npm install && bower install && gulp build
