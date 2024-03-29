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
            echo 'Pulling changes from the branch '+ params.branch
            echo "Pulling changes from the branch ${params.branch}"
            checkout([$class: 'GitSCM', branches: [[name: "*/${params.branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHubLogin', url: 'https://github.com/manupriyalogus/cloud-cw-coinbase.git']]])
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
        }
    }

    stage('Buil docker image'){
        steps{
            sh 'docker build -t manupriyalogus/cloud-cw-coinbase:'+params.tag +' .'
        }
    }

    stage('Push docker Image'){
        steps{
            withCredentials([string(credentialsId: 'dockerhub_id', variable: 'dockerhubpwd')]) {
            sh 'docker login -u manupriyalogus -p ${dockerhubpwd}'

            sh 'docker push manupriyalogus/cloud-cw-coinbase:'+params.tag
            }
        }
    }

    stage('Deploying App to Kubernetes') {
      steps {
            sh 'gcloud container clusters get-credentials cw-cluster --zone us-central1-a --project eloquent-marker-338206'
            sh 'kubectl apply -f /var/lib/jenkins/workspace/coinbase-pipline-all/deployment.yml'
      }
    }



}
}
