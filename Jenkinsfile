pipeline {
    agent any

     stages{
            stage('checkout'){
                steps{
                    git credentialsId: 'login_git',
                     branch: 'master',
                    url: 'https://github.com/Amy2910/RestAssured.git'
                }
            }
            stage('build'){
                steps{
                    bat 'mvn clean install'
                }
            }
            stage('test'){
                steps{
                    bat 'mvn test'
                }
            }
        }
    }