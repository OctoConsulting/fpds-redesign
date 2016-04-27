FROM ubuntu:trusty
MAINTAINER Dan Montgomery <dan.montgomery@octoconsulting.com>

RUN apt-get -y -q update && apt-get -y -q install software-properties-common htop
RUN add-apt-repository ppa:webupd8team/java && apt-get -y -q update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get -y -q install oracle-java8-installer \
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
     npm \
  && apt-get clean 

curl -sL https://deb.nodesource.com/setup_5.x | sudo -E bash -sudo apt-get install -y nodejs

RUN update-java-alternatives -s java-8-oracle

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
#RUN cd fpds-redesign/app/fpds/src/main/resources/static && npm install && bower install && gulp build && mvn spring-boot:run &
RUN cd fpds-redesign/app/fpds/src/main/resources/static && npm install && bower install && gulp build
