package de.stroeerdigitalpublishing.git

class GitUtils {
    def steps

    GitUtils(steps) {
        this.steps = steps
    }

    String getRepoName() {
        return ((String)steps.scm.getUserRemoteConfigs()[0].getUrl().tokenize('/')[3].split("\\.")[0]).toLowerCase()
    }

    String getShortCommitHash () {
        return steps.sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
    }

    String getCommitHash(){
        return ((String) steps.sh(returnStdout: true, script: 'git rev-parse HEAD')).replace('\n','').replace('\r','').replace(' ','')
    }
}
