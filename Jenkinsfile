pipeline
{
    agent any
	tools
	{
		maven 'Maven'
	}
	options
    {
        timeout(time: 1, unit: 'HOURS')
		
        // Discard old builds after 5 days or 5 builds count.
        buildDiscarder(logRotator(daysToKeepStr: '5', numToKeepStr: '5'))
	  
	    //To avoid concurrent builds to avoid multiple checkouts
	    disableConcurrentBuilds()
    }
    stages
    {
	    stage ('checkout')
		{
			steps
			{
				checkout scm
			}
		}
		stage ('Build')
		{
			steps
			{
				sh "mvn install"
			}
		}
		stage ('Unit Testing')
		{
			steps
			{
				sh "mvn test"
			}
		}
		stage ('Sonar Analysis')
		{
			steps
			{
				withSonarQubeEnv("com_piyush_nishant_3145803_sonar") 
				{
					sh "mvn clean package sonar:sonar"
				}
			}
		}
		stage ('Upload to Artifactory')
		{
			steps
			{
				rtMavenDeployer (
                    id: 'deployer',
                    serverId: 'piyush.nishant.nagp@3145803',
                    releaseRepo: 'piyush-nishant-jenkins-integration',
                    snapshotRepo: 'piyush-nishant-jenkins-integration-snapshot'
                )
                rtMavenRun (
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: 'deployer',
                )
                rtPublishBuildInfo (
                    serverId: 'piyush.nishant.nagp@3145803',
                )
			}
		}
		
		stage ('Docker Image')
		{
			steps
			{
				sh returnStdout: true, script: '/usr/local/bin/docker build -t piyushnishantedu/devopssampleapplication_piyush_nishant:${BUILD_NUMBER} -f Dockerfile .'
			}
		}
		stage('Test image')
		{
		    steps
		    {
		        echo "Image is created"
		    }
		}
		stage('Push Image to DTR')
		{
		    steps
		    {
		        withDockerRegistry(credentialsId: 'docker-hub', url: 'https://registry.hub.docker.com') 
		        {
                       sh returnStdout: true, script: '/usr/local/bin/docker push piyushnishantedu/devopssampleapplication_piyush_nishant:${BUILD_NUMBER}'
                }
		    }
		}
		stage ('Docker deployment')
		{
		    steps
		    {
		        withDockerRegistry(credentialsId: 'docker-hub', url: 'https://registry.hub.docker.com') 
		        {
		            sh 'docker run --name devopssampleapplication_piyush_nishant -d  piyushnishantedu/devopssampleapplication_piyush_nishant:${BUILD_NUMBER}'
		        }
		    }
		}
		stage('Test Deployment') 
		{
		    steps
		    {
		        echo "Deployment Completed"
		    }
		}

		/*stage ('Push to DTR')
	    {
		    steps
		    {
		    	sh returnStdout: true, script: '/usr/local/bin/docker push 192.168.99.100/devopssampleapplication_piyush_nishant:${BUILD_NUMBER}'
		    }
	    }
        stage ('Stop Running container')
    	{
	        steps
	        {
	            sh '''
                    ContainerID=$(docker ps | grep 7015 | cut -d " " -f 1)
                    if [  $ContainerID ]
                    then
                        docker stop $ContainerID
                        docker rm -f $ContainerID
                    fi
                '''
	        }
	    }

		stage ('Docker deployment')
		{
		    steps
		    {
		        sh 'docker run --name devopssampleapplication_piyush_nishant -d -p 7015:8080 192.168.99.100/devopssampleapplication_piyush_nishant:${BUILD_NUMBER}'
		    }
		}*/
	}
	post 
	{
        always 
		{
			echo "*********** Executing post tasks like Email notifications *****************"
        }
    }
}