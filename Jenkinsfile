pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
    jdk   'JAVA_HOME'
  }
   stages {
      stage ('Build project') {
                steps {
                        sh 'mvn install'
                }
      }
      stage ('Unit Test') {
            steps {
                    sh 'mvn test'
            }
      }

    stage('SonarQube analysis') {
        environment {
            SCANNER_HOME = tool 'SonarQubeScanner'
            ORGANIZATION = "cristhian-github-microservices"
            PROJECT_NAME = "cristhian_jenkins-pipelines"
        }
        steps {
            withSonarQubeEnv( installationName: 'SonarQubeServer',credentialsId: 'sonarQube-token') {
            sh '''$SCANNER_HOME/bin/sonar-scanner \
             -Dproject.settings=sonar-project.properties '''
            }
        }
    }
  }
}