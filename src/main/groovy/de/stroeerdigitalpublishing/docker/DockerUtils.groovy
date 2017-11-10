package de.stroeerdigitalpublishing.docker

class DockerUtils {

    def steps

    DockerUtils(steps) {
        this.steps = steps
    }

    def getBuildTags(String owner, String repoName, String registry, String... tags) {
        if (tags.length == 0) {
            return ""
        }

        def out = ""
        for (String tag : tags) {
            out = "${out}-t ${registry}/${owner}/${repoName}:${tag} "
        }

        return out
    }

    def build(String DockerFile,String commitHash) {
        //return steps.sh(returnStdout: true, script: "docker build ${DockerFile} ${buildTags}")
        //return steps.sh(returnStdout: true, script: "docker build -q ${DockerFile}")
        //return steps.sh(returnStdout: true, script: "docker build ${DockerFile} 2>/dev/null | awk '/Successfully built/{print \$NF}'")
        steps.sh "docker build ${DockerFile} -t ${commitHash}"
    }

    def push(String author, String repoName, String dockerRegistry, String... tags) {
        for (String tag : tags) {
            final String script = 'docker push ' + getFullImageTag(author,repoName,dockerRegistry,tag)
            steps.echo steps.sh(returnStdout: true, script: script)
        }
    }

    static def getFullImageTag(String author, String repoName, String dockerRegistry, String tag) {
        return sprintf('%1$s/%2$s/%3$s:%4$s',dockerRegistry,author,repoName,tag)
    }

    def tag(String commitHash, String author, String repoName, String dockerRegistry, String... tags) {
        for (String tag : tags) {
            String fullImageTag = getFullImageTag(author,repoName,dockerRegistry,tag)
            steps.sh "docker tag ${commitHash} ${fullImageTag}"
        }
    }

    def build_tag_push(String author, String repoName, String dockerRegistry, String DockerFile,String commitHash,String... tags) {
        build(DockerFile,commitHash)
        tag(commitHash, author, repoName, dockerRegistry, tags)
        push(author, repoName, dockerRegistry, tags)
    }
}
