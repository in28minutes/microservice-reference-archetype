
# Microservice Archetype 

An archetype helps in automating the basic setup of a microservice. This helps in standardizing project setup across an organization. 
- Multiple Layers and Dependencies
- Security
- Unit Testing
- Integration Testing
- Integration with Nexus/SONAR etc

## Step By Step Guide

- Read how Maven Archetype plugin works over here - http://maven.apache.org/archetype/maven-archetype-plugin/

### Setting up Reference Project
- We have our reference microservice in the folder microservice-reference. This is a Spring Boot Project with an a couple of controllers and unit/integration tests. We will use this as the reference project to reverse engineer an archetype. 
- When we create a new project using a maven archetype, the two important inputs are groupId and artifactId. In the reference project we would need to make sure that everything that needs to customized based on these inputs should be using similar values. In the microservice-reference, we use the following as the standard:
     - groupId - com.organization.project 
     - artifactId - project-name
- For example, we call our Spring Boot Application class as ProjectNameApplication. "ProjectName" will be replaced with the artifactId variable. 
- Ensure that you configure the latest version of maven-archetype-plugin in your microservice-reference 

```xml
	<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<artifactId>maven-archetype-plugin</artifactId>
					<version>3.0.1</version>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>

```

### Reverse Engineering an Archetype from microservice-reference
- In the command prompt cd to the folder containing this project

```
cd microservice-reference
mvn archetype:create-from-project
```

- You will see the following statements in the log
```
[INFO] Setting default groupId: com.organization.project
[INFO] Setting default artifactId: project-name
[INFO] Setting default version: 0.0.2-SNAPSHOT
[INFO] Setting default package: com.organization.project
```
- Archetype project created in microservice-reference/target/generated-sources/archetype

### Copy the created archetype to the microservice-archetype project
- Copy the archetype project created in earlier step to the folder microservice-archetype

### Install the archetype
- cd to the root of the project and run 
```
mvn clean install
```
- This will install the archetype to your local repository
```
[INFO] Installing /in28Minutes/git/microservice-archetype/microservice-archetype/target/project-name-archetype-0.0.2-SNAPSHOT.jar to /Users/rangaraokaranam/.m2/repository/com/organization/project/project-name-archetype/0.0.2-SNAPSHOT/project-name-archetype-0.0.2-SNAPSHOT.jar
```

### Create a new project using the archetype

- Create a new folder on your hard drive. Let's call it first-project
- Execute the following commands

```
cd first-project
mvn archetype:generate -DarchetypeCatalog=local
```

- archetype plugin asks for a groupId and artifactId as shown below

```
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
Define value for property 'groupId': com.first
Define value for property 'artifactId': first-project
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' com.first: : 
Confirm properties configuration:
groupId: com.first
artifactId: first-project
version: 1.0-SNAPSHOT
package: com.first
 Y: : Y
```
- When the archetype plugin executes successfully, you should see the message shown below:

```
[INFO] Project created from Archetype in dir: /in28Minutes/git/microservice-archetype/first-project
```

### Verify the new project

You can do a ```mvn clean install``` on the new project in first-project to check it everything is good.

## About in28Minutes
- At in28Minutes, we ask ourselves one question everyday. How do we create more effective trainings?
- We use Problem-Solution based Step-By-Step Hands-on Approach With Practical, Real World Application Examples. 
- Our success on Udemy and Youtube (2 Million Views & 12K Subscribers) speaks volumes about the success of our approach.
- While our primary expertise is on Development, Design & Architecture Java & Related Frameworks (Spring, Struts, Hibernate) we are expanding into the front-end world (Bootstrap, JQuery, Angular JS). 

### Our Beliefs
- Best Course are interactive and fun.
- Foundations for building high quality applications are best laid down while learning.

### Our Approach
- Problem Solution based Step by Step Hands-on Learning
- Practical, Real World Application Examples.
- We use 80-20 Rule. We discuss 20% things used 80% of time in depth. We touch upon other things briefly equipping you with enough knowledge to find out more on your own. 
- We will be developing a demo application in the course, which could be reused in your projects, saving hours of your effort.
- All the code is available on Github, for most steps.
  
### Useful Links
- [Our Website](http://www.in28minutes.com)
- [Facebook](http://facebook.com/in28minutes)
- [Twitter](http://twitter.com/in28minutes)
- [Google Plus](https://plus.google.com/u/3/110861829188024231119)
