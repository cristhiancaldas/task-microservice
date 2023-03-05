pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME' 
  }
  stages {
    stage ('Unit Test') {
            steps {
                    sh 'mvn clean test'
            }  
    }
    stage ('Build Maven') {
      steps {
        sh 'mvn clean package'
      }
    }
    
  }
}
