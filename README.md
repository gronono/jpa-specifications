# jpa-specifications

Exemple d'utilisation de JpaSpecificationExecutor avec JPA static metamodel

Fork de [ce projet exemple](https://github.com/gronono/jpa-specifications)

Cela évite d'avoir des références sur les noms des attributs dans les classes entités :

```
public static final String PROPERTYNAME_xx = "xx";
```

Les modifications concernent :

- le fichier pom.xml du projet par ajout du processeur d'annotations dans le build du projet :

```
    <build>
		<plugins>
			<plugin>
				<groupId>org.bsc.maven</groupId>
				<artifactId>maven-processor-plugin</artifactId>
				<version>2.2.4</version>
				<executions>
					<execution>
						<id>process</id>
						<goals>
							<goal>process</goal>
						</goals>
						<phase>generate-sources</phase>
						<configuration>
							<processors>
								<processor>org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor</processor>
							</processors>
						</configuration>
					</execution>
				</executions>
				<configuration>
					<outputDirectory>src/main/generated</outputDirectory>
				</configuration>
				<dependencies>
					<dependency>
						<groupId>org.hibernate</groupId>
						<artifactId>hibernate-jpamodelgen</artifactId>
						<version>5.0.2.Final</version>
					</dependency>
				</dependencies>
			</plugin>
		</plugins>
	</build>
```
- la classe entité _Person.java_ par suppression des déclarations des propriétés de l'entité.
- la classe _PersonSpecifications.java_ est modifiée pour utiliser la classe *Person_.java*, représentation du metamodel JPA de la classe _Person.java_.
