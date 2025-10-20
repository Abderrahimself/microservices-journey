pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        DOCKER_HUB_REPO = 'abderrahimself'
        IMAGE_TAG = 's11'
        DOCKER_HUB_CREDS = 'dockerhub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Cloning Repository =========='
                git branch: 'tp-devops',
                    url: 'https://github.com/Abderrahimself/microservices-journey.git'
                sh 'ls -la'
            }
        }

        stage('Build All Services') {
            parallel {
                stage('Build Config Server') {
                    steps {
                        dir('configserver') {
                            echo '========== Building Config Server =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }

                stage('Build Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Building Eureka Server =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }

                stage('Build Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Building Gateway Server =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }

                stage('Build Accounts Service') {
                    steps {
                        dir('accounts') {
                            echo '========== Building Accounts Service =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }

                stage('Build Cards Service') {
                    steps {
                        dir('cards') {
                            echo '========== Building Cards Service =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }

                stage('Build Loans Service') {
                    steps {
                        dir('loans') {
                            echo '========== Building Loans Service =========='
                            retry(3) {
                                sh 'mvn clean compile -DskipTests'
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            parallel {
                stage('SonarQube - Config Server') {
                    steps {
                        dir('configserver') {
                            echo '========== Analyzing Config Server =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=configserver \
                                              -Dsonar.projectName='Config Server' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Config Server'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Config Server: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }

                stage('SonarQube - Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Analyzing Eureka Server =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=eurekaserver \
                                              -Dsonar.projectName='Eureka Server' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Eureka Server'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Eureka Server: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }

                stage('SonarQube - Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Analyzing Gateway Server =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=gatewayserver \
                                              -Dsonar.projectName='Gateway Server' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Gateway Server'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Gateway Server: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }

                stage('SonarQube - Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Analyzing Accounts Service =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=accounts \
                                              -Dsonar.projectName='Accounts Service' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Accounts'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Accounts: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }

                stage('SonarQube - Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Analyzing Cards Service =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=cards \
                                              -Dsonar.projectName='Cards Service' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Cards'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Cards: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }

                stage('SonarQube - Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Analyzing Loans Service =========='
                            script {
                                try {
                                    withSonarQubeEnv('SonarQube-Server') {
                                        sh '''
                                            mvn sonar:sonar -DskipTests \
                                              -Dsonar.projectKey=loans \
                                              -Dsonar.projectName='Loans Service' \
                                              -Dsonar.java.binaries=target/classes
                                        '''
                                    }
                                    echo '✅ SonarQube analysis completed for Loans'
                                } catch (Exception e) {
                                    echo "⚠️ SonarQube analysis failed for Loans: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo '========== Checking Quality Gate =========='
                script {
                    try {
                        timeout(time: 5, unit: 'MINUTES') {
                            def qg = waitForQualityGate()
                            if (qg.status != 'OK') {
                                echo "⚠️ Quality Gate failed: ${qg.status}"
                                currentBuild.result = 'UNSTABLE'
                            } else {
                                echo '✅ Quality Gate passed!'
                            }
                        }
                    } catch (Exception e) {
                        echo "⚠️ Quality Gate check error: ${e.message}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Run Unit Tests') {
            parallel {
                stage('Test Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Testing Accounts Service =========='
                            script {
                                try {
                                    sh 'mvn test'
                                } catch (Exception e) {
                                    echo "⚠️ Tests failed for Accounts: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                    post {
                        always {
                            script {
                                if (fileExists('accounts/target/surefire-reports')) {
                                    junit 'accounts/target/surefire-reports/*.xml'
                                }
                            }
                        }
                    }
                }

                stage('Test Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Testing Cards Service =========='
                            script {
                                try {
                                    sh 'mvn test'
                                } catch (Exception e) {
                                    echo "⚠️ Tests failed for Cards: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                    post {
                        always {
                            script {
                                if (fileExists('cards/target/surefire-reports')) {
                                    junit 'cards/target/surefire-reports/*.xml'
                                }
                            }
                        }
                    }
                }

                stage('Test Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Testing Loans Service =========='
                            script {
                                try {
                                    sh 'mvn test'
                                } catch (Exception e) {
                                    echo "⚠️ Tests failed for Loans: ${e.message}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }
                    }
                    post {
                        always {
                            script {
                                if (fileExists('loans/target/surefire-reports')) {
                                    junit 'loans/target/surefire-reports/*.xml'
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Package Services') {
            parallel {
                stage('Package Config Server') {
                    steps {
                        dir('configserver') {
                            echo '========== Packaging Config Server =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }

                stage('Package Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Packaging Eureka Server =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }

                stage('Package Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Packaging Gateway Server =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }

                stage('Package Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Packaging Accounts Service =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }

                stage('Package Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Packaging Cards Service =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }

                stage('Package Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Packaging Loans Service =========='
                            retry(3) {
                                sh 'mvn package -DskipTests'
                            }
                        }
                    }
                }
            }
        }

        stage('Build Docker Images') {
            parallel {
                stage('Docker - Config Server') {
                    steps {
                        dir('configserver') {
                            echo '========== Building Docker Image: Config Server =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }

                stage('Docker - Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Building Docker Image: Eureka Server =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }

                stage('Docker - Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Building Docker Image: Gateway Server =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }

                stage('Docker - Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Building Docker Image: Accounts =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }

                stage('Docker - Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Building Docker Image: Cards =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }

                stage('Docker - Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Building Docker Image: Loans =========='
                            retry(3) {
                                sh 'mvn compile jib:dockerBuild -DskipTests'
                            }
                        }
                    }
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo '========== Pushing Images to Docker Hub =========='
                script {
                    withCredentials([usernamePassword(credentialsId: env.DOCKER_HUB_CREDS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        retry(3) {
                            sh '''
                                set +x
                                echo "Logging in to Docker Hub..."
                                docker logout || true
                                echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                                echo "Pushing configserver..."
                                docker push abderrahimself/configserver:s11

                                echo "Pushing eurekaserver..."
                                docker push abderrahimself/eurekaserver:s11

                                echo "Pushing gatewayserver..."
                                docker push abderrahimself/gatewayserver:s11

                                echo "Pushing accounts..."
                                docker push abderrahimself/accounts:s11

                                echo "Pushing cards..."
                                docker push abderrahimself/cards:s11

                                echo "Pushing loans..."
                                docker push abderrahimself/loans:s11

                                echo "Logging out..."
                                docker logout
                            '''
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo '========== Pipeline Completed =========='
            script {
                try {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                } catch (Exception e) {
                    echo "⚠️ No test reports found or failed to process: ${e.message}"
                }
            }
            archiveArtifacts artifacts: '**/target/*.jar',
                            fingerprint: true,
                            allowEmptyArchive: true
        }

        success {
            echo '========== ✅ BUILD SUCCESSFUL =========='
        }

        failure {
            echo '========== ❌ BUILD FAILED =========='
        }

        unstable {
            echo '========== ⚠️ BUILD UNSTABLE (Tests/Quality Gate Failed but Build Continued) =========='
        }
    }
}