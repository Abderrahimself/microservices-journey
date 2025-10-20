pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        DOCKER_HUB_REPO = 'abderrahimself'
        IMAGE_TAG = 's11'
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
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: 'https://registry.hub.docker.com']) {
                    sh """
                        docker push ${DOCKER_HUB_REPO}/configserver:${IMAGE_TAG}
                        docker push ${DOCKER_HUB_REPO}/eurekaserver:${IMAGE_TAG}
                        docker push ${DOCKER_HUB_REPO}/gatewayserver:${IMAGE_TAG}
                        docker push ${DOCKER_HUB_REPO}/accounts:${IMAGE_TAG}
                        docker push ${DOCKER_HUB_REPO}/cards:${IMAGE_TAG}
                        docker push ${DOCKER_HUB_REPO}/loans:${IMAGE_TAG}
                    """
                }
            }
        }
    }

    post {
        always {
            echo '========== Pipeline Completed =========='
            script {
                try {
                    def testReports = findFiles(glob: '**/target/surefire-reports/*.xml')
                    if (testReports.length > 0) {
                        junit '**/target/surefire-reports/*.xml'
                    }
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
            echo '========== ⚠️ BUILD UNSTABLE (Tests Failed but Build Continued) =========='
        }
    }
}