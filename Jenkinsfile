pipeline {
    agent any
    environment{
       PROJECT_ID = 'deploy-backend-393210'
       CLUSTER_NAME = 'k8s-cluster'
       LOCATION = 'us-central1-c'
       CREDENTIALS_ID = 'kubernetes'
    }
    tools{
        maven 'maven'
    }
    stages{
       stage('Scm Checkout') {            
		steps {
                  checkout scm
		      }
       }  
       stage('Build Maven'){
            steps{
	         checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github_creds', url: 'https://github.com/ErAdarshChauhan/deploy-springboot-with-mongodb-to-k8s.git']])
	         sh 'mvn clean install'
            }
        }
	stage('Test') { 
		steps {
	          echo "Testing..."
		  sh 'mvn test'
		}
	}
         stage('Build Docker image'){
            steps{
		sh 'whoami'
                script{
                    sh 'docker build -t eradarshchauhan/bookmanagementtool:${BUILD_NUMBER} .'
		    //myimage = docker.build("eradarshchauhan/kubernetes:${env.BUILD_ID}")
                }
            }
        }
        stage('Push image to Docker Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockercred', variable: 'dockercred')]) {
                     sh 'docker login -u eradarshchauhan -p ${dockercred}' 
                    }
                     sh 'docker push eradarshchauhan/bookmanagementtool:${BUILD_NUMBER}'
	      }
            }
        }
	 // stage('Push image to Docker Hub'){
  //               steps {
  //                  script {
  //                     docker.withRegistry('https://registry.hub.docker.com', 'dockercred') {
  //                     myimage.push("${env.BUILD_ID}")
  //                    }   
  //                  }
  //               }
  //           }

		
         stage('Deploy to Google K8s Engine'){
            steps{
		//sh "sed -i 's/kubernetes:latest/kubernetes:${BUILD_NUMBER}/g' deployment.yaml"
		  echo "Deployment started ..."
		  sh 'ls -ltr'
		  sh 'pwd'
		  sh "sed -i 's/latest/${env.BUILD_ID}/g' deployment.yaml"
		  step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
		  echo "Deployment Finished ..."
            }
        }
    } 
  
}	
