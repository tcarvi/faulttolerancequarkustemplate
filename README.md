# faulttolerancequarkustemplate
Template for Quarkus Fault Tolerance spec

### Directory: src/main/java/com/tcarvi/config
- Configuration of your application parameters. 
- Specification [microprofile-config](https://microprofile.io/project/eclipse/microprofile-config)
- Class ConfigController1 injects a config, with @ConfigProperty pattern
- Class ConfigController2 injects a config, with ConfigProvider.getConfig().getValue() pattern

### File: src/main/resources/application.yaml
- List of parameters to be injected 

## Packaging and running the application
- To generate a jar executable:  
```console
mvn package
```  

---

- It produces:  
    - `configquarkustemplate.jar`
    - `configquarkustemplate-runner.jar`

---

- To run the application:
```console
java -jar target/configquarkustemplate-runner.jar
```  

---

- To launch the entry page, open your browser at the following URL
    - http://localhost:8080

## Running the application in dev mode
- You can run your application in dev mode that enables live coding using:  
```console
mvn compile quarkus:dev
```  

## Creating a native executable
- Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation.
- You can create a native executable using:
```console
mvn package -Dquarkus.package.type=native
```  
- Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:
```console
mvn package -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```  
Or to use Mandrel distribution:
```console
mvn package -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11
```  

You can then execute your native executable with:
```console
./target/configquarkustemplate-runner
```  
If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.