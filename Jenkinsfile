pipeline {
    agent any

	 stages {
        stage('Change dir stage') {
            steps {
				dir("test") {
					
						sh 'mvn clean install -Dmaven.test.skip=true'
					
				}
			}
		}
	}
}
