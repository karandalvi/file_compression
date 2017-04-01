#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile
# (the default one in this case)
#
default: Node.class DaryHeap.class HeapNode.class PairingHeap.class HuffmanNode.class HuffmanTree.class TreeBuilder.class BinaryFileReader.class BinaryFileWriter.class CodeTableWriter.class decoder.class encoder.class

# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
Node.class: Node.class
    $(JCC) $(JFLAGS) Node.java

DaryHeap.class: DaryHeap.java
  	$(JCC) $(JFLAGS) DaryHeap.java

HeapNode.class: HeapNode.java
		$(JCC) $(JFLAGS) HeapNode.java

PairingHeap.class: PairingHeap.java
		$(JCC) $(JFLAGS) PairingHeap.java

HuffmanNode.class: HuffmanNode.java
		$(JCC) $(JFLAGS) HuffmanNode.java

HuffmanTree.class: HuffmanTree.java
		$(JCC) $(JFLAGS) HuffmanNode.java

TreeBuilder.class: TreeBuilder.java
		$(JCC) $(JFLAGS) TreeBuilder.java

BinaryFileReader.class: BinaryFileReader.java
		$(JCC) $(JFLAGS) BinaryFileReader.java

BinaryFileWriter.class: BinaryFileWriter.java
		$(JCC) $(JFLAGS) BinaryFileWriter.java

CodeTableWriter.class: CodeTableWriter.java
		$(JCC) $(JFLAGS) CodeTableWriter.java

decoder.class: decoder.java
		$(JCC) $(JFLAGS) decoder.java

encoder.class: encoder.class
		$(JCC) $(JFLAGS) encoder.java

# To start over from scratch, type 'make clean'.
# Removes all .class files, so that the next make rebuilds them
#
clean:
        $(RM) *.class
