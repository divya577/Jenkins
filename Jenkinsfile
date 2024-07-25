pipeline {
	agent any
	tools {
		maven 'maven 3.8.6'
		jdk 'OpenJDK11'
		}
	stages{
		stage("clean"){
			steps{
				echo "Start Clean"
				bat "mvn clean"
		}
	}
	stage("test"){
		steps{
			echo "Start Test"
				bat "mvn test"
		}
	}
	stage("build"){
		steps{
			echo "Start build"
				bat "mvn install -DskipTests"
			}
		}
	}
}
	