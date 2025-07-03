pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials' // ID Jenkins
        DOCKER_HUB_USER = 'yatassaye'
        BACK_IMAGE_NAME = "${DOCKER_HUB_USER}/gestion-etudiant-back"
        FRONT_IMAGE_NAME = "${DOCKER_HUB_USER}/gestion-etudiant-front"
        IMAGE_TAG = 'latest' // ou utilisez "${env.BUILD_NUMBER}" ou un tag Git
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend Image') {
            steps {
                script {
                    docker.build("${BACK_IMAGE_NAME}:${IMAGE_TAG}", "./gestion-etudiants/.")
                }
            }
        }

        stage('Build Frontend Image') {
            steps {
                script {
                    docker.build("${FRONT_IMAGE_NAME}:${IMAGE_TAG}", "./gestion-student/.")
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_HUB_CREDENTIALS}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Images') {
            steps {
                script {
                    docker.image("${BACK_IMAGE_NAME}:${IMAGE_TAG}").push()
                    docker.image("${FRONT_IMAGE_NAME}:${IMAGE_TAG}").push()
                }
            }
        }

        stage('Logout') {
            steps {
                sh 'docker logout'
            }
        }
    }
}
