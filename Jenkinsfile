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
                    serverId: 'JfrogId',
                    spec: '''{
                    "files": [
                         {
                             "pattern": "art-doc2-dev-loc-new/sample/",
                             "target": "snapshots/"
                        }
                     ]
                     }''',
 
                    buildName: 'holyFrog',
                    buildNumber: '42'
                )
              }
           }
        
        
    }
}
