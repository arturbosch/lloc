To count lines of code of a file or a whole directory with files write:
java -jar CountMyLOCs.jar [options..] [language] [pathToFiles]+
	options can be: 
		-c for counting comments too
		-f for full mode, counting imports and package statements too
		-l to create a log file with filename:loc with the default loc sorting
		-ls to create a log file with filename:loc and sorting by filenames 
		-h for help eg. this message
	languages supported so far: 
		java