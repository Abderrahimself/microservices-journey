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
                            sh 'mvn clean compile -DskipTests'
                        }
                    }
                }
                
                stage('Build Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Building Eureka Server =========='
                            sh 'mvn clean compile -DskipTests'
                        }
                    }
                }
                
                stage('Build Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Building Gateway Server =========='
                            sh 'mvn clean compile -DskipTests'
                        }
                    }
                }
                
                stage('Build Accounts Service') {
                    steps {
                        dir('accounts') {
                            echo '========== Building Accounts Service =========='
                            sh 'mvn clean compile -DskipTests'
                        }
                    }
                }
                
                stage('Build Cards Service') {
                    steps {
                        dir('cards') {
                            echo '========== Building Cards Service =========='
                            sh 'mvn clean compile -DskipTests'
                        }
                    }
                }
                
                stage('Build Loans Service') {
                    steps {
                        dir('loans') {
                            echo '========== Building Loans Service =========='
                            sh 'mvn clean compile -DskipTests'
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
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit 'accounts/target/surefire-reports/*.xml'
                        }
                    }
                }
                
                stage('Test Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Testing Cards Service =========='
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit 'cards/target/surefire-reports/*.xml'
                        }
                    }
                }
                
                stage('Test Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Testing Loans Service =========='
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit 'loans/target/surefire-reports/*.xml'
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
                            sh 'mvn package -DskipTests'
                        }
                    }
                }
                
                stage('Package Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Packaging Eureka Server =========='
                            sh 'mvn package -DskipTests'
                        }
                    }
                }
                
                stage('Package Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Packaging Gateway Server =========='
                            sh 'mvn package -DskipTests'
                        }
                    }
                }
                
                stage('Package Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Packaging Accounts Service =========='
                            sh 'mvn package -DskipTests'
                        }
                    }
                }
                
                stage('Package Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Packaging Cards Service =========='
                            sh 'mvn package -DskipTests'
                        }
                    }
                }
                
                stage('Package Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Packaging Loans Service =========='
                            sh 'mvn package -DskipTests'
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
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
                
                stage('Docker - Eureka Server') {
                    steps {
                        dir('eurekaserver') {
                            echo '========== Building Docker Image: Eureka Server =========='
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
                
                stage('Docker - Gateway Server') {
                    steps {
                        dir('gatewayserver') {
                            echo '========== Building Docker Image: Gateway Server =========='
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
                
                stage('Docker - Accounts') {
                    steps {
                        dir('accounts') {
                            echo '========== Building Docker Image: Accounts =========='
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
                
                stage('Docker - Cards') {
                    steps {
                        dir('cards') {
                            echo '========== Building Docker Image: Cards =========='
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
                
                stage('Docker - Loans') {
                    steps {
                        dir('loans') {
                            echo '========== Building Docker Image: Loans =========='
                            sh 'mvn compile jib:dockerBuild -DskipTests'
                        }
                    }
                }
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                echo '========== Pushing Images to Docker Hub =========='
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
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
    }
    
    post {
        always {
            echo '========== Pipeline Completed =========='
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', 
                            fingerprint: true, 
                            allowEmptyArchive: true
        }
        
        success {
            echo '========== BUILD SUCCESSFUL =========='
        }
        
        failure {
            echo '========== BUILD FAILED =========='
        }
    }
}