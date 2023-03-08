pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME' 
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
          SCANNER_HOME = tool 'SonarQube'
        }
        steps {
        withSonarQubeEnv(credentialsId: 'sonarQube-token') {
             sh '''$SCANNER_HOME/bin/sonar-scanner \
             -Dsonar.projectKey=projectKey \
             -Dsonar.projectName=task-microservices \
             -Dsonar.sources=src/ \
             -Dsonar.java.binaries=target/classes/ \
             -Dsonar.exclusions=src/test/java/****/*.java'''
           }
         }
    }

  }
}