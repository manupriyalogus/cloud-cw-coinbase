pipeline {

  environment {
    dockerimagename = "manupriyalogus/cloud-cw-coinbase"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Build project') {
        steps {
            echo 'Building project started.'
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHubLogin', url: 'https://github.com/manupriyalogus/cloud-cw-coinbase.git']]])
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
        }
    }

    stage('Buil docker image'){
        steps{
            sh 'docker build -t manupriyalogus/cloud-cw-coinbase-0.0.1 .'
        }
    }

    stage('Push docker Image'){
        steps{
            withCredentials([string(credentialsId: 'dockerhub_id', variable: 'dockerhubpwd')]) {
            sh 'docker login -u manupriyalogus -p ${dockerhubpwd}'

            sh 'docker push manupriyalogus/cloud-cw-coinbase-0.0.1'
            }
        }
    }

    stage('Deploying App to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "deploymentservice.yml", kubeconfigId: "kubernetes")
        }
      }
    }

  }

}
