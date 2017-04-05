
# Initialize
JCC = javac
JFLAGS = -g
RM = rm

# Build
default: performance.class decoder.class encoder.class

performance.class: performance.java
	$(JCC) $(JFLAGS) performance.java

decoder.class: decoder.java
	$(JCC) $(JFLAGS) decoder.java

encoder.class: encoder.java
	$(JCC) $(JFLAGS) encoder.java

# Clean
clean:
	$(RM) *.class
