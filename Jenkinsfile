#!/usr/bin/env groovy

podTemplate(label: 'java',
            containers: [
                containerTemplate(name: 'jdk8',
                                  image: 'openjdk:8u131-jdk-alpine',
                                  ttyEnabled: true,
                                  command: 'cat',
                                  envVars: [
                                          secretEnvVar(key: 'BINTRAY_USER', secretName: 'bintray-secret', secretKey: 'bintrayUser'),
                                          secretEnvVar(key: 'BINTRAY_API_KEY', secretName: 'bintray-secret', secretKey: 'bintrayApiKey')
                                  ])
        ]
) { node('java') {


        if (env.BRANCH_NAME == 'master') {

                def commitHash

                stage('Checkout'){
                    def scmVars = checkout scm
                    commitHash = scmVars.GIT_COMMIT
                }

            container('jdk8') {
                stage('Build') {
                    sh "./gradlew clean build"
                }

                stage('Publish') {
                    sh "./gradlew bintrayUpload -PbintrayPublish=true -PbuildNumber=${commitHash}"
                }
            }

        }
    }
}