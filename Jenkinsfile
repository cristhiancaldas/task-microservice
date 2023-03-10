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
            ORGANIZATION = "cristhian-github"
            PROJECT_NAME = "cristhian_jenkins-pipeline-as-code"
        }
        steps {
            withSonarQubeEnv( installationName: 'SonarQubeServer',credentialsId: 'sonarQube-token') {
            sh '''$SCANNER_HOME/bin/sonar-scanner \
             -Dsonar.projectKey=projectKey \
             -Dsonar.projectName=task-microservices-Jenkins \
             -Dsonar.sources=src/main \
             -Dsonar.java.binaries=target/classes '''
            }
        }
    }

     stage("Quality Gate"){
              timeout(time: 1, unit: 'MINUTES') {
                  def qg = waitForQualityGate()
                  if (qg.status != 'OK') {
                      error "Pipeline aborted due to quality gate failure: ${qg.status}"
                  }
              }
     }

  }
}