pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               withMaven(maven : 'Maven') {
						bat 'mvn clean install -Dmaven.test.skip=true'
					}
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
