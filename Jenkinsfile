pipeline {
  agent any
  stages {
    stage('test') {
      parallel {
        stage('test') {
          steps {
            echo 'done'
          }
        }

        stage('error') {
          steps {
            sleep(unit: 'MILLISECONDS', time: 1000)
          }
        }

      }
    }

  }
}