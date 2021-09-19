# faulttolerancequarkustemplate
- Template for Quarkus Fault Tolerance spec

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

## Resumo da especificação:
- https://download.eclipse.org/microprofile/microprofile-fault-tolerance-2.0/microprofile-fault-tolerance-spec.html
- This specification defines an easy to use and flexible system for building resilient applications.
- Fault tolerance is about leveraging different strategies to guide the execution and result of some logic.
- Retry policies, bulkheads, and circuit breakers are popular concepts in this area.
- They dictate whether and when executions should take place, and fallbacks offer an alternative result when an execution does not complete successfully.
- The main design is to separate execution logic from execution. The execution can be configured with fault tolerance policies, such as RetryPolicy, fallback, Bulkhead and CircuitBreaker.
- This specification is to define a standard API and approach for applications to follow in order to achieve the fault tolerance.
- interceptor bindings:
    - Timeout: Define a duration for timeout
    - Retry: Define a criteria on when to retry
    - Fallback: provide an alternative solution for a failed execution.
    - CircuitBreaker: offer a way of fail fast by automatically failing execution to prevent the system overloading and indefinite wait or timeout by the clients.
    - Bulkhead: isolate failures in part of the system while the rest part of the system can still function.
    - Asynchronous
- This specification defines a set of annotations to be used by classes or methods. The annotations are interceptor bindings. Therefore, this specification depends on the Java Interceptors and Contexts and Dependency Injection specifications define in Java EE platform.
- The Contexts and Dependency Injection (CDI) specification defines a powerful component model to enable loosely coupled architecture design. This specification explores the rich SPI provided by CDI to register an interceptor so that the Fault Tolerance policies can be applied to the method invocation.
- The Java Interceptors specification defines the basic programming model and semantics for interceptors. This specification uses the typesafe interceptor bindings. The annotations @Asynchronous, @Bulkhead, @CircuitBreaker, @Fallback, @Retry and @Timeout are all interceptor bindings.
- These annotations may be bound at the class level or method level. The annotations adhere to the interceptor binding rules defined by Java Interceptors specification.
- For instance, if the annotation is bound to the class level, it applies to all business methods of the class. If the component class declares or inherits a class level interceptor binding, it must not be declared final, or have any static, private, or final methods. If a non-static, non-private method of a component class declares a method level interceptor binding, neither the method nor the component class may be declared final.
- Since this specification depends on CDI and interceptors specifications, fault tolerance operations have the following restrictions:
    - Fault tolerance interceptors bindings must applied on a bean class or bean class method otherwise it is ignored,
    - invocation must be business method invocation as defined in CDI specification.
    - if a method and its containing class don’t have any fault tolerance interceptor binding, it won’t be considered as a fault tolerance operation.
- The MicroProfile config specification defines a flexible config model to enable microservice configurable and achieve the strict separation of config from code. All parameters on the annotations/interceptor bindings are config properties. They can be configured externally either via other predefined config sources (e.g. environment variables, system properties or other sources). For an instance, the maxRetries parameter on the @Retry annotation is a configuration property. It can be configured externally.
- The MicroProfile Metrics specification provides a way to monitor microservice invocations. It is also important to find out how Fault Tolerance policies are operating, e.g.
    - When Retry is used, it is useful to know how many times a method was called and succeeded after retrying at least once.
    - When Timeout is used, you would like to know how many times the method timed out.
- Because of this requirement, when Microprofile Fault Tolerance and Microprofile Metrics are used together, metrics are automatically added for each of the methods annotated with a @Retry, @Timeout, @CircuitBreaker, @Bulkhead or @Fallback annotation.
- The implementor of the MicroProfile Fault Tolerance specification must provide one or more Fault Tolerance interceptors. The interceptor(s) provide the functionality for Fault Tolerance annotations. The interceptor(s) will be called if one or more Fault Tolerance annotations are specified. For instance, a Fault Tolerance interceptor will retry the specified operation if the Retry annotation is specified on that operation. The base priority of the lowest priority Fault Tolerance interceptor is Priority.PLATFORM_AFTER+10, which is 4010. If more than one Fault Tolerance interceptor is provided by an implementation, the priority number taken by Fault Tolerance interceptor(s) should be in the range of [base, base+40].
- The Fault Tolerance interceptor base priority can be configured via MicroProfile Config with the property name of mp.fault.tolerance.interceptor.priority. The property value will only be read at application startup. Any subsequent value changes will not take effect until the application restarts.
- Example code:
    - MyLogInterceptor will be invoked first, followed by a Fault Tolerance interceptor that does Retry capability, and then MyPrintInterceptor.
