This sample code is courtesy of the book JBoss At Work (O'Reilly).
For more information, see http://www.jbossatwork.com.

There are 10 chapters in the book. Source code for each chapter
can be found in the corresponding directory.

The examples demonstrate the following technology:
- A simple MVC (Model/View/Controller) web application using JSPs and Servlets.
- Database connectivity using a DAO (Data Access Object)
- Hibernate (an Object/Relational Mapper)
- Hypersonic (an embedded Java-based Relational Database)
- Stateless Session Beans
- JMS (Java Message Service) and MDB (Message-driven Beans)
- JavaMail
- Logins using JAAS (Java Authentication and Authorization Service)

The example web application is for a fictional car dealership --
JAW Motors. You will be able to view/create/edit/delete cars
in the dealer's inventory.

The example uses XDoclet extensively to automate the
build process. XDoclet generates a number of configuration files 
for you automatically (web.xml and jboss-web.xml, ejb-jar.xml
hibernate HBM mapping files, etc.).

The following files are copied into your JBoss distribution by 
code/build.xml:

The main application:
- code/build/distribution/jaw.ear --> $JBOSS_HOME/server/default/deploy

The database connection:
- code/sql/jaw-ds.xml --> $JBOSS_HOME/server/default/deploy

The JAAS security settings:
- code/src/META-INF/jaw-login-config.xml --> $JBOSS_HOME/server/default/conf
- code/src/META-INF/jaw-login-config-service.xml --> $JBOSS_HOME/server/default/deploy

Additionally, code/sql/build.xml shows you how to set up and refresh the 
Hypersonic database with the required tables and sample data.

To build the example yourself, make sure that you've made the following installations
and set these environment variables:
-JAVA_HOME
-JBOSS_HOME (use JBoss 4.0.2 - *JBoss 4.0.3+ users see note at bottom*)
-XDOCLET_HOME (use XDOCLET 1.2.3)
-AXIS_1_1_HOME (for Apache Axis 1.1 - http://www.apache.org/dyn/closer.cgi/ws/axis/1_1)
-AXIS_1_2_1_HOME (for Apache Axis 1.2.1 - available at: http://www.apache.org/dyn/closer.cgi/ws/axis/1_2_1)
-JWSDP_HOME (For Java Web Services Developer Pack - use version 1.5)

From each project's top-level directory:
Use "ant all" to build the project from scratch.
Use "ant colddeploy" to copy the files to JBoss.
   NOTE: it is important that JBoss not be running when colddeploy is executed.=

In chapters 4-10:
Use "ant" from the sql sub-directory to create the logins and seed the database with sample data.

Two logins are used in this application:
- username: fsmith, password: fred
- username: jjones, password: john

In chapters 5-10 (for JBoss 4.0.3+ users only):
The hibernate JAR files were moved, so change the common/build.xml file as follows:
From:
<property name="hibernate.lib.dir"
value="${env.JBOSS_HOME}/server/default/deploy/jboss-hibernate.deployer"/>

To:
<property name="hibernate.lib.dir"
value="${env.JBOSS_HOME}/server/default/lib"/> 