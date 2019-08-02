pipeline {
    agent any

	 stages {
        stage('Change dir stage') {
            steps {
				dir("test") {
					
						bat 'mvn clean install -Dmaven.test.skip=true'
					
				}
			}
		}
	}
}
