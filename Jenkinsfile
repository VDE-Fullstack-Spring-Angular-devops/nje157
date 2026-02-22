pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'
        DOCKER_HUB_USER = 'yatassaye'

        BACK_IMAGE_NAME = "${DOCKER_HUB_USER}/gestion-etudiant-back"
        FRONT_IMAGE_NAME = "${DOCKER_HUB_USER}/gestion-etudiant-front"

        IMAGE_TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend Image') {
            steps {
                bat """
                    docker build -t ${BACK_IMAGE_NAME}:${IMAGE_TAG} gestion-etudiants
                """
            }
        }

        stage('Build Frontend Image') {
            steps {
                bat """
                    docker build -t ${FRONT_IMAGE_NAME}:${IMAGE_TAG} gestion-student
                """
            }
        }

        stage('Login to Docker Hub') {
    steps {
        withCredentials([usernamePassword(
            credentialsId: "${DOCKER_HUB_CREDENTIALS}",
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )]) {
            bat """
                echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
            """
        }
         }
}


        stage('Push Images') {
            steps {
                bat """
                    docker push ${BACK_IMAGE_NAME}:${IMAGE_TAG}
                    docker push ${FRONT_IMAGE_NAME}:${IMAGE_TAG}
                """
            }
        }

        stage('Logout') {
            steps {
                bat 'docker logout'
            }
        }
    }
}
