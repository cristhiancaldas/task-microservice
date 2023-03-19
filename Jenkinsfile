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
             -Dsonar.projectKey=projectKey \
             -Dsonar.projectName=task-microservices-Jenkins \
             -Dsonar.sources=src/main \
             -Dsonar.exclusions=src/main/java/com/app/taskmicroservice/dto/*,src/main/java/com/app/taskmicroservice/exceptions/* \
             -Dsonar.java.binaries=target/classes '''
            }
        }
    }
  }
}