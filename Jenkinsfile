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
        script {
          kubeconfig(caCertificate: '''-----BEGIN CERTIFICATE-----
MIIELTCCApWgAwIBAgIRANuR5lFeJK1pl3V8oDyIUMcwDQYJKoZIhvcNAQELBQAw
LzEtMCsGA1UEAxMkZmU2ZmNhMDAtMWM2YS00NzExLWIyYjctZjI0NGI4MmZkMjc5
MCAXDTIyMDExNjAzMzgzNloYDzIwNTIwMTA5MDQzODM2WjAvMS0wKwYDVQQDEyRm
ZTZmY2EwMC0xYzZhLTQ3MTEtYjJiNy1mMjQ0YjgyZmQyNzkwggGiMA0GCSqGSIb3
DQEBAQUAA4IBjwAwggGKAoIBgQDkmGQbTONEtV5KeT+gHuFsJJAp6LM46fOrZ3aw
4WcxOn3H97P7Kp0Arjh1GE2C2WB5YzYGTXH3rjc8r4xFL71rJnTrK3D8ostNf5yg
j50n+ylhmGPeUaTWCgDFD9siCoqNxgKfUjGQDOBPP5+ECDhyk0yvtp1SiGPAT47D
C6zSqrrOYEe9s2jHwgICkshTcsonDknHdy448utktlzvqXeUYo1eKhwwwH8sGGnO
kcMYf6190fgSf7633J8F6KhUbwb5ywaUcIJfZYt0x+BJCgjEjEY12/gFcgKvJmfD
u4RjIA94HZLQhgHHOXCgNO6kjHVzxwbtB3JMtF8eN8Mf3FGzPbSq1Mo5EgRNQroc
k76mfR2wOMooCb8mWUznJvAWOF2BHSmg4w+Gkj9eZ1PbNt/zThjJ/NKm3HcpGXJg
D87TDpatKZBfDYQHUy9YcLiE+dgOBVPXJs0etlD4SIODH53AlkgTdcTkfReFEFFl
PiyaRP8qYmLIie+u0hW/aLz5328CAwEAAaNCMEAwDgYDVR0PAQH/BAQDAgIEMA8G
A1UdEwEB/wQFMAMBAf8wHQYDVR0OBBYEFBjF/m6RBOPYIa1NlYeZ8IXyMVSaMA0G
CSqGSIb3DQEBCwUAA4IBgQBS0WZ8uhT0QJrfM3kL6v6JW4WdIphefBfQ+Gvh/5yD
TDnBkA5eQSSION2eNMLl5SgqJqmRinCoSWlb9mKC64R/hIGotk9m78CamScxWrY9
6U7UjK2sOg3PxO6PAjiWRYBP1O/Fd/al7D4MXET7LnexyG1a9qda/voOP8rBw+i4
Xrewf42WtTLeR7sIJ2uenzqMTuqPrkKBoTj9QJ1Ffwu8WSCd4kH8g7CJzUlvCLD/
Eb8tqjD1MjKGWEvBbEkRv16E5KOvIMmC3mykpjaRhBdehdzfIOLn0cSmu7eeVXa4
kbFnR9LTmNK8h4y78FvB6/7ogdns0/Y13+s9LVrn4AMq/+J3EeFHemdv6NyFBzCC
zh5ChC1V6kcQN8SbzsXsTRK0UaLmuDF+sfRr6i/knkhx4CpdX2GO1Tv8+5gFkdy1
nR/hF5MoL55An97cpC7kwpep9ZkzFxikjpGFojG/XUjYaebaKpF7Hrum+T5g9C4s
urqlUkoBCz1fj0vet3hg86U=
-----END CERTIFICATE-----''', credentialsId: 'gke-jenkins-sa-token', serverUrl: 'https://34.121.24.184',configs: "deploymentservice.yml") {

}
        }
      }
    }

}


}
