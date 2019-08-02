pipeline {
    agent any

	 stages {
        stage('Change dir stage') {
            steps {
				dir("test") {
					withMaven(maven : 'Maven') {
						bat 'mvn clean install -Dmaven.test.skip=true'
					}
				}
			}
		}
	}
}
