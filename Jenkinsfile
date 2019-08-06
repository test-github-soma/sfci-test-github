@Library('sfci-pipeline-sharedlib@soma-github') _

def pipelineTriggerCron = { pollSCM('*/2 * * * *') }
executePipeline([pipelineTriggerCron: pipelineTriggerCron]){
        stage('Initialization'){
                mavenInit([gitCredsId: 'soma-github'])
        }
        
        stage('Checking out source code'){
                checkout scm
        }
        
        stage('Build Jars'){
                mavenBuild()
        }
        
        stage('Deploy Snapshot Jars'){
                mavenDeploySnapshots()
        }
        
        stage('Build Docker Image'){
                mavenDockerBuild([dockerfile: 'Dockerfile'])
        }
        stage('Publish Docker Image to Artifactory'){
                mavenDockerPublish()
        }
}

