pipeline{
    agent any

    tools{
        maven "maven"
    }

    environment{
           APP_NAME = "spring-docker-cicd"
           RELEASE_NO= "1.0.0"
           DOCKER_USER= "skumary200"
           IMAGE_NAME= "${DOCKER_USER}"+"/"+"${APP_NAME}"
           IMAGE_TAG= "${RELEASE_NO}-${BUILD_NUMBER}"
    }

    stages{

        stage("print"){
            steps{
                script{
                     bat 'echo %IMAGE_NAME%'
                }

            }
        }

        stage("SCM checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kumarsarvinder/dockerdemo.git']])
            }
        }

        stage("Build Process"){
            steps{
               bat 'mvn clean install'
            }
        }

        stage("Docker Image Build"){
            steps{
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage("Deploy image to hub"){
            steps{
                withCredentials([string(credentialsId: 'docker-secret', variable: 'docker-secret')]) {
                    bat 'docker login -u skumary200 -p %docker-secret%'
                    bat 'docker push %IMAGE_NAME%:%IMAGE_TAG%'
                }
            }
        }


    }

    post{
        always
        {
          emailext attachLog: true, body: '''
            <html>
            <body>
                <p>Github Code</p>
                <p>Build Status: ${BUILD_STATUS}</p>
                <p>Build Number: ${BUILD_NUMBER}</p>
                <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
            </body>
            </html>''', mimeType: 'text/html', replyTo: 'skumary200@gmail.com', subject: 'Jenkins Pipeline Status : ${BUILD_NUMBER}', to: 'skumary200@gmail.com'

        }
    }

}