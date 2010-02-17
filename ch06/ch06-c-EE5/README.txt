The changes needed to migrate this chapter example to EJB 3 for the 4.3 JBoss EAP are:

ch06-c-ejb3\ejb:

1) Create interface InventoryFacadeInt with InventoryFacade methods. 

2) In file InventoryFacade.java:

   -Add: import javax.ejb3.*;
   -Add: @Stateless annotation before class definition
   -Modify class definition: public class InventoryFacade implements InventoryFacadeInt 
   -Remove superfluous XDoclet ejb descriptor comments.

3) in the build.xml:

   -Remove J2EE 2.1 local jar and add JBoss AS lib in classpath for EJB3 annotations and imports.
   -Remove the superfluous gen_src_dir and XDoclet EJB Descriptor artifact generation.
   -build now only packages the class files in the ejb jar. 


ch06-c-ejb3\webapp:

1) In file ControllerServlet:

    -Modify controller servlet by removing call to ServiceLocator and all the Home object references and replace with simple 
     JNDI lookup to the injected local object:

     private static final String COMP_NAME="/jaw/InventoryFacade/local";

        InventoryFacadeInt inventory = null;

        try {
		
		InitialContext ctx = new InitialContext();

        	inventory = (InventoryFacadeInt) ctx.lookup(COMP_NAME);

		} catch (Exception se) {
			throw new RuntimeException(se.getMessage());
		}

     Note: In Jboss 5.0 example we use the @EJB annotation that eliminates the JNDI lookup.

ch06-c-ejb3\common:

1) In CarDTO and AccountingDTO:
   -Add: import javax.persistence.*;
   -Add: @Entity, @Table, @Id, @GenerateedClass and @Column annotations, removing all Hibernate XDoclet comments.

2) In ServiceLocator.java:
   - Remove superfluous ServiceLocator class ejb home and remote object methods. Note: I
     could have implemented the controller code in a method here but chose not to.
   - Add: import org.hibernate.cfg.*;
   - Replace JNDI lookup of Hibernate SessionFactory with AnnotationConfiguration().buildSessionFactory();
   - Updated catch block to catch simply Exception.

3) Removed hibernate directory
4) Removed compile-lib directory with old J2EE jar
4) Added AS 5.0 common lib dir reference






-Bill Quirk


