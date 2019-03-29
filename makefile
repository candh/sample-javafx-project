execute:
	mvn compile exec:java -Dexec.mainClass="com.phonebook.App" -T4 -Dmaven.test.skip=true
