# SharepointAuth
Rewriting coursesclient in Scala -- more to show the project set up with maven and intelli-j than to show serious Scala coding.

Sometime in the distant past, I installed Scala on my machine. As I remember, I just googled ubuntu scala install for instructions.

Initially I called this github repository coursesclientscala, but this name confused intellij on import, and I could not add in the frameworks that I tend to use.

When I renamed the github repository SharepointAuth, imported it, and then tried Add Framework Support, all the standard options were available.

I set up this project as I did the coursesclient project changed the artifact id in the pom.xml file as follows.

<artifactId>SharepointAuth</artifactId>

Of course, it was not yet possible to create a Scala class.

I had to use File->Settings->plugins to get the Scala plugin.

Then I had to add Scala framework support by right clicking on SharepointAuth and selecting Add Frame Support.

Then I had to create the Scala ivy library via download. The prompts help the user through this procedure.

Now New on a folder gives me the option of creating a Scala class. I probably should have done the Scala setup before I pulled the sources down from github.

Now I am going to add Scala capabilities to my maven set up.

I found a [web page](http://davidb.github.io/scala-maven-plugin/usage.html) that provides the following example.

````
<project>
  ...
  <build>
    <sourceDirectory>src/main/scala</sourceDirectory>
    <testSourceDirectory>src/test/scala</testSourceDirectory>
    ...
    <plugins>
      ...
      <plugin>
        <groupId>net.alchim31.maven</groupId>
        <artifactId>scala-maven-plugin</artifactId>
        <version>3.2.1</version>
        ... (see other usage or goals for details) ...
      </plugin>
      ...
    </plugins>
    ...
  </build>
  ...
</project>
````

I found info on formating the README.md [here](https://help.github.com/articles/basic-writing-and-formatting-syntax/).

The above is just a hint with respect to setting up a pom.xml to include building with scala files.

In reality I had to add the following dependency.

````
        <dependency>
            <groupId>org.scala-lang</groupId>
            <artifactId>scala-library</artifactId>
            <version>2.12.2</version>
        </dependency>
````
Here is the plugin section that needs to be added to plugins section of the build section of the pom.xml.
````
            <plugin>
                <groupId>net.alchim31.maven</groupId>
                <artifactId>scala-maven-plugin</artifactId>
                <version>3.2.1</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration/>
            </plugin>
````
In the SharepointAuthRequest.scala file, when the translation from Java to Scala was less than obvious I either added a specific comment or left the original Java line as a comment.

Note in particular that the Scala classOf method does not seem to work with a single-instance object like SharepointAuthRequest -- hence the different logic in setting up the logger.

Note also that Scala has logic built-in to infer types. If I had used that feature the translation from Java to Scala would probably have been less clear.



