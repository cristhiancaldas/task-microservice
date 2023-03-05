pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME' 
  }
  stages {
    stage ('Unit Test') {
            steps {
                container ('maven') {
                    sh 'mvn clean test'
                 }
            }  
    }
    stage ('Build Maven') {
      steps {
        sh 'mvn clean package'
      }
    }
    
  }
}
