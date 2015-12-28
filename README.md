## Usage:

To count lines of code of a file or a whole directory with files write:

java -jar CountMyLOCs.jar [options..] [language] [pathToFiles]+

	options can be: 
		* -c for counting comments too
		* -f for full mode, counting imports and package statements too
		* -l to create a log file with filename:loc with the default loc sorting
		* -ls to create a log file with filename:loc and sorting by filenames 
		* -h for help eg. this message
	languages supported so far: 
		* java
		* cpp

## Changelog:

### v1.0 
- initial Version 

### v1.1 
- Changed to count logical loc

### v1.2 
- Can be used as a library with the LOC-class
- sorting for output log file
- paths can be added to the analyze

### v1.3
- C++ language is supported now with language command 'cpp'
