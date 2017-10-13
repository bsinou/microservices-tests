# spring-batch-minimal
Minimal bundle to start a Spring Framework Batch Job from the command line.

This bundle does not use spring boot in order to unveil the magic and really understand the underlying binding.

## How-to

To launch the job:

```
cd path/to/your/project
java -cp build/classes:build/libs/* org.springframework.batch.core.launch.support.CommandLineJobRunner net.sinou.sandbox.springbatch.minimal.BatchConfiguration importUserJob
```