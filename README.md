# README #

### What is this repository for? ###
The project uses Huffman Coding to compress a file for data transmission. It creates a code table containing the Huffman codes for various tokens in the original file and the encoded file in binary format. The project also contains a decoder program to decode the encoded file and reconstruct the original file from it.

### How do I get set up? ###
1. Clone the project and compile all the java files.
2. Create a text file containing one integer value (between 0 and 999999) on each line in the same directory to provide as input for the program.
3. Run the following programs.
java performance input_filename.txt
java encoder input_filename.txt
java decoder encoded.bin code_table.txt