pipeline {
    agent any

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Navigateur à utiliser pour les tests')
        choice(name: 'ENV', choices: ['DEV', 'QA', 'PROD'], description: 'Environnement cible')
        string(name: 'TAGS', defaultValue: '@v1', description: 'Tags Cucumber à exécuter')
        string(name: 'URL', defaultValue: 'https://example.com', description: 'URL de l’application sous test')
    }

    environment {
        API_URL = 'https://xray.cloud.getxray.app/api/v2/authenticate'
        CLIENT_ID = 'AFC9594E0A7F469592C85F5D6BDC6EEB'
        CLIENT_SECRET = 'ae0402b7075192c2a554bde07c9ee9ff424e3ab764a1ac082fda09aa39b370f8'
    }

    stages {
        stage('Retrieve Xray Token') {
            steps {
                script {
                    def tokenResponse = sh(script: """
                        curl -X POST "https://xray.cloud.getxray.app/api/v2/authenticate" \\
                            -H "Content-Type: application/json" \\
                            -d '{
                                "client_id": "AFC9594E0A7F469592C85F5D6BDC6EEB",
                                "client_secret": "ae0402b7075192c2a554bde07c9ee9ff424e3ab764a1ac082fda09aa39b370f8"
                            }' | jq -r '.token'
                    """, returnStdout: true).trim()

                    if (tokenResponse) {
                        echo "Token Xray récupéré avec succès!"
                        env.XRAY_TOKEN = tokenResponse
                    } else {
                        error "Échec de la récupération du token!"
                    }
                }
            }
        }

        stage('Export Features to Xray') {
            steps {
                script {
                    sh """
                        curl -X POST "${XRAY_IMPORT_URL}" \\
                            -H "Content-Type: multipart/form-data" \\
                            -H "Authorization: Bearer ${XRAY_TOKEN}" \\
                            -F "file=@src/test/resources/features.zip"
                    """
                }
            }
        }

        stage('Run Tests') {
            steps {
                bat """
                    mvn test \\
                    -Dcucumber.filter.tags="${TAGS}" \\
                    -Dbrowser="${BROWSER}" \\
                    -Denv="${ENV}" \\
                    -Durl="${URL}"
                """
            }

            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Upload Results to Xray') {
            steps {
                script {
                    sh """
                        curl -X POST "${XRAY_RESULTS_URL}" \\
                            -H "Content-Type: application/json" \\
                            -H "Authorization: Bearer ${XRAY_TOKEN}" \\
                            -d @target/cucumber.json
                    """
                }
            }
        }
    }
}
