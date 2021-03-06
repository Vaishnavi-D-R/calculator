pipeline {
    agent any
    tools{
        maven 'MAVEN-3.6.3'
        jdk 'JDK 15'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('sonar-server') {
                sh 'mvn clean package sonar:sonar'
              }
            }
          }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
            post{
                success{
                    echo "========Deploying executed successfully========"
                    mail bcc: '', body: 'deploying is sucesfull', cc: '', from: '', replyTo: '', subject: 'deployed', to: 'vaishnavidr123@gmail.com'
                }
                failure{
                 echo "========Deploying failed========"
                 mail bcc: '', body: 'deploying failed', cc: '', from: '', replyTo: '', subject: 'deployed', to: 'vaishnavidr123@gmail.com'
                }
            }
        }
      stage('collect artifact'){
           steps{
               archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
           }
        }
           stage('deploy to artifactory')
           {
              steps{
                    rtUpload (
                        serverId: 'ARTIFACTORY_SERVER',
                            spec: '''{
                                "files": [
                                {
                                    "pattern": "target/*.jar",
                                    "target": "art-doc2-dev-loc/sample/"
                                }
                                ]
                            }''',
                buildName: 'holyFrog',
                buildNumber: '42'
                )
            }
           }
         stage('download from artifactory')
           {
              steps{
                    rtDownload (
                    serverId: 'ARTIFACTORY_SERVER',
                    spec: '''{
                    "files": [
                         {
                             "pattern": "art-doc2-dev-loc/sample/",
                             "target": "src/"
                        }
                     ]
                     }''',
 
                    buildName: 'holyFrog',
                    buildNumber: '42'
                )
              }
           }
        
         stage('upload to ec2')
           {
              steps{
          sshagent(['513fbce6-0f7a-4bc0-8cd9-66f09ab7ab56']){
                   sh 'ssh -o StrictHostKeyChecking=no ubuntu@52.25.51.185 pwd'
                   sh 'scp -r C:/Windows/System32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/calculator-pipeline/target/*.jar ubuntu@52.25.51.185:/home/ubuntu/artifacts'
                     }
              }
           }
         stage('upload to s3')
           {
              steps{
        withAWS(region:'us-west-2',credentials:'85aff390-c4e1-4aa6-a350-e8586ec1803c') {
                    s3Upload(file:'artifacts/mindsapp/', bucket:'vaishnavidrbucket', path:'artifacts/')
        }
              }
        }
    }
}