@Retry
@MyLog
@MyPrint
public void myInvok() {
 //do something
}
@Priority(3000)
@MyLog
public class MyLogInterceptor{
 //do logging
}
@Priority(5000)
@MyPrint
public class MyPrintInterceptor{
 //do printing
}
- Use interceptor and annotation to specify the execution and policy configuration. The annotation Asynchronous has to be specified for any asynchronous calls. Otherwise, synchronous execution is assumed.
- Asynchronous means the execution of the client request will be on a separate thread. This thread should have the correct security context or naming context associated with it.
- A method or a class can be annotated with @Asynchronous, which means the method or the methods under the class will be invoked by a separate thread. The method annotated with @Asynchronous must return a Future or a CompletionStage from the java.util.concurrent package. Otherwise, a FaultToleranceDefinitionException occurs.
- When a method annotated with @Asynchronous is invoked, it immediately returns a Future or CompletionStage. The execution of the any remaining interceptors and the method body will then take place on a separate thread.
    - Until the execution has finished, the Future or CompletionStage which was returned will be incomplete.
    - If the execution throws an exception, the Future or CompletionStage will be completed with that exception. (I.e. Future.get() will throw an ExecutionException which wraps the thrown exception and any functions passed to CompletionStage.exceptionally() will run.)
    - If the execution completes normally and returns a value, the Future or CompletionStage will then delegate to the returned value.
    - Code:
    @Asynchronous
    public CompletionStage<Connection> serviceA() {
        Connection conn = null;
        counterForInvokingServiceA++;
        conn = connectionService();
        return CompletableFuture.completedFuture(conn);
    }
    - The above code-snippet means that the Asynchronous policy is applied to the serviceA method, which means that a call to serviceA will return a CompletionStage immediately and that execution of the method body will be done on a different thread.
    - The @Asynchronous annotation can be used together with @Timeout, @Fallback, @Bulkhead, @CircuitBreaker and @Retry. In this case, the method invocation and any fault tolerance processing will occur in a different thread. The returned Future or CompletionStage will be completed with the final result once all other Fault Tolerance processing has been completed. However, the two different return types have some differences.
- Code example:
-  the Retry will not be triggered as the method invocation returns normally.
@Asynchronous
@Retry
public Future<Connection> serviceA() {
   CompletableFuture<U> future = new CompletableFuture<>();
   future.completeExceptionally(new RuntimeException("Failure"));
   return future;
}
- Code example:
- In the following example, the Retry will be triggered as the returned CompletionStage completes exceptionally.
@Asynchronous
@Retry
public CompletionStage<Connection> serviceA() {
   CompletableFuture<U> future = new CompletableFuture<>();
   future.completeExceptionally(new RuntimeException("Failure"));
   return future;
}
The above behaviour makes it easier to apply Fault Tolerance logic around a CompletionStage which was returned by another component, e.g. applying @Asynchronous, @Retry and @Timeout to a JAX-RS client call.
It is apparent that when using @Asynchronous, it is much more desirable to specify the return type CompletionStage over Future to maximise the usage of Fault Tolerance.
A call to a method annotated with @Asynchronous will never throw an exception directly. Instead, the returned Future or CompletionStage will report that its task failed with the exception which would have been thrown.
For example, if @Asynchronous is used with @Bulkhead on a method which returns a Future and the bulkhead queue is full when the method is called, the method will return a Future where calling isDone() returns true and calling get() will throw an ExecutionException which wraps a BulkheadException.
Timeout prevents from the execution from waiting forever. It is recommended that a microservice invocation should have timeout associated with.
A method or a class can be annotated with @Timeout, which means the method or the methods under the class will have Timeout policy applied.