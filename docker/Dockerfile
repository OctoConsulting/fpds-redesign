FROM ubuntu:trusty
MAINTAINER Dan Montgomery <dan.montgomery@octoconsulting.com>

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
     default-jre \
     default-jdk \
     maven \
  && rm -rf /var/lib/apt/lists/*;

# Install the latest NVM
RUN curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.25.4/install.sh | bash && \
    sudo /bin/bash -c "echo \"[[ -s \$HOME/.nvm/nvm.sh ]] && . \$HOME/.nvm/nvm.sh\" >> /etc/profile.d/npm.sh" && \
    echo "[[ -s $HOME/.nvm/nvm.sh ]] && . $HOME/.nvm/nvm.sh" >> $HOME/.bashrc

RUN npm install -g gulp bower browserify 

# Add id_rsa for github
RUN mkdir -p /root/.ssh
ADD ./id_rsa /root/.ssh/id_rsa
RUN chmod 600 /root/.ssh/id_rsa
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts

RUN git clone git@github.com:OctoConsulting/fpds-redesign.git

