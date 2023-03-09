pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
    jdk   'JDK_HOME'
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
            ORGANIZATION = "cristhian-github"
            PROJECT_NAME = "cristhian_jenkins-pipeline-as-code"
          }
        steps {
            withSonarQubeEnv( installationName: 'SonarQubeServer',credentialsId: 'sonarQube-token') {
                   sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.organization=$ORGANIZATION \
                   -Dsonar.java.binaries=build/classes/java/ \
                   -Dsonar.projectKey=$PROJECT_NAME \
                   -Dsonar.sources=.'''
               }
         }
    }

  }
}