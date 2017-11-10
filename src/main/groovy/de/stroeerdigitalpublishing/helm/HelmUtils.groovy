package de.stroeerdigitalpublishing.helm

class HelmUtils {
    def steps
    HelmUtils(steps){
        this.steps = steps
    }

    String escapeString(input){
        def command = $/echo ${input} | sed 's/\//\-/'/$
        return steps.sh(returnStdout: true, script: command).trim()
    }
}
