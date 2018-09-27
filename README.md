# Stream Processor Archetypes

For building stream processors that communicate with eachother, using CloudEvents, this repository contains a collection of archetypes for a quick developer experience.

## Supported Processors Archetypes

* `source`: Sample project that generates data and submits it via CloudEvents to an injected _target_. 
* `sink`: Sample consumer that receives a CloudEvent from a configured data stream topic.
* `processor`: Sample data processor that reveices CloudEvents and submits them to a different _target_.

## Demo

Watch a short demo of how to use the Processor Archetypes on YouTube:

[![Using CloudEvent Flow Archetypes](https://img.youtube.com/vi/vpNsNVlTJrg/0.jpg)](https://www.youtube.com/watch?v=vpNsNVlTJrg)


## Usage

In order to run the archetype, make sure that the following repo configured in your `settings.xml` file:

```
    <repositories>
        <repository>
            <id>jboss-thirdparty-releases</id>
            <name>JBoss Thirdparty Releases</name>
            <url>https://repository.jboss.org/nexus/service/local/repositories/thirdparty-releases/content/</url>
        </repository>
    </repositories>
```

### OKD and oc CLI

You need OKD and the latest `oc` CLI. We have setup a prepared script for running OKD v3.10, [here](https://github.com/project-streamzi/ocp-broker/blob/clean_up/ocp_asb_streamings.sh), and provision the Strimzi APB and the Event Flow APB.

### Source Archetype

Building an application that sends CloudEvent data to a stream processing pipeline, by using the following archetype:

```
mvn archetype:generate \
    -DarchetypeGroupId=io.streamzi.eventflow.archetypes \
    -DarchetypeArtifactId=eventflow-source-archetype \
    -DarchetypeVersion=0.0.1 \
    -DgroupId=com.mycorp \
    -DartifactId=my-src \
    -DdhOrg=myorg \
    -Dversion=1.0-SNAPSHOT
```

This generates a new maven project inside the `my-src` folder. Make yourself familiar with the Java API, used in the `src/main/java` folder. Build the project running `mvn clean install`. This will build a _FatJar_ and a _Linux Container_, containing the _FatJar_ application. Now it's time to register the newly created _Stream Processor_ application, by invoking:

```
oc create -f ./src/main/resources/my-source.yml 
```

The new _Stream Processor_ is now available for usage in the Event Flow Editor.


### Sink Archetype

Building an application that receives CloudEvent data from a stream processing pipeline, by using the following archetype:


```
 mvn archetype:generate \
    -DarchetypeGroupId=io.streamzi.eventflow.archetypes \
    -DarchetypeArtifactId=eventflow-sink-archetype \
    -DarchetypeVersion=0.0.1 \
    -DgroupId=com.mycorp \
    -DartifactId=my-sink \
    -DdhOrg=myorg \
    -Dversion=1.0-SNAPSHOT
```
This generates a new maven project inside the `my-sink` folder. Make yourself familiar with the Java API, used in the `src/main/java` folder. Build the project running `mvn clean install`. This will build a _FatJar_ and a _Linux Container_, containing the _FatJar_ application. Now it's time to register the newly created _Stream Processor_ application, by invoking:

```
oc create -f ./src/main/resources/my-sink.yml 
```
The new _Stream Processor_ is now available for usage in the Event Flow Editor.

### Processor Archetype

Building an application that receives and sends CloudEvent data from and to a stream processing pipeline, by using the following archetype:


```
 mvn archetype:generate \
    -DarchetypeGroupId=io.streamzi.eventflow.archetypes \
    -DarchetypeArtifactId=eventflow-processor-archetype \
    -DarchetypeVersion=0.0.2-SNAPSHOT \
    -DgroupId=com.mycorp \
    -DartifactId=my-proc \
    -DdhOrg=myorg \
    -Dversion=1.0-SNAPSHOT
```
This generates a new maven project inside the `my-proc` folder. Make yourself familiar with the Java API, used in the `src/main/java` folder. Build the project running `mvn clean install`. This will build a _FatJar_ and a _Linux Container_, containing the _FatJar_ application. Now it's time to register the newly created _Stream Processor_ application, by invoking:

```
oc create -f ./src/main/resources/my-processor.yml 
```
The new _Stream Processor_ is now available for usage in the Event Flow Editor.
