pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
    jdk   'JAVA_HOME'
  }
   stages {
      stage ('Unit Testing') {
                steps {
                        sh 'mvn test'
                }
      }

      stage ('Integration testing') {
            steps {
                    sh 'mvn verify -DskipUnitTests'
            }
      }

      stage('Maven build'){

             steps{
                 script{
                       sh 'mvn clean install'
              }
            }
      }

    stage('SonarQube analysis') {
        environment {
            SCANNER_HOME = tool 'SonarQubeScanner'
        }
        steps {
            withSonarQubeEnv( installationName: 'SonarQubeServer',credentialsId: 'sonarQube-token') {
            sh '''$SCANNER_HOME/bin/sonar-scanner \
             -Dproject.settings=sonar-project.properties '''
            }
        }
    }

    stage('Quality Gate Status'){
          steps{
              script{
                 waitForQualityGate abortPipeline: false, credentialsId: 'sonarQube-token'
                    }
              }
    }
  }
}

/*
pipeline{

    agent any

    stages {

        stage('Git Checkout'){

            steps{

                script{

                    git branch: 'main', url: 'https://github.com/vikash-kumar01/mrdevops_javaapplication.git'
                }
            }
        }
        stage('UNIT testing'){

            steps{

                script{

                    sh 'mvn test'
                }
            }
        }
        stage('Integration testing'){

            steps{

                script{

                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }
        stage('Maven build'){

            steps{

                script{

                    sh 'mvn clean install'
                }
            }
        }
        stage('Static code analysis'){

            steps{

                script{

                    withSonarQubeEnv(credentialsId: 'sonar-api') {

                        sh 'mvn clean package sonar:sonar'
                    }
                   }

                }
            }
            stage('Quality Gate Status'){

                steps{

                    script{

                        waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api'
                    }
                }
            }
        }

}
*/