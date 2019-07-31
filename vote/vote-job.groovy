def gitUrl = 'https://github.com/japais/example-voting-app.git'

job('vote-app-dsl') {
    description('Pipeline de compilacion de imagen del componente vote')
    logRotator(10, 10, -1, -1)

    scm {
        git{
            remote{
                url(gitUrl)
            }
            branch('*/master')   
        }
    }
    triggers {
        scm('*/5 * * * *')
    }
    steps {
        dockerBuildAndPublish{
            repositoryName('jpaisregistry.azurecr.io/vote:${env.BUILD_NUMBER}')
            registryCredentials('jpais-registry')
        }
    }
}