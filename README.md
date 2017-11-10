# jenkins-pipeline-utils
Public Jenkins Pipeline Utilities Library which are build upon Kubernetes
To use this library add the repository and the artifact as maven dependency using the following mechanics:

#### Grape
```groovy
@GrabResolver(name='stroeerdigitalpublishing', root='https://dl.bintray.com/stroeerdigitalpublishing/maven/')
```

```groovy
@Grab(group='de.stroeerdigitalpublishing', module='jenkins-pipeline-utils', version='1.0-latest')
```

#### Maven
```xml
<repository>
  <id>stroeerdigitalpublishing</id>
  <name>stroeerdigitalpublishing</name>
  <url>https://dl.bintray.com/stroeerdigitalpublishing/maven/</url>
</repository>
```

```xml
<dependency>
  <groupId>de.stroeerdigitalpublishing</groupId>
  <artifactId>jenkins-pipeline-utils</artifactId>
  <version>1.0-latest</version>
  <type>pom</type>
</dependency>
```

#### Gradle
```groovy
maven {
        url "https://dl.bintray.com/stroeerdigitalpublishing/maven/"
}
```

```groovy
compile 'de.stroeerdigitalpublishing:jenkins-pipeline-utils:1.0-latest'
```

#### Ivy
```xml
<ibiblio name="stroeerdigitalpublishing" 
         root="https://dl.bintray.com/stroeerdigitalpublishing/maven/" 
         m2compatible="true" />
```

```xml
<dependency org='de.stroeerdigitalpublishing' name='jenkins-pipeline-utils' rev='1.0-latest'>
  <artifact name='jenkins-pipeline-utils' ext='pom' ></artifact>
</dependency>
```

## Requirements to build and publish (authorized persons only) locally 

- [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- ``JAVA_HOME`` enviroment variable set
- Optinally use [docker](https://www.docker.com/) to build
- [Bintray Account](https://bintray.com/)
- [Administrative Access to Binatry Project](https://bintray.com/stroeerdigitalpublishing/maven/jenkins-pipeline-utils)

## Build and publish using local jdk enviroment

#### Windows
```sh
$ ./gradlew.bat clean build bintrayUpload -PbuildNumber=<buildNumber> -PbintrayPublish=<true|false> -PbintrayUser=<user> -PbintrayApiKey=<apiKey>
```

#### Linux
```sh
$ ./gradlew clean build bintrayUpload -PbuildNumber=<buildNumber> -PbintrayPublish=<true|false> -PbintrayUser=<user> -PbintrayApiKey=<apiKey>
```

#### Docker

```sh
$ docker run -it \
             --rm \
             --workdir /root/project \
             -v <local-project-path>:/root/project \
             openjdk:8u131-jdk-apline \
             ./gradlew clean build bintrayUpload -PbuildNumber=<buildNumber> \
             -PbintrayPublish=<true|false> -PbintrayUser=<user> -PbintrayApiKey=<apiKey>
```

