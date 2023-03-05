pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME' 
  }
  stages {
    stage ('Build Maven') {
      steps {
        sh 'mvn clean package'
      }
    }
  }
}
