package snippet;

public class Snippet {
	<!--jasper report-->
	            <plugin>
	                <groupId>com.alexnederlof</groupId>
	                <artifactId>jasperreports-plugin</artifactId>
	                <version>2.3</version>
	                <executions>
	                    <execution>
	                        <phase>process-sources</phase>
	                        <goals>
	                            <goal>jasper</goal>
	                        </goals>
	                    </execution>
	                </executions>
	                <configuration>
	                    <!-- These are the default configurations: -->
	                    <compiler>net.sf.jasperreports.engine.design.JRJdtCompiler</compiler>
	                    <sourceDirectory>${project.basedir}/src/main/resources/reports/jrxml</sourceDirectory>
	                    <outputDirectory>${project.build.outputDirectory}/reports/jasper</outputDirectory>
	                    <outputFileExt>.jasper</outputFileExt>
	                    <xmlValidation>true</xmlValidation>
	                    <verbose>false</verbose>
	                    <numberOfThreads>4</numberOfThreads>
	                </configuration>
	            </plugin>
	            <!--end of jasper report-->
	        </plugins>
}

