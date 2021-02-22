JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Items.java \
	Orders.java \
	MySqlDatabase.java \
	Project.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class