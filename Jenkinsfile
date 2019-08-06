@Library('sfci-pipeline-sharedlib@beta') _


env.SHARE_LIB_VERSION = 'v1'
env.RELEASE_BRANCHES = ['master']
env.RELEASE = true

//Scheduling this job to execute every 45 mins
//def pipelineTriggerCron = { cron('H/10 * * * *') }

def envDef = [
        emailTo: 'jitender.kumar@salesforce.com',
        stopSuccessEmail: true,
        maxDaysToKeepBuild: 7,
        disableConcurrentBuilds: true
]


executePipeline(envDef) {
        stage('Initialization'){
                mavenInit([githubCredsId: "jitu"])
        }
        stage('Checking out source code'){
                checkout scm
        }
        stage('MavenBuild'){
                mavenBuild()
        }
        stage('MavenBuildWithCodeCoverage'){
                mavenBuildWithCodeCoverage()
        }
        stage('mavenDeploySnapshots'){
                mavenDeploySnapshots()
        }
        stage('mavenPostBuild'){
                mavenPostBuild()
        }
        stage('mavenReleasePrepare'){
                mavenReleasePrepare()
        }
        stage('mavenReleasePerform'){
                mavenReleasePerform([staging_profile_id: '3be18f70c618f6'])
        }
        stage('mavenDockerBuild'){
                mavenDockerBuild([dockerfile: 'Dockerfile'])
        }
        stage('mavenDockerPublish'){
                //mavenDockerPublish([])
        }


//        stage('Building docker artifact for the release'){
//                //Building docker artifact for the release
//                mavenDockerReleaseBuild()
//        }
//        stage('Stage docker artifact to docker-sfci-dev registry '){
//                // Stage docker artifact to docker-sfci-dev registry
//                mavenDockerP2PStage()
//        }
//        stage('Promote docker artifact to docker-sfci-rc registry and copy to docker-gcp registry'){
//                 // Promote docker artifact to docker-sfci-rc registry
//                mavenDockerP2PPromote([copy_docker_artifacts_to_gcp_repo:true])
//        }
        
}

