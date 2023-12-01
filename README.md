# Person-JavaEE-WS

How to build this Java EE app on Oracle Weblogic server?

Set up guide: 
https://blogs.oracle.com/weblogicserver/weblogic-server-14-in-eclipse-ide-for-java-ee-developers

Description:

(Free registration is necessary on to download)
JDK11
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

JDK17
https://www.oracle.com/java/technologies/downloads/#java17

Eclipse 2023.03 – Eclipse IDE for Enterprise Java and Web Developers
https://www.eclipse.org/downloads/packages/release/2023-03/r

Eclipse Memory Analyzer - Stand-alone Eclipse RCP Application
https://eclipse.dev/mat/downloads.php

------------------------------------------------------------------------------------------------------
Oracle WebLogic Server 14c
Generic Installer for Oracle WebLogic Server and Oracle Coherence
https://www.oracle.com/middleware/technologies/weblogic-server-
downloads.html#license-lightbox

unwrap to a folder --> cd C:\Users\User01\Downloads\fmw_14.1.1.0.0_wls_lite_Disk1_1of1

set JAVA_HOME and PATH:
JAVA_HOME=JDK11		setx /m JAVA_HOME "C:\Program Files\Java\jdk-11"
PATH=JDK11\bin		SET PATH=C:\Program Files\Java\jdk-11\bin

Run set up jar: 
java.exe -jar fmw_14.1.1.0.0_wls_lite_generic.jar (installing process)

Orace Home:
C:\Oracle\Middleware\Oracle_Home  --> Complete with Examples --> setDomainEnv.cmd --> 
set JAVA_OPTIONS= -Dfile.encoding=utf8 %JAVA_OPTIONS%

UTF8 Console for Hungarian:

Weblogic Domain folder/bin(C:\Leo\domain\leo\bin) -> setDomainEnv.cmd -> JAVA_OPTIONS -> 
Dfile.encoding=utf8 -> Restart Weblogic

------------------------------------------------------------------------------------------------------
Oracle Tools plugin for Eclipse

add into eclipse.ini --> 
--add-opens=java.base/sun.net.www.protocol.jar=ALL-UNNAMED

(OPTIONAL)
change in elcipseini --> 
-vm
C:/Program Files/Java/jdk-11.0.3/bin

JAVA_HOME=JDK17	***
PATH=JDK17\bin	***

start Eclipse

Help --> Install New Software

Work with: http://download.oracle.com/otn_software/oepe/12.2.1.10/photon/repository/ + enter

(if you cannot reach the repository error:
eclipse.ini --> Below the line -vmargs, add the following lines, then save the file:
-Djavax.net.ssl.trustStore=NUL
-Djavax.net.ssl.trustStoreType=Windows-ROOT --> restart Eclipse 
)

Checkbox on:
Oracle WebLogic Scripting Tools
Oracle WebLogic Server Tools
 
x Show only the latest versions of available software
x Group items by category
  Show only software applicable to target environment
x Contact all update sites during install to find required software
  Hide items that are already installed 

If there is set up conflict, use:

Show original error and build my own solution:
x Install fewer items than originally required
x Install different version than originally requested
x Update items already installed
x Remove items already installed

Restart Eclipse 

------------------------------------------------------------------------------------------------------
Create new WebLogic domain

ORA_HOME\oracle_common\common\bin\config.cmd	(start Configuration Wizard)

Create a new domain --> Domain Location: C:\Leo\domain\leo

Create Domain Using Product Templates: 
Basic Weblogic Server Domain - 14.1.1.0.0 [wlserver]*
Admin username/password=weblogic/weblogic1

Domain Mode: Development
Other JDK Location: JDK11 folder

Administration Server --> AdminServer, All Local Addresses, 7001 --> x Start Admin Server

http://localhost:7001/console 
JNDI tree_ AdminServer--> View JNDI Tree

------------------------------------------------------------------------------------------------------
Create New Runtime in Eclipse

File --> New --> Project --> Other ... --> Web --> Dynamic Web Project(empty project Name, only New Server Runtime Environment set up)

New Runtime --> Oracle -->Oracle WebLogic Server(red icon)-->  x Create a new local server --> Next

Name: Oracle WebLogic Server 14.1.1.0
WebLogic Home: C:\Oracle\Middleware\Oracle_Home\wlserver
Java Home: JDK8 or JDK11 folder

Name: Oracle WebLogic Server 14.1.1.0
Server type: x Local
Domain directory: C:\User01\domain\leo	***
x Disable automatic publishing to server --> Finish

Cancel the New Dynamic Web Project

------------------------------------------------------------------------------------------------------
Weblogic Server config for Message-Driven Bean (Printer class***, Connection Factory, Queue)

Services --> Messaging --> JMS Servers --> New
Name=LEO_JMSServer
Target=AdminServer

Services --> Messaging --> JMS Modules --> New
Name=LEO_SystemModule
Target=AdminServer

Services --> Messaging --> JMS Modules --> LEO_SystemModule --> New
Type=Connection Factory
Name=LEO_ConnectionFactory
JNDI Name=LEO_ConnectionFactory

Services --> Messaging --> JMS Modules --> LEO_SystemModule --> New
Type=Queue
Name=LEO_Queue
JNDI Name=LEO_Queue
Create a New Subdeployment, OK
Target=LEO_JMSServer

Restart the server.

------------------------------------------------------------------------------------------------------
Project config (Lombok, Ant)

Import projekct from git to Eclipse workspace. Open in Eclipse. 

Project:
Start eclipse --> Open project from file system

Lombok(PersonWebService):
Search Lombok.jar and run in the folder.(Installing to Eclipse) --> @Getters, @Setters @NoArgsConstructor @AllArgsConstructor
PersonWebService -> Java Build Path ->Web App Libraries ->Add Jar ->lombok-1.18.30 jar
(PersonWebService\src\main\webapp\WEB-INF\lib)

Ant
Eclipse --> Preferences --> Ant --> Runtime --> Global Entries --> C:/Oracle/Middleware/Oracle_Home/wlserver/server/lib/weblogic.jar
clientgen_build.xml(project name:! wsdl=!) --> Run as --> Ant Build(Running and Synchronized Weblogic Server)

Server in Eclipse:
Window/Show View/Servers --> Add/Remove
Publish

Eclipse --> clean
