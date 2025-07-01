node {
    docker.image('maven:3-alpine').inside('-v $HOME/.m2:/root/.m2') {
        stage('Pull repository') {
            checkout scm
        }
        stage('Build') {
            sh 'mvn -B -DskipTests clean package'
        }
        stage('Test') {
            sh 'mvn test'
        }
        stage('Stash jar file') {
            stash includes: 'target/server-0.0.1-SNAPSHOT.jar', name: 'binary'
        }
    }
}
node {
    stage('Unstash jar file') {
        unstash 'binary'
    }
    stage('Build and push Docker image') {
        def customImage = docker.build "(yatassaye/backend-etudiant))/basic-server"
        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
            customImage.push("$BUILD_NUMBER")
            customImage.push("latest")
        }
    }
}


node {
    stage('Build ng image') {
        def customNodeImage = docker.build "node-with-ng"
        customNodeImage.inside {
            stage('Pull repository') {
                checkout scm
            }
            stage('Install npm') {
                sh 'npm install'
            }
            stage('Build') {
                sh 'ng build'
            }
            stage('Stash dist folder') {
                stash includes: 'dist/**/*', name: 'distFolder'
            }
        }
    }
}
node {
    stage('Unstash dist folder') {
        unstash 'distFolder'
    }
    stage('Build Docker image') {
        def customImage = docker.build("(yatassaye/frontend-etudiant)/basic-client", "-f ./dockerfiles/nginx/Dockerfile .")
        stage('Push Docker image') {
            docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                customImage.push("$BUILD_NUMBER")
                customImage.push("latest")
            }
        }

    }
}